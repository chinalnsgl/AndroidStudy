package com.example.sgl.graphicsandanimaction;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.study.sgl.tools.util.L;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new AnimationSurfaceView(this));

        //ImageView imageView = (ImageView) findViewById(R.id.iv);
        //imageView.setImageBitmap(BitmapUtil.decodeSampledBitmapFromResource(getResources(), R.drawable.san, 100, 100));
    }

    /**
     * 自定义 SurfaceView 表层视图
     * 1. SurfaceView允许其他线程更新视图对象(执行绘制方法)而View不允许这么做，它只允许UI线程更新视图对象。

     2. SurfaceView是放在其他最底层的视图层次中，所有其他视图层都在它上面，所以在它之上可以添加一些层，而且它不能是透明的。

     3. 它执行动画的效率比View高，而且你可以控制帧数。

     4. 因为它的定义和使用比View复杂，占用的资源也比较多，除非使用View不能完成，再用SurfaceView否则最好用View就可以。(贪吃蛇，俄罗斯方块，棋牌类这种帧数比较低的可以使用View做就好)
     */
    class AnimationSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

        LoopThread loopThread; // 绘画线程,可以有多个

        public AnimationSurfaceView(Context context) {
            super(context);
            init();
        }

        private void init() {
            SurfaceHolder holder = getHolder();
            holder.addCallback(this); // 设置生命周期回调方法
            loopThread = new LoopThread(holder, getContext());
        }

        /**
         * 视图可见时执行
         * @param surfaceHolder
         */
        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            loopThread.isRunning = true;
            loopThread.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        /**
         * 视图不可见时执行
         * @param surfaceHolder
         */
        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            loopThread.isRunning = false;
            try {
                loopThread.join(); // 等待线程结束
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * 视图绘制线程
         */
        private class LoopThread extends Thread {
            final SurfaceHolder holder;
            Context context;
            boolean isRunning;
            float radius = 10;
            Paint paint;

            public LoopThread(SurfaceHolder holder, Context context) {
                this.holder = holder;
                this.context = context;
                paint = new Paint();
                paint.setColor(Color.YELLOW);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(8);
            }

            @Override
            public void run() {
                Canvas canvas = null;
                while (isRunning) {
                    try {
                        synchronized (holder) {
                            canvas = holder.lockCanvas(null);
                            doDraw(canvas);
                            // 用于设置执行的帧数 20帧
                            Thread.sleep(10);

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        holder.unlockCanvasAndPost(canvas);
                    }
                }
            }

            private void doDraw(Canvas canvas) {
                // 清屏操作
                canvas.drawColor(Color.BLACK);

                canvas.translate(200, 200);
                canvas.drawCircle(0, 0, radius++, paint);
                if (radius > 100) {
                    radius = 10;
                }
            }
        }
    }

    /**
     * 自定义 View
     */
    class AnimationView extends View {

        float radius = 10;
        Paint paint;

        /**
         * 在代码中 new AnimationView() 执行这个构造
         * 在 XML布局中使用调用两个参数的构造
         *
         * @param context
         */
        public AnimationView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.translate(200, 200);
            canvas.drawCircle(0, 0, radius++, paint);

            if (radius > 100) {
                radius = 10;
            }
            L.i("onDraw");
            invalidate();

        }
    }
}
