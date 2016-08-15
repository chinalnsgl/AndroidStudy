package com.study.sgl.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.study.sgl.tools.util.L;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        findViewById(R.id.startNormalActivity).setOnClickListener(this);
        findViewById(R.id.startDialogActivity).setOnClickListener(this);
        L.i("onCreate");

        // 读取保存数据
        if (savedInstanceState != null) {
            String tempString = savedInstanceState.getString("key");
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.startNormalActivity:
                intent = new Intent(this, NormalActivity.class);
                startActivity(intent);
                break;
            case R.id.startDialogActivity:
                intent = new Intent(this, DialogActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.i("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.i("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.i("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.i("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.i("onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.i("onRestart");
    }

    /**
     * 此方法会在Activity被系统回收时调用,手动 finish 或者 back 不会触发此方法,用于保存Activity属性
     * 避免系统因内存不足回收后,onCreate重建丢失数据.
     * 需要 将数据保存在 outState 中,在onCreate方法中读取
     * @param outState
     * @param outPersistentState
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("key", "value");
    }
}
