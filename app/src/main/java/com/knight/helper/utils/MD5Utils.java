package com.knight.helper.utils;

import org.xutils.common.util.MD5;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/31.
 */

public class MD5Utils implements Serializable {
    public static String getMD5(String string) {
        return MD5.md5(string);
    }

    public static String getMD5Salt(String name, String password) {
        return MD5.md5(name + password);
    }
}
