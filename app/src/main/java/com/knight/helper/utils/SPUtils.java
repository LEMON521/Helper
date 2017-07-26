package com.knight.helper.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/26.
 */

/**
 * <P>SharedPreferences的工具类</P>
 * <p>
 * <P>需要提供 Context , 文件名 , key , value</P>
 */
public class SPUtils implements Serializable {


    /**
     * 设置值
     *
     * @param context    上下文
     * @param configName 文件名
     * @param key        key值
     * @param value      value值 --boolean类型
     */
    public static void setValue(Context context, String configName, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 设置值
     *
     * @param context    上下文
     * @param configName 文件名
     * @param key        key值
     * @param value      value值 --int类型
     */
    public static void setValue(Context context, String configName, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 设置值
     *
     * @param context    上下文
     * @param configName 文件名
     * @param key        key值
     * @param value      value值 --String类型
     */
    public static void setValue(Context context, String configName, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 获取值
     *
     * @param context    上下文
     * @param configName 文件名
     * @param key        key值
     * @return 返回值, String类型
     */
    public static String getValueStr(Context context, String configName, String key) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    /**
     * 获取值
     *
     * @param context    上下文
     * @param configName 文件名
     * @param key        key值
     * @return 返回值, int类型
     */
    public static int getValueInt(Context context, String configName, String key) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, -1);
    }

    /**
     * 获取值
     *
     * @param context    上下文
     * @param configName 文件名
     * @param key        key值
     * @return 返回值, boolean类型
     */
    public static boolean getValueBoolean(Context context, String configName, String key) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }
}
