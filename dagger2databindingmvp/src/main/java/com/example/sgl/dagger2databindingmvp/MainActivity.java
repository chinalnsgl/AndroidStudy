package com.example.sgl.dagger2databindingmvp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.sgl.dagger2databindingmvp.base.BaseActivityForDagger;
import com.example.sgl.dagger2databindingmvp.dagger.AppComponent;
import com.example.sgl.dagger2databindingmvp.databinding.ActivityMainBinding;
import com.example.sgl.dagger2databindingmvp.listmodule.ListActivity;

public class MainActivity extends BaseActivityForDagger {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 数据绑定,布局文件名去下划线加Binding构成的类名
        // 使用数据绑定工具类设置内容视图
        // binding 对象包含所有布局中的控件, 控件ID为属性名
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // 通过 binding 对象获取布局文件中 id 为 list 的控件,并设置点击事件
        binding.list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toActivity(ListActivity.class);
            }
        });
    }

    @Override
    protected void setActivityComponent(AppComponent appComponent) {
        // 此 Activity 没有业务处理,不需要依赖注入
    }
}
