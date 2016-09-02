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
    Observable<GirlResponse> getGirls(@Path("pageSize") int pageSize, @Path("pageNo") int pageNo);
    /******

     搜索 API
     http://gank.io/api/search/query/listview/category/Android/count/10/page/1
     注：  category 后面可接受参数 all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     count 最大 50

     获取某几日干货网站数据:
     http://gank.io/api/history/content/2/1
     注： 2 代表 2 个数据，1 代表：取第一页数据

     获取特定日期网站数据:
     http://gank.io/api/history/content/day/2016/05/11

     获取发过干货日期接口:
     http://gank.io/api/day/history 方式 GET

     支持提交干货到审核区:
     https://gank.io/api/add2gank 方式: POST
     字段	描述	备注
     url	想要提交的网页地址
     desc	对干货内容的描述	单独的文字描述
     who	提交者 ID	干货提交者的网络 ID
     type	干货类型	可选参数: Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     debug	当前提交为测试数据	如果想要测试数据是否合法, 请设置 debug 为 true! 可选参数: true | false

     分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     请求个数： 数字，大于0
     第几页：数字，大于0
     例：
     http://gank.io/api/data/Android/10/1
     http://gank.io/api/data/福利/10/1
     http://gank.io/api/data/iOS/20/2
     http://gank.io/api/data/all/20/2

     每日数据： http://gank.io/api/day/年/月/日
     例： http://gank.io/api/day/2015/08/06

     随机数据：http://gank.io/api/random/data/分类/个数
     数据类型：福利 | Android | iOS | 休息视频 | 拓展资源 | 前端
     个数： 数字，大于0
     例： http://gank.io/api/random/data/Android/20

     *****/
}
