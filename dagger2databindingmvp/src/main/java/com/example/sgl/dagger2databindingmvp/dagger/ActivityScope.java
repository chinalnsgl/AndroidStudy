package com.example.sgl.dagger2databindingmvp.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * dagger2 Activity 作用域注解
 * 此注解标记注入实例生命周期与 Activity 相同
 *
 * @author Song.gl
 * @version 2016 06 10 0:31
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
