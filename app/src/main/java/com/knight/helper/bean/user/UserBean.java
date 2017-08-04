package com.knight.helper.bean.user;

import org.xutils.DbManager;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.ex.DbException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lemon on 2017/7/27.
 */

@Table(name = "UserBase")
public class UserBean implements Serializable {

    /**
     * 默认构造方法必须有,如果没有,这张表是创建不成功的
     */
    public UserBean() {

    }


    /**
     * uid 32位
     */
    @Column(name = "uid", isId = true, property = "NOT NULL")
    private String uid;


    /**
     * 登录名
     */
    @Column(name = "username")
    private String username;


    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 密码
     */
    @Column(name = "password", property = "NOT NULL")
    private String password;

    /**
     * 加盐---也就是说MD5加密后,在进行一次加密
     */
    @Column(name = "salt")
    private String salt;

    /**
     * 是否在线
     */
    @Column(name = "isOnline")
    private boolean isOnline;

    /**
     * 是否禁用
     */
    @Column(name = "disabled")
    private boolean disabled;

    /**
     * 是否禁用
     */
    @Column(name = "email")
    private String email;


    /**
     * 登录时间---最近一次登录时间
     */
    @Column(name = "loginAt")
    private long loginAt;

    /**
     * 退出登录时间---最近一次退出登录时间
     */
    @Column(name = "logoutAt")
    private long logoutAt;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private long createTime;


    /**
     * 登录次数
     */
    @Column(name = "loginCount")
    private long loginCount;


    public List<UserInfo> getUserInfo(DbManager db) throws DbException {
        return db.selector(UserInfo.class).where("uid", "=", this.uid).findAll();
    }

    public List<UserChat> getUserChat(DbManager db) throws DbException {

        //只要是跟该用户
        return db.selector(UserChat.class).where("chatId", "=", this.uid).findAll();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setLoginCount(long loginCount) {
        this.loginCount = loginCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(long loginAt) {
        this.loginAt = loginAt;
    }

    public long getLogoutAt() {
        return logoutAt;
    }

    public void setLogoutAt(long logoutAt) {
        this.logoutAt = logoutAt;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLoginCount() {
        return loginCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserBean)) return false;

        UserBean userBean = (UserBean) o;

        if (!getUid().equals(userBean.getUid())) return false;
        if (!getUsername().equals(userBean.getUsername())) return false;
        return getPassword().equals(userBean.getPassword());

    }

    @Override
    public int hashCode() {
        int result =  getUid().hashCode();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }


}
