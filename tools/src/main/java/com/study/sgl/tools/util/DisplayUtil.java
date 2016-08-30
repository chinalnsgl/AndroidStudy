package com.study.sgl.tools.util;

import android.content.Context;

/**
 * 像素转换工具类
 *
 * @author Song.gl
 * @version 2016 08 30 21:32
 */
public class DisplayUtil {

    /**
     * 将 px 转换成 dp , 保持尺寸大小不变
     *
     * @param pxValue px 值
     * @return dip 值 返回值加上0.5意为向上取整 eg: 1.4 + 0.5 四舍五入取值为 2
     */
    public static int pxToDip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * dp 转 px
     */
    public static int dipToPx(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px 转 sp
     */
    public static int pxToSp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp 转 px
     */
    public static int spToPx(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

}