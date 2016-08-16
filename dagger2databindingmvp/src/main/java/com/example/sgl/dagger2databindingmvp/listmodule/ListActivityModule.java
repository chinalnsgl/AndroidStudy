package com.example.sgl.dagger2databindingmvp.listmodule;

import com.example.sgl.dagger2databindingmvp.dagger.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * 提供依赖模块, 此方法在 dagger 注入时提供构造方法参数
 * 并对外提供 V 层
 *
 * @author Song.gl
 * @version 2016 08 16 16:58
 */
@Module
public class ListActivityModule {
    private ListContract.ListView view;

    public ListActivityModule(ListContract.ListView view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public ListContract.ListView providerListActivity() {
        return view;
    }
}