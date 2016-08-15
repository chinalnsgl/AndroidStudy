package com.study.sgl.tools;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * RecyclerView 通用适配器
 *
 * @author Song.gl
 * @version 2016 08 14 13:31
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    protected Context context; // 上下文
    protected int layoutId; // 布局ID
    protected List<T> datas; // 数据集合

    /**
     * 构造，初始化属性
     */
    public CommonAdapter(int layoutId,Context context,List<T> datas) {
        this(layoutId, context, datas, null);
    }

    public CommonAdapter(int layoutId, Context context, List<T> datas, MultiItemTypeSupport<T> multiItemTypeSupport) {
        this.context = context;
        this.layoutId = layoutId;
        this.datas = datas;
        this.multiItemTypeSupport = multiItemTypeSupport;
    }

    /**
     * ItemView 点击事件回调接口
     */
    public interface OnItemClickListener<T> {
        void onItemClick(T t);
    }

    /**
     * 多布局类型处理接口
     */
    public interface MultiItemTypeSupport<T>{
        /**
         * 根据Bean获取项目类型
         */
        int getItemViewType(int position, T t);

        /**
         * 根据项目类型获取布局ID
         */
        int getLayoutId(int itemType);
    }

    protected OnItemClickListener<T> listener;

    /**
     * 设置 item listener
     */
    public void setListener(OnItemClickListener<T> listener) {
        this.listener = listener;
    }

    protected MultiItemTypeSupport<T> multiItemTypeSupport;

    /**
     * 设置 multiItemTypeSupport
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
            return multiItemTypeSupport.getItemViewType(position, datas.get(position));
        }
        return super.getItemViewType(position);
    }

    /**
     * 创建 ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (multiItemTypeSupport != null) {
            return ViewHolder.getInstants(multiItemTypeSupport.getLayoutId(viewType), context, parent);
        } else {
            return ViewHolder.getInstants(layoutId,context,parent);
        }
    }

    /**
     * 绑定控件
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(datas.get(holder.getAdapterPosition()));
                }
            });
        }
        convert(holder, datas.get(position));
    }

    /**
     * 抽象方法，需要使用者实现具体的绑定细节
     * @param holder holder对象
     * @param t 数据对象
     */
    protected abstract void convert(ViewHolder holder, T t);

    /**
     * 数据数量
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 设置数据
     * @param datas data
     */
    public void replaceData(List<T> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    /**
     * 追加数据
     * @param datas data
     */
    public void insertData(List<T> datas) {
        this.datas.addAll(datas);
        this.notifyDataSetChanged();
    }
}