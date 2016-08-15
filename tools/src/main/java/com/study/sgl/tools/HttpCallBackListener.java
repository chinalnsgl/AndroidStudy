package com.study.sgl.tools;

/**
 * 网络请求回调接口
 *
 * @author Song.gl
 * @version 2016 08 15 14:33
 */
public interface HttpCallBackListener {

    void onFinish(String response);

    void onError(Exception e);
}
