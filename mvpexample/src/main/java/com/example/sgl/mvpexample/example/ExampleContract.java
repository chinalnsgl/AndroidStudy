package com.example.sgl.mvpexample.example;

import com.study.sgl.tools.base.BasePresenter;
import com.study.sgl.tools.base.BaseView;

import java.util.List;

/**
 * example 模块协议
 *
 * @author Song.gl
 * @version 2016 08 18 14:34
 */
public interface ExampleContract {

    /**
     * P 层接口
     */
    interface Presenter extends BasePresenter {

        /**
         * 发送请求获取数据集合 List<M>
         *     1. 调用 V 层接口隐藏 recyclerView 并显示进度条动画 loading()
         *     2. 请求网络获取数据 略
         *     3.1 加载成功调用 V 层接口设置更新列表 refresh()
         *     3.2 加载失败调用 V 层接口设置失败页面 error()
         *     3.1和3.2需要运行在 UI 线程
         */
        void sendRequest();
    }

    /**
     * V 层接口
     */
    interface View extends BaseView<Presenter> {

        /**
         * 隐藏 recyclerView 并显示加载动画
         */
        void loading();

        /**
         * 为 adapter 设置数据集合, 并显示 recyclerView 提示加载完成隐藏加载动画
         */
        void refresh(List<M> data);

        /**
         * 显示加载失败错误提示信息, 显示刷新重试按钮
         */
        void error();
    }

}
