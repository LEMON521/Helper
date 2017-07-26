package com.knight.helper.bean;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/23.
 */

/**
 * 机器人聊天的实体类
 */
public class ChatReceiveBean implements Serializable {

    /**
     * 返回码
     */
    public String error_code = "";

    /**
     * 返回说明
     */
    public String reason = "";

    /**
     * 返回详细数据
     */
    public ChatReceiveResultBean result= null;


}
