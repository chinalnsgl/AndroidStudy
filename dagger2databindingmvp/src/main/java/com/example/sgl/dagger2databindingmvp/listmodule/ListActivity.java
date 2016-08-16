package com.example.sgl.dagger2databindingmvp.listmodule;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sgl.dagger2databindingmvp.BR;
import com.example.sgl.dagger2databindingmvp.R;
import com.example.sgl.dagger2databindingmvp.adapter.DataBindAdapter;
import com.example.sgl.dagger2databindingmvp.base.BaseActivityForDagger;
import com.example.sgl.dagger2databindingmvp.dagger.AppComponent;
import com.example.sgl.dagger2databindingmvp.data.local.BeautifulGirl;
import com.example.sgl.dagger2databindingmvp.databinding.ActivityListBinding;
import com.example.sgl.dagger2databindingmvp.databinding.ItemBinding;
import com.study.sgl.tools.util.L;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ListActivity extends BaseActivityForDagger implements ListContract.ListView {

    // 使用 dagger2 注入 presenter.
    // 需要 presenter 构造方法加 @inject 注解, 或者 ListActivityModule 提供方法实例
    @Inject
    ListPresenter presenter;
    ActivityListBinding binding; // 数据绑定对象
    DataBindAdapter<BeautifulGirl> adapter; // 适配器
    LinearLayoutManager linearLayoutManager; // 布局管理器
    private int pageNo = 1; // 页数
    private boolean isLoadMore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        // 初始化 adapter
        adapter = new DataBindAdapter<BeautifulGirl>(new ArrayList<BeautifulGirl>(),R.layout.item, BR.girl) {
            /**
             * 绑定数据
             * @param viewDataBinding 数据绑定对象
             * @param beautifulGirl 实体类
             */
            @Override
            protected void convert(ViewDataBinding viewDataBinding, BeautifulGirl beautifulGirl) {
                ItemBinding itemBinding = (ItemBinding) viewDataBinding;
                Glide.with(ListActivity.this).load(beautifulGirl.getUrl()).into(itemBinding.image);
            }
        };
        linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置上拉加载
        binding.recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 获取最后显示项目 从0开始
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                // 获取项目总数
                int totalItemCount = linearLayoutManager.getItemCount();
                // 刷新
                if (isLoadMore && lastVisibleItem >= totalItemCount - 2 && dy > 0) {
                    binding.swipeRefresh.setRefreshing(true);
                    isLoadMore = false;
                    presenter.loadMore(++pageNo);
                }
            }
        });

        binding.swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        // 设置下拉刷新
        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            /**
             * 下拉刷新方
             */
            @Override
            public void onRefresh() {
                if (isRestricted()) {
                    L.i(TAG,"ignore!");
                } else {
                    presenter.loadData();
                }
            }
        });
        presenter.loadData();
    }

    /**
     * 实现父类方法,注入 component 组件
     * @param appComponent application组件
     */
    @Override
    protected void setActivityComponent(AppComponent appComponent) {
        // Dagger开头接 component 接口名称,调用静态方法获得组件内部类实例
        // 需要初始化依赖的 component ,这里依赖 appComponent
        // 需要提供 module 的实例. 最后 build 方法完成 component 的初始化
        // 并需要注入到本 Activity
        DaggerListActivityComponent.builder().appComponent(appComponent).listActivityModule(new ListActivityModule(this)).build().inject(this);
    }

    /**
     * Activity直接通过注解注入 presenter
     * 此方法主要用于 fragment 做为 V 层时使用
     * 在 presenter 中提供下面方法完成 Fragment 持有 presenter .
     * 注解 @inject标注的普通方法将会在初始化自动执行
     *          @Inject
     *          void setupPresenter() {
     *              mainView.setPresenter(this);
     *          }
     */
    @Override
    public void setPresenter(ListContract.ListPresenter presenter) {
    }

    public void showList(List<BeautifulGirl> data) {
        adapter.replaceData(data);
        pageNo = 1;
        binding.swipeRefresh.setRefreshing(false);
    }

    @Override
    public void moreList(List<BeautifulGirl> data) {
        adapter.insertData(data);
        isLoadMore = true;
        binding.swipeRefresh.setRefreshing(false);
    }
}
