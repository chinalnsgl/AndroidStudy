package com.study.sgl.tools.util;

/**
 * 字符串工具类
 *
 * @author Song.gl
 * @version 2016 08 25 18:54
 */
public class StringUtil {

    private StringUtil() {
    }

    /**
     * 判断字符串是否为null或长度为0
     *
     * @param string 待校验字符串
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(CharSequence string) {
        return string == null || string.length() == 0;
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param string 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isSpace(String string) {
        return string == null || string.trim().length() == 0;
    }

    /**
     * 返回字符串长度
     *
     * @param string 字符串
     * @return null 返回 0，其他返回自身长度
     */
    public static int length(CharSequence string) {
        return string == null ? 0 : string.length();
    }
}