package com.study.sgl.tools;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 通用的ViewHolder
 *
 * @author Song.gl
 * @version 2016 08 14 13:11
 */
public class ViewHolder extends RecyclerView.ViewHolder{

    private SparseArray<View> views; // 控件集合
    private View currentView; // 当前视图

    /**
     * 构造函数，初始化属性
     */
    public ViewHolder(View itemView) {
        super(itemView);
        this.currentView = itemView;
        this.views = new SparseArray<>();
    }

    /**
     * 实例方法
     * @param layoutId 布局ID
     * @param context 上下文
     * @param parent 父容器
     */
    public static ViewHolder getInstants(int layoutId, Context context, ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    /**
     * 通过控件ID 获取控件
     * @param viewId 控件ID
     * @param <T> 控件
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = currentView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置 TextView 内容
     * @param viewId 控件ID
     * @param text 内容
     */
    public ViewHolder setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    public ViewHolder setImage(int viewId, int resId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}