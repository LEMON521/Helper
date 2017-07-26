package com.knight.helper.bean;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/23.
 */

/**
 * 向服务器推送的bean
 */
public class ChatPushBean implements Serializable{


    /**
     * 您申请到的本接口专用的APPKEY
     */
    public String key = "";
    /**
     * 要发送给机器人的内容，不要超过30个字符
     */
    public String info = "";
    /**
     * 返回的数据的格式，json或xml，默认为json
     */
    public String dtype = "";
    /**
     * 地点，如北京中关村
     */
    public String loc = "";
    /**
     * 1~32位，此userid针对您自己的每一个用户，用于上下文的关联
     */
    public String userid = "";



}
