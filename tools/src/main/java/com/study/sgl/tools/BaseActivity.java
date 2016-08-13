package com.study.sgl.tools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * BaseActivity ,监控当前使用Activity,并使用工具类辅助,可以随时关闭程序
 *
 * @author Song.gl
 * @version 2016 08 13 16:51
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.i("BaseActivity",getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
