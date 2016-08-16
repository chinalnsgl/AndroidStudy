package com.example.sgl.dagger2databindingmvp.listmodule;

import com.example.sgl.dagger2databindingmvp.data.local.BeautifulGirl;
import com.study.sgl.tools.base.BasePresenter;
import com.study.sgl.tools.base.BaseView;

import java.util.List;

/**
 * List 模块 VP 接口
 *
 * @author Song.gl
 * @version 2016 08 16 16:54
 */
public interface ListContract {

    /**
     * P层接口
     */
    interface ListPresenter extends BasePresenter {
        /**
         * 加载数据
         */
        void loadData();

        /**
         * 加载更多
         */
        void loadMore(int pageNo);
    }

    /**
     * V层接口
     */
    interface ListView extends BaseView<ListPresenter> {

        /**
         * 显示数据
         */
        void showList(List<BeautifulGirl> data);

        /**
         * 显示更多
         */
        void moreList(List<BeautifulGirl> data);
    }
}
