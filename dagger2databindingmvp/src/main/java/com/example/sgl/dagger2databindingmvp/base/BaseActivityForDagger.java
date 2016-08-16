package com.example.sgl.dagger2databindingmvp.base;

import android.os.Bundle;

import com.example.sgl.dagger2databindingmvp.dagger.AppApplication;
import com.example.sgl.dagger2databindingmvp.dagger.AppComponent;
import com.study.sgl.tools.base.BaseActivity;

/**
 * 使用Dagger 的基类
 *
 * @author Song.gl
 * @version 2016 08 16 14:51
 */
public abstract class BaseActivityForDagger extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 调用方法实现 dagger 注入
        setActivityComponent(((AppApplication)getApplication()).getAppComponent());
    }

    /**
     * 注入Activity依赖,重写此方法实现 dagger2 的依赖注入
     * @param appComponent application组件
     */
    protected abstract void setActivityComponent(AppComponent appComponent);
}