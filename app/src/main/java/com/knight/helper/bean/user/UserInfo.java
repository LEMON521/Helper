package com.knight.helper.bean.user;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * 用户详细信息表--UserInfo
 * <p>
 * Created by lemon on 2017/8/1.
 */
@Table(name = "UserInfo")
public class UserInfo implements Serializable {

    /**
     * 默认构造方法必须有,如果没有,这张表是创建不成功的
     */
    public UserInfo() {

    }


    /**
     * id
     */
    @Column(name = "id", isId = true, autoGen = true, property = "NOT NULL")
    private long id;

    /**
     * uid 32位
     */
    @Column(name = "uid", property = "NOT NULL")
    private String uid;

    /**
     * nickName 昵称
     */
    @Column(name = "nickName", property = "NOT NULL")
    private String nickName;

    /**
     * email 邮箱
     */
    @Column(name = "email")
    private String email;


    /**
     * phone 电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * avatar 头像
     */
    @Column(name = "avatar")
    private String avatar;


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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserInfo : {" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;

        UserInfo userInfo = (UserInfo) o;

        if (getId() != userInfo.getId()) return false;
        if (!getUid().equals(userInfo.getUid())) return false;
        if (!getNickName().equals(userInfo.getNickName())) return false;
        if (!getEmail().equals(userInfo.getEmail())) return false;
        if (!getPhone().equals(userInfo.getPhone())) return false;
        return getAvatar().equals(userInfo.getAvatar());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getUid().hashCode();
        result = 31 * result + getNickName().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPhone().hashCode();
        result = 31 * result + getAvatar().hashCode();
        return result;
    }
}
