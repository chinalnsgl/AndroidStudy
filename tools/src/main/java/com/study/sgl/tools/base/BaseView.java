package com.study.sgl.tools.base;

/**
 * View 基类
 *
 * @author Song.gl
 * @version 2016 06 09 23:17
 */
public interface BaseView<T extends BasePresenter> {

    /**
     * 为 View 设置 Presenter
     */
    void setPresenter(T presenter);
}
