package com.study.sgl.tools.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 图片处理工具类
 *
 * @author Song.gl
 * @version 2016 09 06 14:59
 */
public class BitmapUtil {

    /**
     * 计算图片 inSampleSize
     * @param options options
     * @param reqWidth 宽度
     * @param reqHeight 高度
     * @return inSampleSize
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;

        if (width > reqWidth || height > reqHeight) {
            final int halfWidth = width / 2;
            final int halfHeight = height / 2;

            while (halfWidth / inSampleSize > reqWidth && halfHeight / inSampleSize > reqHeight) {
                inSampleSize *= 2;
            }
        }
        // 缩略比例
        return inSampleSize;
    }

    /**
     * 按指定大小解析图片
     * @param res 资源
     * @param resId ID
     * @param reqWidth 宽
     * @param reqHeight 高
     * @return 图片
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        // 可以在解码的时候避免内存的分配，它会返回一个null的Bitmap，
        // 但是可以获取到 outWidth, outHeight 与 outMimeType。
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res, resId, options);
    }
}