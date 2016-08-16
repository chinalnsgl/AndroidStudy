package com.example.sgl.dagger2databindingmvp.data.remote;


import com.example.sgl.dagger2databindingmvp.data.local.BeautifulGirl;

import java.util.ArrayList;
import java.util.List;

/**
 * 美女请求结果
 *
 * @author Song.gl
 * @version 2016 08 15 13:16
 */
public class GirlResponse extends BaseResponse {

    private List<BeautifulGirl> results = new ArrayList<>();

    public List<BeautifulGirl> getResults() {
        return results;
    }

    public void setResults(List<BeautifulGirl> results) {
        this.results = results;
    }
}