package com.example.sgl.dagger2databindingmvp.listmodule;

import com.example.sgl.dagger2databindingmvp.data.remote.GirlResponse;
import com.example.sgl.dagger2databindingmvp.server.NetService;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * P 层实现类
 *
 * @author Song.gl
 * @version 2016 08 16 17:11
 */
public class ListPresenter implements ListContract.ListPresenter {

    private ListContract.ListView listView;
    private NetService netService;

    /**
     * 构造方法
     * 加 @Inject 注解将本实例提供到对象图中,供 dagger2 实现自动注入
     * 此注解方法所需要参数必须在对象图中可见
     */
    @Inject
    public ListPresenter(ListContract.ListView listView, NetService netService) {
        this.listView = listView;
        this.netService = netService;
    }

    /**
     * 方法用于自动实例本类提供到 Fragment 实现的 V 层中.
     */
    @Inject
    void setupPresenter() {
        listView.setPresenter(this);
    }

    @Override
    public void loadMore(int pageNo) {
        netService.getGirls(5, pageNo).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GirlResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GirlResponse girlResponse) {
                        listView.moreList(girlResponse.getResults());
                    }
                });
    }

    @Override
    public void loadData() {
        netService.getGirls(5, 1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GirlResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GirlResponse girlResponse) {
                        listView.showList(girlResponse.getResults());
                    }
                });
    }
}