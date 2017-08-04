package com.knight.helper.constant;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/25.
 */

public class SPConstant implements Serializable {

    /**
     * USER_CONFIG 文件
     */
    public static final String USER_CONFIG_XML = "user_config";
    /**
     * key_account 账号缓存
     */
    public static final String USER_CONFIG_KEY_ACCOUNT = "key_account";

    /**
     * key_uid uid缓存
     */
    public static final String USER_CONFIG_KEY_UID = "key_uid";

    /**
     * key_password 密码缓存--MD5加密
     */
    public static final String USER_CONFIG_KEY_PASSWORD = "key_password";

    /**
     *  是否自动登录
     */
    public static final String USER_CONFIG_KEY_LOGIN_AUTO = "key_login_auto";


    /**
     * key_password_remember 是否自动登录
     */
    public static final String USER_CONFIG_KEY_PASSWORD_REMEMBER = "key_password_remember";

    /**
     * key_uid uid缓存
     */
    public static final String USER_CONFIG_KEY_IS_LOGIN = "key_is_login";



}
