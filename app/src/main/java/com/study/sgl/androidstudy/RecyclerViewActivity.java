package com.study.sgl.androidstudy;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.study.sgl.androidstudy.data.local.BeautifulGirl;
import com.study.sgl.androidstudy.data.remote.GirlResponse;
import com.study.sgl.tools.CommonAdapter;
import com.study.sgl.tools.HttpCallBackListener;
import com.study.sgl.tools.HttpUtil;
import com.study.sgl.tools.ViewHolder;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    private final static int showResponse = 0;
    private CommonAdapter<BeautifulGirl> adapter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case showResponse:
                    GirlResponse result = new Gson().fromJson(msg.obj.toString(), GirlResponse.class);
                    adapter.replaceData(result.getResults());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        requestData();
        adapter = new CommonAdapter<BeautifulGirl>(R.layout.item,this,new ArrayList<BeautifulGirl>()) {
            @Override
            protected void convert(ViewHolder holder, BeautifulGirl beautifulGirl) {
                holder.setText(R.id.title, beautifulGirl.getDesc());
                holder.setImage(R.id.image,beautifulGirl.getUrl());
            }
        };
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void requestData() {
        HttpUtil.sendHttpRequest("http://gank.io/api/data/福利/10/1", new HttpCallBackListener() {
            @Override
            public void onFinish(String response) {
                Message message = new Message();
                message.what = showResponse;
                message.obj = response;
                handler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }
}
