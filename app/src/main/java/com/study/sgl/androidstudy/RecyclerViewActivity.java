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

    private final static int SHOW_RESPONSE = 0;
    private CommonAdapter<BeautifulGirl> adapter;

    /**
     * 消息接收处理,执行在主线程
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
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
        // 请求数据
        requestData();

        // 配置 adapter
        adapter = new CommonAdapter<BeautifulGirl>(R.layout.item,this,new ArrayList<BeautifulGirl>()) {
            /**
             * 将数据绑定到布局
             * @param holder holder对象
             * @param beautifulGirl 数据对象
             */
            @Override
            protected void convert(ViewHolder holder, BeautifulGirl beautifulGirl) {
                holder.setText(R.id.title, beautifulGirl.getDesc());
                holder.setImage(R.id.image,beautifulGirl.getUrl());
            }
        };

        // 配置 RecyclerView
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    /**
     * 请求网络数据
     */
    private void requestData() {
        HttpUtil.sendHttpRequest("http://gank.io/api/data/福利/10/1", new HttpCallBackListener() {
            /**
             * 请求成功回调
             */
            @Override
            public void onFinish(String response) {
                Message message = new Message();
                message.what = SHOW_RESPONSE;
                message.obj = response;
                handler.sendMessage(message);
            }

            /**
             * 请求异常回调
             */
            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }
}
