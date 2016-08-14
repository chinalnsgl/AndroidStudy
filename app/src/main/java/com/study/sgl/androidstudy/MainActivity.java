package com.study.sgl.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.study.sgl.tools.BaseActivity;
import com.study.sgl.tools.L;
import com.study.sgl.tools.T;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.startOtherActivity).setOnClickListener(this);
        findViewById(R.id.startOtherActivity2).setOnClickListener(this);
        findViewById(R.id.startOtherActivity3).setOnClickListener(this);
        findViewById(R.id.list).setOnClickListener(this);
    }

    /**
     * 获取 Activity 的返回值,需要使用 startActivityForResult 方式启动
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    L.i(data.getStringExtra(SecondActivity.RETURN_DATA));
                }
                break;
            default:
        }
    }

    /**
     * 创建菜单,返回true才可以显示菜单,返回false将不会显示菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    /**
     * 菜单项目选中事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // item.getItemId() 返回选中菜单项的ID
        switch (item.getItemId()) {
            case R.id.add_item:
                T.s(this,"You clicked Add");
                break;
            case R.id.remove_item:
                T.s(this, "You clicked Remove");
                break;
            default:
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.startOtherActivity:
                // 显示启动另一个Activity
                intent = new Intent(MainActivity.this, SecondActivity.class);
                // 传递数据
                intent.putExtra(SecondActivity.EXTRA_DATA, "Hello SecondActivity!");
                // 正常启动Activity
                startActivity(intent);
                break;
            case R.id.startOtherActivity2:
                // 隐式启动另一个Activity
                intent = new Intent("com.example.ACTION_START");
                startActivity(intent);
                break;
            case R.id.startOtherActivity3:
                // 获取返回值的启动的方式
                intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.list:
                intent = new Intent(this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
