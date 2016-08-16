package com.example.sgl.dagger2databindingmvp.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Databind Adapter 使用数据绑定的 recyclerView 的适配器
 *
 * @author Song.gl
 * @version 2016 06 12 16:51
 */
public abstract class DataBindAdapter<T> extends RecyclerView.Adapter<DataBindViewHolder> {

    protected List<T> data; // 数据
    protected int layoutId; // 布局ID
    protected int variableId; // 变量ID
    protected OnItemClickListener<T> listener; // Item 点吉监听
    protected MultiItemTypeSupport<T> multiItemTypeSupport; // 多类型支持

    /**
     * 构造
     */
    public DataBindAdapter(List<T> data, int layoutId, int variableId) {
        this(data, layoutId, variableId, null, null);
    }

    public DataBindAdapter(List<T> data, int layoutId, int variableId, OnItemClickListener<T> listener) {
        this(data, layoutId, variableId, listener, null);
    }

    public DataBindAdapter(List<T> data, int layoutId, int variableId, MultiItemTypeSupport<T> multiItemTypeSupport) {
        this(data, layoutId, variableId, null, multiItemTypeSupport);
    }

    /**
     * 构造
     * @param data 数据集合
     * @param layoutId 布局ID
     * @param variableId 数据绑定变量
     * @param listener 点击事件回调接口
     * @param multiItemTypeSupport 多布局类型支持接口
     */
    public DataBindAdapter(List<T> data, int layoutId, int variableId, OnItemClickListener<T> listener, MultiItemTypeSupport<T> multiItemTypeSupport) {
        this.data = data;
        this.layoutId = layoutId;
        this.variableId = variableId;
        this.listener = listener;
        this.multiItemTypeSupport = multiItemTypeSupport;
    }

    /**
     * 设置数据
     * @param data data
     */
    public void replaceData(List<T> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    /**
     * 追加数据
     * @param data data
     */
    public void insertData(List<T> data) {
        this.data.addAll(data);
        this.notifyDataSetChanged();
    }

    /**
     * 设置 item listener
     * @param listener listener
     */
    public void setListener(OnItemClickListener<T> listener) {
        this.listener = listener;
    }

    /**
     * 设置 多类型布局支持
     * @param multiItemTypeSupport multiItemTypeSupport
     */
    public void setMultiItemTypeSupport(MultiItemTypeSupport<T> multiItemTypeSupport) {
        this.multiItemTypeSupport = multiItemTypeSupport;
    }

    /**
     * 获取条目布局类型,返回值到 onCreateViewHolder 方法中第二个参数
     */
    @Override
    public int getItemViewType(int position) {
        if (multiItemTypeSupport != null) {
            return multiItemTypeSupport.getItemViewType(position, data.get(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public DataBindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 判断是否有多布局类型
        ViewDataBinding binding;
        if (multiItemTypeSupport != null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), multiItemTypeSupport.getLayoutId(viewType), parent, false);
        } else {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId, parent, false);
        }
        return new DataBindViewHolder(binding, binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final DataBindViewHolder holder, final int position) {
        // 获取数据设置到变量中
        holder.getViewDataBinding().setVariable(variableId, data.get(position));
        // 立即执行更新
        holder.getViewDataBinding().executePendingBindings();
        // 点击事件
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(data.get(holder.getAdapterPosition()));
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return listener.onItemLongClick(holder.itemView, holder.getAdapterPosition());
                }
            });
        }
        // 绑定具体细节实现
        convert(holder.getViewDataBinding(), data.get(position));
    }

    /**
     * 抽象方法，需要使用者实现具体的绑定细节
     * @param viewDataBinding 数据绑定对象
     * @param t 数据对象
     */
    protected abstract void convert(ViewDataBinding viewDataBinding, T t);


    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * item 点击事件回调接口
     */
    public interface OnItemClickListener<T> {

        void onItemClick(T t);
        boolean onItemLongClick(View view, int position);
    }

    /**
     * 多布局类型处理接口
     */
    public interface MultiItemTypeSupport<T> {
        /**
         * 根据Bean获取项目类型
         */
        int getItemViewType(int position, T t);

        /**
         * 根据项目类型获取布局ID
         */
        int getLayoutId(int itemType);
    }

}