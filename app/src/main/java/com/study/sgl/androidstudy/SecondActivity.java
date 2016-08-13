package com.study.sgl.androidstudy;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.study.sgl.tools.ActivityCollector;
import com.study.sgl.tools.BaseActivity;
import com.study.sgl.tools.L;

public class SecondActivity extends BaseActivity implements View.OnClickListener {

    public static final String EXTRA_DATA = "extraData";
    public static final String RETURN_DATA = "returnData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String data = getIntent().getStringExtra(EXTRA_DATA);
        if (data != null) {
            ((TextView) findViewById(R.id.content)).setText(data);
            L.i("display text");
        }
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.logout).setOnClickListener(this);
        findViewById(R.id.progress).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                // 设置返回结果
                Intent intent = new Intent();
                intent.putExtra(RETURN_DATA, "回去");
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.logout:
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("确定退出程序吗?")
                        .setCancelable(false)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCollector.finishAllActivity();
                            }
                        }).show();
                break;
            case R.id.progress:
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("提示");
                progressDialog.setMessage("模拟网络请求");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            default:
        }
    }

    /**
     * 处理返回键回传数据,此方法在点击返回键时触发
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(RETURN_DATA, "回去");
        setResult(RESULT_OK, intent);
        finish();
    }
}
