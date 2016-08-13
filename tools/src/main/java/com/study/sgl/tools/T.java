package com.study.sgl.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast 工具类
 * 本类保持封装单体 Toast ,直接改变当前 Toast 内容,不会重复显示.
 *
 * @author Song.gl
 * @version 2016 06 10 12:21
 */
public class T {

    private T() { }

    private static Toast toast;

    public static boolean isShow = Constants.IS_SHOW_TOAST;

    /**
     * 短时间 Toast
     * @param context context
     * @param message message
     */
    public static void s(Context context, CharSequence message) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            } else {
                toast.setText(message);
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.show();
        }
    }

    /**
     * 长时间 Toast
     * @param context context
     * @param message message
     */
    public static void l(Context context, CharSequence message) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            } else {
                toast.setText(message);
                toast.setDuration(Toast.LENGTH_LONG);
            }
            toast.show();
        }
    }
}
