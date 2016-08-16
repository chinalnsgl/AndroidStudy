package com.example.sgl.dagger2databindingmvp.server;

import com.example.sgl.dagger2databindingmvp.data.remote.GirlResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Retrofit2 调用接口,用于网络请求
 *
 * @author Song.gl
 * @version 2016 06 11 9:50
 */
public interface NetService {

    @GET("/api/data/福利/{pageSize}/{pageNo}")
    Observable<GirlResponse> getGirls(@Path("pageSize") int pageSize,@Path("pageNo") int pageNo);
}
