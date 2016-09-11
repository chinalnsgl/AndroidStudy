package ren.solid.camerademo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by _SOLID
 * Date:2016/3/23
 * Time:9:43
 * <p/>
 * <uses-permission android:name="android.permission.CAMERA" />
 * <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * <p/>
 * 注：应用程序同时只能存在一个激活的 camera
 * <p/>
 * 关于图片拉伸变形的问题，要把SurfaceView的宽高比例和图片的预览尺寸的宽高比例设置相同
 */
public class CameraSurfaceView extends SurfaceView {

    private static String TAG = "ren.solid.camerademo.view.CameraSurfaceView";

    private SurfaceHolder mSurfaceHolder; // 操作 surface 的接口
    private Camera camera; // 相机
    private Camera.Parameters parameters = null;
    private Context mContext;
    private int mCameraCount; // 摄像头数量
    private int mCurrentCameraFacing = Camera.CameraInfo.CAMERA_FACING_BACK; // 当前摄像头 默认为后置摄像头
    private String mPictureSaveDir; // 图片保存目录
    private String mPictureSavePath; // 图片保存路径
    private OnSavePictureListener mOnSavePictureListener; // 保存图片接口

    public CameraSurfaceView(Context context) {
        this(context, null);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    /**
     * 初始化相机
     */
    private void init() {
        mCameraCount = Camera.getNumberOfCameras();//得到摄像头数量
        mSurfaceHolder = getHolder(); // 获取 Surface 操作接口
        getCamera(); // 获取相机
        mSurfaceHolder.setKeepScreenOn(true);// 屏幕常亮
        mSurfaceHolder.addCallback(new SurfaceCallback());//为SurfaceView的Holder添加一个回调函数

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (camera != null) {
                    camera.autoFocus(null); // 对焦
                }
            }
        });

    }

    /***
     * 得到系统相机
     */
    private void getCamera() {
        if (camera == null)
            camera = Camera.open(mCurrentCameraFacing); // 打开后置摄像头
    }

    /**
     * SurFaceHolder 生命周期回调
     */
    private final class SurfaceCallback implements SurfaceHolder.Callback {

        /**
         * 拍照状态变化时调用该方法
         */
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            Log.i(TAG, "surfaceChanged    " + "width:" + width + "|" + "height:" + height);
            if (camera != null) {
                camera.stopPreview();
                startPreview(holder);
                setCameraParameters(width, height);
            }
        }

        /**
         * SurFaceView 可见时回调
         */
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.i(TAG, "surfaceCreated");
            startPreview(holder); // 开始预览
        }

        /**
         * SurFaceView 不可见时回调
         */
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.i(TAG, "surfaceDestroyed");
            releaseCamera(); // 回收相机
        }

    }

    /***
     * 切换相机摄像头
     */
    public void changCameraFacing() {
        if (mCameraCount > 1) {
            mCurrentCameraFacing = (mCurrentCameraFacing == Camera.CameraInfo.CAMERA_FACING_BACK) ?
                    Camera.CameraInfo.CAMERA_FACING_FRONT : Camera.CameraInfo.CAMERA_FACING_BACK;
            releaseCamera();
            startPreview(mSurfaceHolder);
        } else {
            //手机不支持前置摄像头
        }
    }

    /***
     * 设置相机参数
     *
     * @param width
     * @param height
     */
    private void setCameraParameters(int width, int height) {
        mSurfaceHolder.setFixedSize(width, height);//照片的大小
        parameters = camera.getParameters(); // 获取相机参数
        parameters.setPictureFormat(ImageFormat.JPEG); // 设置图片格式

        parameters.setPreviewSize(width, height); // 设置预览大小
        parameters.setPictureSize(width, height); // 设置保存的图片尺寸

        parameters.setPreviewFpsRange(4, 10);//fps
        parameters.setJpegQuality(100); // 设置照片质量
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);//自动对焦
        //parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//连续对焦
        //camera.cancelAutoFocus();//如果要实现连续的自动对焦，这一句必须加上
        camera.setParameters(parameters);
    }

    /**
     * 开始预览
     */
    private void startPreview(SurfaceHolder surfaceHolder) {
        getCamera();
        try {
            camera.setPreviewDisplay(surfaceHolder); // 设置用于显示预览的SurfaceHolder对象
//            if (mContext.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
//                // parameters.set("orientation", "portrait");
//                camera.setDisplayOrientation(90);//在2.2以上可以使用
//            } else {
//                // parameters.set("orientation", "landscape");
//                camera.setDisplayOrientation(0);//在2.2以上可以使用
//            }
            camera.setDisplayOrientation(getPreviewDegree());
            camera.startPreview(); // 开始预览
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于根据手机方向获得相机预览画面旋转的角度
     */
    private int getPreviewDegree() {
        // 获得手机的方向
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int rotation = windowManager.getDefaultDisplay()
                .getRotation();
        Log.i(TAG, "rotation:" + rotation);
        int degree = 0;
        // 根据手机的方向计算相机预览画面应该选择的角度
        switch (rotation) {
            case Surface.ROTATION_0:
                degree = 90;
                break;
            case Surface.ROTATION_90:
                degree = 0;
                break;
            case Surface.ROTATION_180:
                degree = 270;
                break;
            case Surface.ROTATION_270:
                degree = 180;
                break;
        }
        return degree;
    }

    /**
     * 将拍下来的照片存放在SD卡中
     *
     * @param data
     * @throws IOException
     */
    private void saveToSDCard(byte[] data) throws IOException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 格式化时间
        String filename = format.format(date) + ".jpg";

        File fileFolder = new File(getPictureSaveDir());

        if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
            fileFolder.mkdir();
        }
        File jpgFile = new File(fileFolder, filename);
        FileOutputStream outputStream = new FileOutputStream(jpgFile); // 文件输出流

        //由于在预览的时候，我们调整了预览的方向，所以在保存的时候我们要旋转回来，不然保存的图片方向是不正确的
        Matrix matrix = new Matrix();
        if (mCurrentCameraFacing == Camera.CameraInfo.CAMERA_FACING_BACK) {
            matrix.setRotate(90);
        } else {
            matrix.setRotate(-90);
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

        outputStream.flush(); // 写入sd卡中
        outputStream.close(); // 关闭输出流
        mPictureSavePath = getPictureSaveDir() + File.separator + filename;
        if (mOnSavePictureListener != null) {
            mOnSavePictureListener.onSuccess(mPictureSavePath);
        }
        Toast.makeText(mContext.getApplicationContext(), "图片已保存至:" + mPictureSavePath,
                Toast.LENGTH_LONG).show();

        //这个的作用是让系统去扫描刚拍下的这个图片文件，以利于在MediaSore中能及时更新，
        // 可能会存在部分手机不用使用的情况（众所周知，现在国内的Rom厂商已把原生Rom改的面目全非）
        //mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + mPictureSavePath)));
        MediaScannerConnection.scanFile(mContext, new String[]{
                        mPictureSavePath},
                null, new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        // Log.e(TAG, "扫描完成");
                    }
                });


    }


    private final class MyCameraPictureCallback implements Camera.PictureCallback {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            try {
                saveToSDCard(data); // 保存图片到sd卡中
                camera.startPreview(); // 拍完照后，重新开始预览
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 拍照
     */
    public void takePicture() {
        if (camera != null) {
            camera.takePicture(null, null, new MyCameraPictureCallback());//每次调用takePicture获取图像后，摄像头会停止预览
        } else {
            //TODO: 提示用户相机不存在
        }
    }

    /***
     * 释放相机资源
     */
    public void releaseCamera() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    /**
     * 设置图片的保存路径
     *
     * @param pictureSavePath
     */
    public void setPictureSavePath(String pictureSavePath) {
        mPictureSaveDir = pictureSavePath;
    }

    /***
     * 得到图片保存的目录
     *
     * @return
     */
    public String getPictureSaveDir() {
        String path;
        if (mPictureSaveDir == null)
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                path = Environment.getExternalStorageDirectory()
                        + "/SolidCamera/";
            else {
                path = mContext.getCacheDir().getAbsolutePath()
                        + "/SolidCamera/";
            }
        else {
            path = mPictureSaveDir;
        }
        mPictureSaveDir = path;
        return path;
    }

    public void onResume() {
        getCamera();
    }

    public void onPause() {
        releaseCamera();
    }

    public void setOnSavePictureListener(OnSavePictureListener onSavePictureListener) {
        mOnSavePictureListener = onSavePictureListener;
    }

    public interface OnSavePictureListener {
        void onSuccess(String filePath);
    }
}
