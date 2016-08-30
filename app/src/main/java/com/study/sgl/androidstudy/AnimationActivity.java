package com.study.sgl.androidstudy;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv;
    private AlphaAnimation aa;
    private TranslateAnimation ta;
    private ScaleAnimation sa;
    private RotateAnimation ra;
    private AnimationSet as;
    private ObjectAnimator oa; // 属性动画

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        iv = (ImageView) findViewById(R.id.image);

        findViewById(R.id.alpha).setOnClickListener(this);
        findViewById(R.id.translate).setOnClickListener(this);
        findViewById(R.id.scale).setOnClickListener(this);
        findViewById(R.id.rotate).setOnClickListener(this);
        findViewById(R.id.animationset).setOnClickListener(this);
        findViewById(R.id.propertyalpha).setOnClickListener(this);
        findViewById(R.id.propertytranslate).setOnClickListener(this);
        findViewById(R.id.propertyscale).setOnClickListener(this);
        findViewById(R.id.propertyrotate).setOnClickListener(this);
        findViewById(R.id.propertyanimationset).setOnClickListener(this);


        // 视图动画
        // 1. 透明度动画
        aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000); // 设置动画持续时间
        // 2. 位移动画
        ta = new TranslateAnimation(0, 200, 0, 300); // x起点,x终点,y起点,y终点
        ta.setDuration(1000);
        // 3. 缩放动画
        sa = new ScaleAnimation(0, 2, 0, 2, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        sa.setDuration(1000);
        // 4. 旋转动画
        ra = new RotateAnimation(0, 360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(1000);
        // 5. 动画集合
        as = new AnimationSet(true);
        //as.addAnimation(aa);
        as.addAnimation(ta);
        //as.addAnimation(sa);
        as.addAnimation(ra);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alpha:
                iv.startAnimation(aa);
                break;
            case R.id.translate:
                iv.startAnimation(ta);
                break;
            case R.id.scale:
                iv.startAnimation(sa);
                break;
            case R.id.rotate:
                iv.startAnimation(ra);
                break;
            case R.id.animationset:
                iv.startAnimation(as);
                break;
            case R.id.propertyalpha:
                oa = ObjectAnimator.ofFloat(iv, "alpha", 0,1);
                oa.setDuration(2000);
                oa.start();
                break;
            case R.id.propertytranslate:
                oa = ObjectAnimator.ofFloat(iv, "translationX", 300);
                oa.setDuration(2000);
                oa.start();
                break;
            case R.id.propertyscale:
                /*WrapperView wv = new WrapperView(iv);
                oa = ObjectAnimator.ofInt(wv, "width", 20);
                oa.setDuration(2000);
                oa.start();*/

                oa = ObjectAnimator.ofFloat(iv, "scale", 0, 1);
                oa.setDuration(2000);
                oa.start();
                break;
            case R.id.propertyrotate:
                oa = ObjectAnimator.ofFloat(iv, "rotate", 180);
                oa.setDuration(2000);
                oa.start();
                break;
            case R.id.propertyanimationset:
                //iv.startAnimation(as);
                break;
        }
    }

    class WrapperView {
        private View v;

        public WrapperView(View v) {
            this.v = v;
        }

        public int getWidth() {
            return v.getLayoutParams().width;
        }

        public void setWidth(int widht) {
            v.getLayoutParams().width = widht;
            v.requestLayout();
        }
    }
}
