package com.study.sgl.tools.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.study.sgl.tools.util.L;

/**
 * BaseActivity ,监控当前使用Activity,并使用工具类辅助,可以随时关闭程序
 *
 * @author Song.gl
 * @version 2016 08 13 16:51
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG; // 默认类名为 TAG

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.i("BaseActivity",getClass().getSimpleName());
        ActivityCollector.addActivity(this);
        TAG = getClass().getSimpleName();
        // 竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    /**
     * 启动 Activity
     * @param tarActivity 目标Activity
     */
    protected void toActivity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(this, tarActivity);
        startActivity(intent);
    }
}
