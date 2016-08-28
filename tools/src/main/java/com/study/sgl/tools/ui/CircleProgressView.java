package com.study.sgl.tools.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.study.sgl.tools.util.L;

/**
 * 环形进度条
 *
 * @author Song.gl
 * @version 2016 08 28 14:17
 */
public class CircleProgressView extends View {

    private int measureHeight; // 控件宽度
    private int measureWidth; // 控件高度

    private Paint circlePaint; // 圆画笔
    private float circleXY; // 圆的圆点
    private float radius; // 圆的半径

    private Paint arcPaint; // 环画笔
    private RectF arcRectF; // 环外接矩形
    private float sweepAngle; // 扫描角度
    private float sweepValue = 90; // 角度

    private Paint textPaint; // 文本画笔
    private String showText = "Android Skill"; // 显示的文本
    private float showTextSize = 50; // 文本大小

    public CircleProgressView(Context context) {
        super(context);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measureWidth, measureHeight);
        initView();
        L.i("onMeasure");
    }

    private void initView() {
        // 设置长度为屏幕宽高的最小值
         float length = Math.min(measureWidth, measureHeight);

        circleXY = length / 2; // 原点坐标
        radius = length / 2 / 2; // 半径长度

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true); // 抗锯齿
        circlePaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));

        arcRectF = new RectF(length / 10, length / 10, length / 10 * 9, length / 10 * 9);
        sweepAngle = sweepValue / 100f * 360f;

        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);
        arcPaint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        arcPaint.setStrokeWidth(length / 10);
        arcPaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setTextSize(showTextSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画圆
        canvas.drawCircle(circleXY, circleXY, radius, circlePaint);
        // 画弧
        canvas.drawArc(arcRectF, 270, sweepAngle, false, arcPaint);
        // 画文字
        canvas.drawText(showText, 0, showText.length(), circleXY, circleXY + showTextSize / 4, textPaint);
        L.i("onDraw");
    }

    public void setShowText(String showText) {
        this.showText = showText;
        invalidate();
    }

    public void setShowTextSize(float showTextSize) {
        this.showTextSize = showTextSize;
        invalidate();
    }

    public void setSweepValue(float sweepValue) {
        if (sweepValue != 0) {
            this.sweepValue = sweepValue;
        } else {
            this.sweepValue = 25;
        }
        invalidate();
    }

    public void forceInvalidate() {
        invalidate();
    }
}