package com.example.sgl.dagger2databindingmvp.dagger;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sgl.dagger2databindingmvp.server.NetService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * 全局依赖组件,提供全局单例类实例依赖
 * 其它 ActivityComponent 可以依赖本接口提供的单例对象,
 *
 * @author Song.gl
 * @version 2016 06 09 23:42
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    /**
     * 提供全局 Context 上下文对象
     */
    @Named("application") Context getContext();

    /**
     * 提供 Retrofit2
     */
    Retrofit getRetrofit();

    /**
     * 提供请求类对象
     */
    NetService getNetService();

    /**
     * 提供sharedPreferences
     */
    SharedPreferences getSharedPreferences();
}
