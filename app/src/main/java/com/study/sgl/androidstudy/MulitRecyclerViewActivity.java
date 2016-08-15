package com.study.sgl.androidstudy;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.study.sgl.tools.adapter.CommonAdapter;
import com.study.sgl.tools.adapter.ViewHolder;
import com.study.sgl.tools.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MulitRecyclerViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulit_recycler_view);

        // 设置数据
        List<String> data = new ArrayList<>();
        for(int i='a' ;i<='z'; i++) {
            data.add((char)i + "");
            for(int j= 1; j<=9 ; j++) {
                data.add((char)i + "" + j);
            }
        }

        // 类型回调
        CommonAdapter.MultiItemTypeSupport<String> multiItem = new CommonAdapter.MultiItemTypeSupport<String>() {
            @Override
            public int getItemViewType(int position, String s) {
                if (s.length() == 1) {
                    return 0;
                }
                return 1;
            }

            @Override
            public int getLayoutId(int itemType) {
                if (itemType == 0) {
                    return R.layout.background_item;
                }
                return R.layout.simple_item;
            }
        };
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new CommonAdapter<String>(R.layout.activity_mulit_recycler_view, this, data, multiItem){
            @Override
            protected void convert(ViewHolder holder, String s) {
                holder.setText(R.id.tv, s);
            }
        });
    }
}
