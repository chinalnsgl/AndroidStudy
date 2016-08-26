package com.study.sgl.tools.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.study.sgl.tools.R;

/**
 * 自定义标题栏,扩展布局控件
 *
 * @author Song.gl
 * @version 2016 08 26 19:52
 */
public class TopBar extends RelativeLayout {

    public static final int LEFT_BUTTON = 0;
    public static final int RIGHT_BUTTON = 1;

    private final String leftText; // 左侧按钮文本
    private final int leftColor; // 左侧按钮文本颜色
    private final Drawable leftBackground; // 左侧按钮背景
    private final String rightText; // 右侧按钮文本
    private final int rightColor; // 右侧按钮文本颜色
    private final Drawable rightBackground; // 右铡按钮背景
    private final String titleText; // 标题文本
    private final int titleColor; // 标题文本颜色
    private final float titleTextSize; // 标题文本大小

    private Button leftButton; // 左侧按钮
    private Button rightButton; // 右侧按钮
    private TextView title; // 标题

    private LayoutParams leftParams; // 左侧按钮布局参数
    private LayoutParams rightParams; // 右侧按钮布局参数
    private LayoutParams titleParams; // 标题布局参数

    private TopBarClickListener topBarClickListener; // 回调接口

    public void setTopBarClickListener(TopBarClickListener topBarClickListener) {
        this.topBarClickListener = topBarClickListener;
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取 attrs 文件中定义的 declare-styleable 的所有属性值存到 TypedArray 中
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        leftText = ta.getString(R.styleable.TopBar_leftText);
        leftColor = ta.getColor(R.styleable.TopBar_leftColor, 0);
        leftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        rightText = ta.getString(R.styleable.TopBar_rightText);
        rightColor = ta.getColor(R.styleable.TopBar_rightColor, 0);
        rightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        titleText = ta.getString(R.styleable.TopBar_titleText);
        titleColor = ta.getColor(R.styleable.TopBar_titleColor, 0);
        titleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 20);
        // 获取完所有属性值后,需要调用 recycle 来完成资源的回收 很重要..
        ta.recycle();

        // 初始化控件, 设置参数, 设置布局, 添加到 viewGroup
        leftButton = new Button(context);
        leftButton.setText(leftText);
        leftButton.setTextColor(leftColor);
        leftButton.setBackground(leftBackground);
        // 布局参数
        leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_START, TRUE);
        addView(leftButton, leftParams);

        rightButton = new Button(context);
        rightButton.setText(rightText);
        rightButton.setTextColor(rightColor);
        rightButton.setBackground(rightBackground);

        rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_END, TRUE);
        addView(rightButton, rightParams);

        title = new TextView(context);
        title.setText(titleText);
        title.setTextColor(titleColor);
        title.setTextSize(titleTextSize);
        title.setGravity(Gravity.CENTER);

        titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(title, titleParams);

        /**
         * 左侧按钮点击事件
         */
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (topBarClickListener != null) {
                    topBarClickListener.leftClick();
                }
            }
        });

        /**
         * 右侧按钮点击事件
         */
        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (topBarClickListener != null) {
                    topBarClickListener.rightClick();
                }
            }
        });
    }

    /**
     * 设置按钮是否显示
     * @param button 按钮ID
     * @param visible 是否显示
     */
    public void setButtonVisable(int button, boolean visible) {
        if (button == LEFT_BUTTON) {
            if (visible) {
                leftButton.setVisibility(VISIBLE);
            } else {
                leftButton.setVisibility(GONE);
            }
        } else if(button == RIGHT_BUTTON) {
            if (visible) {
                rightButton.setVisibility(VISIBLE);
            } else {
                rightButton.setVisibility(GONE);
            }
        }
    }

    /**
     * 设置回调接口,
     */
    interface TopBarClickListener {

        void leftClick();

        void rightClick();
    }
}