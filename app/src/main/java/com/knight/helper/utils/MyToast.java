package com.knight.helper.utils;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/25.
 */

public  class MyToast implements Serializable {

    /**
     * 简单的Toast 短时
     *
     * @param context
     * @param showStr
     */
    public static void showShort(Context context,String showStr){
        Toast.makeText(context,showStr,Toast.LENGTH_SHORT).show();
    }

    /**
     * 简单的Toast 长时
     *
     * @param context
     * @param showStr
     */
    public static void showLong(Context context,String showStr){
        Toast.makeText(context,showStr,Toast.LENGTH_LONG).show();
    }

}
