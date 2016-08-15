package com.study.sgl.androidstudy.data.remote;

/**
 * 请求结果基类
 *
 * @author Song.gl
 * @version 2016 08 15 13:10
 */
public abstract class BaseResponse {

    protected boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}