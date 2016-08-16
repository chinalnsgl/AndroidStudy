package com.example.sgl.dagger2databindingmvp.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 数据绑定通用的 recyclerView.ViewHolder
 *
 * @author Song.gl
 * @version 2016 06 25 14:00
 */
public class DataBindViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding viewDataBinding; // 数据绑定对象

    /**
     * 构造
     * @param viewDataBinding 数据绑定对象
     * @param view 视图对象
     */
    public DataBindViewHolder(ViewDataBinding viewDataBinding, View view) {
        super(view);
        this.viewDataBinding = viewDataBinding;
    }

    public ViewDataBinding getViewDataBinding() {
        return viewDataBinding;
    }
}