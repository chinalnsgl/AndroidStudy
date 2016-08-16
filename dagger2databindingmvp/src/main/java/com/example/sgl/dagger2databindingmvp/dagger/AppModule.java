package com.example.sgl.dagger2databindingmvp.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.sgl.dagger2databindingmvp.constants.Constants;
import com.example.sgl.dagger2databindingmvp.server.NetService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 全局实例对象工厂模块
 *
 * @author Song.gl
 * @version 2016 06 09 23:23
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    /**
     * 提供全局 Context
     * @return Context
     */
    @Provides
    @Singleton
    @Named("application")
    public Context providerContext() {
        return context;
    }

    /**
     * 提供 Gson
     * @return Gson
     */
    @Provides
    @Singleton
    public Gson providerGson() {
        //Jun 23, 2016 1:32:14 PM
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                //.setDateFormat("MMM dd, yyyy hh:mm:ss a")
                .setPrettyPrinting().create();
    }

    /**
     * 提供 OkHttpClient
     * 添加缓存,日志拦截器,超时时间设置,cookie 自动化管理
     * @param context @Named("application") Context
     * @return OkHttpClient
     */
    @Provides
    @Singleton
    public OkHttpClient providerOkHttpClient(@Named("application") Context context) {
        return new OkHttpClient.Builder()
                .cache(new Cache(context.getCacheDir(), Constants.CACHE_SIZE))
                //.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url, cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url);
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();
    }

    /**
     * 提供 retrofit
     * @param gson Gson
     * @param okHttpClient OkHttpClient
     * @return Retrofit
     */
    @Provides
    @Singleton
    public Retrofit providerRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    /**
     * 提供 网络请求
     * @param retrofit retrofit
     * @return NetService
     */
    @Provides
    @Singleton
    public NetService providerNetService(Retrofit retrofit) {
        return retrofit.create(NetService.class);
    }

    /**
     * 提供 SharedPreferences
     * @param context context
     * @return SharedPreferences
     */
    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(@Named("application") Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}