package com.knight.helper.constant;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/23.
 */

public class AddressURL implements Serializable {

    /**
     * 问答机器人接口
     * <p>
     * 请求示例：http://op.juhe.cn/robot/index?info=你好&key=您申请到的APPKEY
     * <p>
     * 名称 	类型 	必填 	说明
     * <p>
     * key 	string 	是 	您申请到的本接口专用的APPKEY
     * <p>
     * info string 	是 	要发送给机器人的内容，不要超过30个字符
     * <p>
     * dtype string 否 	返回的数据的格式，json或xml，默认为json
     * <p>
     * loc 	string 	否 	地点，如北京中关村
     * <p>
     * userid string 否 	1~32位，此userid针对您自己的每一个用户，用于上下文的
     * <p>
     */
    public static final String URL_ROBOT = "http://op.juhe.cn/robot/index";

    /**
     * 问答机器人接口appKey
     */
    public static final String APPKEY_ROBOT = "8bf05235c5a46de25f1d438c95c4c740";

    /**
     * 问答机器人接口
     * <p>
     * 请求示例：http://op.juhe.cn/robot/index?info=你好&key=您申请到的APPKEY
     */
    public static final String URL_WEATHER_HISTORY = "";
    public static final String APPKEY_WEATHER_HISTORY = "";
}
