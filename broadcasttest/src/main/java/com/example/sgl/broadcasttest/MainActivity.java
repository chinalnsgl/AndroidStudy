package com.example.sgl.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.study.sgl.tools.util.T;

public class MainActivity extends AppCompatActivity {

    private NetWorkChangeReceiver netWorkChangeReceiver;
    private IntentFilter intentFilter; // 意图过滤器
    private LocalBroadcastManager localBroadcastManager; // 本地广播管理器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 实例化本地广播管理器
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        /** 创建 意图过滤器 ,添加 Action 过滤网络连接状态改变
         *  动态注册广播, 必须程序运行后才可接收到广播, 可自由控制注册与注销, 灵活性好一些
         *  广播接收器中不能开启线程,不要执行耗时操作*/
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        /** 设置优先级 作用于有序广播 值越大优先级越高 */
        //intentFilter.setPriority(1000);
        /** 创建 广播接收者 , 注册广播 */
        netWorkChangeReceiver = new NetWorkChangeReceiver();
        registerReceiver(netWorkChangeReceiver, intentFilter);

        /** 注册本地广播接收者 */
        //localBroadcastManager.registerReceiver(netWorkChangeReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /** 需要取消注册的广播 释放资源  很重要!! */
        unregisterReceiver(netWorkChangeReceiver);
        /** 取消注册本地广播 */
        //localBroadcastManager.unregisterReceiver(netWorkChangeReceiver);
    }

    /**
     * 发送广播
     */
    public void sendBroadcast(View view) {
        // 创建发送的广播意图
        Intent intent = new Intent("android.intent.action.CUSTOMRECEIVER");
        // 发送标准广播
        sendBroadcast(intent);
        // 发送有序广播
        sendOrderedBroadcast(intent, null);
        /** 发送本地广播 */
        //localBroadcastManager.sendBroadcast(intent);
    }

    /**
     * 内部类,继承广播接收器,接收网络状态广播
     */
    class NetWorkChangeReceiver extends BroadcastReceiver {

        /**
         * 接收到广播时的处理方法
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            // 获取连接管理器
            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取网络信息
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isAvailable()) {
                T.s(context, "network is available");
            } else {
                T.s(context, "network is unavailable");
            }

            /** 可以截断有序广播 */
            //abortBroadcast();
        }
    }
}
