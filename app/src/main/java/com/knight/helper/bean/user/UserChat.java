package com.knight.helper.bean.user;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * <P>用户聊天数据表</P>
 * <p>
 * Created by lemon on 2017/8/1.
 */

@Table(name = "UserChat")
public class UserChat implements Serializable {

    /**
     * 默认构造方法必须有,如果没有,这张表是创建不成功的
     */
    public UserChat() {

    }

    /**
     * uid 32位
     */
    @Column(name = "id", isId = true, autoGen = true, property = "NOT NULL")
    private long id;

    /**
     * uid 32位
     */
    @Column(name = "uid", property = "NOT NULL")
    private String uid;

    /**
     * content 内容
     */
    @Column(name = "content")
    private String content;

    /**
     * time 时间
     */
    @Column(name = "time")
    private long time;

    /**
     * chatId 跟该用户(别人或者自己)聊天的id----该用户的uid
     */
    @Column(name = "chatId")
    private String chatId;

    /**
     * who 区分是自己还是别人,1:自己  0:别人
     */
    @Column(name = "who")
    private int who;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public int getWho() {
        return who;
    }

    public void setWho(int who) {
        this.who = who;
    }

    public void clean() {
        this.uid = "";
        this.content = "";
        this.time = 0;
        this.chatId = "";
        this.who = -1;
    }

    @Override
    public String toString() {
        return "UserChat : {" +"\n" +
                "id=" + id + "\n" +
                ", uid='" + uid + '\'' + "\n" +
                ", content='" + content + '\'' + "\n" +
                ", time=" + time + "\n" +
                ", chatId='" + chatId + '\'' + "\n" +
                ", who=" + who + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserChat)) return false;

        UserChat userChat = (UserChat) o;

        if (getId() != userChat.getId()) return false;
        if (getTime() != userChat.getTime()) return false;
        if (getWho() != userChat.getWho()) return false;
        if (!getUid().equals(userChat.getUid())) return false;
        if (!getContent().equals(userChat.getContent())) return false;
        return getChatId().equals(userChat.getChatId());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getUid().hashCode();
        result = 31 * result + getContent().hashCode();
        result = 31 * result + (int) (getTime() ^ (getTime() >>> 32));
        result = 31 * result + getChatId().hashCode();
        result = 31 * result + getWho();
        return result;
    }
}
