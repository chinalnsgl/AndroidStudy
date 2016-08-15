package com.study.sgl.tools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * 通用的ViewHolder
 *
 * @author Song.gl
 * @version 2016 08 14 13:11
 */
@SuppressWarnings("unused")
public class ViewHolder extends RecyclerView.ViewHolder{

    private SparseArray<View> views; // 控件集合
    private View currentView; // 当前视图
    private Context context; // 上下文

    /**
     * 构造函数，初始化属性
     */
    public ViewHolder(View itemView, Context context) {
        super(itemView);
        this.currentView = itemView;
        this.context = context;
        this.views = new SparseArray<>();
    }

    /**
     * 实例方法
     * @param layoutId 布局ID
     * @param context 上下文
     * @param parent 父容器
     */
    public static ViewHolder getInstants(int layoutId, Context context, ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false),context);
    }

    /**
     * 通过控件ID 获取控件
     * @param viewId 控件ID
     * @param <V> 控件
     */
    public <V extends View> V getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = currentView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (V) view;
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

    /**
     * 设置图片
     * 以下 setXXX 方法可随时添加,方便调用者处理绑定控件.
     */
    public ViewHolder setImage(int viewId, int resId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    /**
     * 设置图片
     * 使用 Glide 请求网络图片
     */
    public ViewHolder setImage(int viewId, String url) {
        ImageView imageView = getView(viewId);
        Glide.with(context)
                .load(url)// 加载图片
                .crossFade()// 淡出效果
                .diskCacheStrategy(DiskCacheStrategy.ALL)// 缓存
                .into(imageView);
        return this;
    }

    /**
     * 设置控件点击事件
     */
    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}