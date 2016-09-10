package com.example.sgl.dagger2databindingmvp;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.sgl.dagger2databindingmvp.base.BaseActivityForDagger;
import com.example.sgl.dagger2databindingmvp.dagger.AppComponent;
import com.example.sgl.dagger2databindingmvp.data.local.BeautifulGirl;
import com.example.sgl.dagger2databindingmvp.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivityForDagger {

    public static String param; // 参数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*// 隐藏 ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        setContentView(R.layout.activity_detail);
        final ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            finish();
        }
        BeautifulGirl girl = (BeautifulGirl) intent.getSerializableExtra(param);

        Glide.with(this).load(girl.getUrl()).into(binding.image);
        /*Glide.with(this).load(girl.getUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                binding.image.setImageBitmap(resource);
                Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        if (palette != null) {
                            if (getSupportActionBar() != null) {
                                if (palette.getVibrantSwatch() != null) {
                                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(palette.getVibrantSwatch().getRgb()));
                                }
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                getWindow().setStatusBarColor(palette.getLightVibrantColor(getResources().getColor(R.color.colorPrimary)));
                            }
                        }
                    }
                });
                *//*if (getSupportActionBar() != null) {
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(p.getVibrantSwatch().getRgb()));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(p.getVibrantSwatch().getRgb());
                }*//*
            }
        });*/
    }

    /**
     * 提供静态方法实例化本类,有利于合作开发
     * @param context 上下文
     * @param girl 实体类
     */
    public static void actionStart(Context context, BeautifulGirl girl) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(param, girl);
        context.startActivity(intent);
    }

    @Override
    protected void setActivityComponent(AppComponent appComponent) {

    }
}
