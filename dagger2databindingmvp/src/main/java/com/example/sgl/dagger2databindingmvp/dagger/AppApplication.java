package com.example.sgl.dagger2databindingmvp.dagger;

import android.app.Application;

/**
 * 自定义 Application ,使用 Dagger, 初始化并提供 appComponent 组件
 *
 * @author Song.gl
 * @version 2016 06 09 23:11
 */
public class AppApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
