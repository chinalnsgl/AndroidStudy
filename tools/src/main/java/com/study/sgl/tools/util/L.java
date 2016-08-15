package com.study.sgl.tools.util;

import android.util.Log;

import com.study.sgl.tools.Constants;

/**
 * 自定义的日志工具类
 *
 * @author Song.gl
 * @version 2016 06 10 1:49
 */
public class L {

    private L() { }

    public static boolean isDebug = Constants.IS_SHOW_LOG;
    private static final String TAG = Constants.DEFAULT_TAG;

    // 下面四个是默认tag的函数
    public static void i(String msg)
    {
        L.i(TAG, msg);
    }

    public static void d(String msg)
    {
        L.d(TAG, msg);
    }

    public static void e(String msg)
    {
        L.e(TAG, msg);
    }

    public static void v(String msg)
    {
        L.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg)
    {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg)
    {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg)
    {
        if (isDebug)
            Log.v(tag, msg);
    }
}
