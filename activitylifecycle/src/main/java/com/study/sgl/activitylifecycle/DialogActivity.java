package com.study.sgl.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;

/**
 * 对话框 Activity 在AndroidManifest文件中设置了主题,@android:style/Theme.Dialog,系统自带的对话框主题,
 * 所以此Activity不会完全覆盖MainActivity,即生命周期方法不会执行onStop.
 * 注:此主题Actovotu为能继承 AppCompatActivity ,否则报错
 */
public class DialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }
}
