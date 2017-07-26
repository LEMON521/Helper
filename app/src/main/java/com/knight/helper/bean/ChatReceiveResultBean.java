package com.knight.helper.bean;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/23.
 */

public class ChatReceiveResultBean implements Serializable{


    /**
     * 根据code值的不同，返回的字段有所不同
     */
    public String code = "";

    /**
     * 机器人返回内容
     */
    public String text = "";

}
