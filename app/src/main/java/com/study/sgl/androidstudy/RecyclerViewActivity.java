package com.study.sgl.androidstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.study.sgl.tools.CommonAdapter;
import com.study.sgl.tools.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        List<String> datas = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            datas.add(i + "");
        }
        rv.setAdapter(new CommonAdapter<String>(R.layout.item,this,datas) {
            @Override
            protected void convert(ViewHolder holder, String s) {
                holder.setText(R.id.title, s);
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
