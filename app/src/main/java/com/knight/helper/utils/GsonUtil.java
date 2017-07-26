package com.knight.helper.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by zkagang on 2016/1/8.
 */
public class GsonUtil {
    private static Gson gson;

    private static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }


    /**
     * 总数据json
     *
     * @param json
     * @return
     */
    public static <T> T getBean(String json, Class<T> classOfT) {
        return getGson().fromJson(json, (Type) classOfT);
    }

}
