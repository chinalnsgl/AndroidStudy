package com.study.sgl.androidstudy.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 自定义 TextView
 *
 * @author Song.gl
 * @version 2016 08 26 16:36
 */
public class CustomTextView extends TextView {

    Paint paint1; // 画笔1
    Paint paint2; // 画笔2

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 初始化画笔, 设置颜色, 设置为填充方式 Paint.Style.Fill 填充 Paint.Style.STROKE 空心
        paint1 = new Paint();
        paint1.setColor(getResources().getColor(android.R.color.holo_blue_light));
        paint1.setStyle(Paint.Style.FILL);
        paint2 = new Paint();
        paint2.setColor(Color.YELLOW);
        paint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 画矩形 相当于填加背景色了
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint1);
        // 画一个小矩形 少了10像素
        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, paint2);
        // 保存 canvas 当前状态
        canvas.save();
        // 将 canvas 在画布上右移 100 像素
        canvas.translate(100, 0);
        // 画文字
        super.onDraw(canvas);
        // 还原到 canvas 保存时的状态
        // 过程相当于在整个控件的矩形内, 右移100像素画出文字
        canvas.restore();
    }


}