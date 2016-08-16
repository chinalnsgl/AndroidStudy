package com.example.sgl.dagger2databindingmvp.listmodule;

import com.example.sgl.dagger2databindingmvp.dagger.ActivityScope;
import com.example.sgl.dagger2databindingmvp.dagger.AppComponent;

import dagger.Component;

/**
 * Activity 组件, 依赖于全局 AppComponent
 * 需要 ListActivityModule
 *
 * @author Song.gl
 * @version 2016 08 16 17:00
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ListActivityModule.class)
public interface ListActivityComponent {
    /**
     * 方法用于实现组件注入到 Activity 中
     */
    void inject(ListActivity listActivity);
}
