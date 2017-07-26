package com.knight.helper.bean;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/23.
 */

/**
 * 聊天的详细数据
 */
public class ChatBean implements Serializable {
    public String text = "";
    public String time = "";
    public int type = -1;

    /**
     * 清空数据
     */
    public void clear(){

        this.text = "";
        this.time = "";
        this.type = -1;

    }
}
