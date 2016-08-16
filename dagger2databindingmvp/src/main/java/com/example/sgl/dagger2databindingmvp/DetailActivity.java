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

    public static String param;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        Intent intent = getIntent();
        BeautifulGirl girl = (BeautifulGirl) intent.getSerializableExtra(param);

        Glide.with(this).load(girl.getUrl()).into(binding.image);

    }

    public static void actionStart(Context context, BeautifulGirl girl) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(param, girl);
        context.startActivity(intent);
    }

    @Override
    protected void setActivityComponent(AppComponent appComponent) {

    }
}
