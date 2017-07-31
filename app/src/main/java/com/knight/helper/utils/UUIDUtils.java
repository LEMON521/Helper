package com.knight.helper.utils;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by lemon on 2017/7/31.
 */

public class UUIDUtils implements Serializable {

    public static String getUUID() {

        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
