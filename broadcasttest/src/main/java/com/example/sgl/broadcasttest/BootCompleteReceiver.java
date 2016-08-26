package com.example.sgl.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.study.sgl.tools.util.L;
import com.study.sgl.tools.util.T;

public class BootCompleteReceiver extends BroadcastReceiver {
    public BootCompleteReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        T.s(context, "Boot complete!");
        L.d("开机启动");
        if (intent.getAction().equals("android.intent.action.CUSTOMRECEIVER")) {
            T.s(context, "自定义广播");
        } else {
            T.s(context, "系统广播");
        }
    }
}
