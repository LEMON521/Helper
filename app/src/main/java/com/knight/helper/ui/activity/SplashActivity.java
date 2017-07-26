package com.knight.helper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.knight.helper.MainActivity;
import com.knight.helper.R;
import com.knight.helper.constant.SPConstant;
import com.knight.helper.ui.BaseActivity;
import com.knight.helper.ui.fragment.LoginFragment;
import com.knight.helper.utils.SPUtils;

import org.xutils.common.util.MD5;
import org.xutils.view.annotation.ContentView;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/25.
 */
@ContentView(R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements Serializable {


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {


        if (SPUtils.getValueBoolean(mActivity
                , SPConstant.USER_CONFIG_XML
                , SPConstant.USER_CONFIG_KEY_LOGIN_AUTO)) {
            if (checkLogin()) {
                Intent intent = new Intent(mActivity, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                SPUtils.setValue(mActivity
                        , SPConstant.USER_CONFIG_XML
                        , SPConstant.USER_CONFIG_KEY_PASSWORD
                        , "");//将密码设置为空

                showLogin();

            }
        } else {
            showLogin();
        }


    }


    /**
     * 显示登录fragment
     */
    private void showLogin() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, new LoginFragment())
                .commit();

    }


    /**
     * 登录
     *
     * @return
     */
    private boolean checkLogin() {

        return SPUtils.getValueStr(mActivity
                , SPConstant.USER_CONFIG_XML
                , SPConstant.USER_CONFIG_KEY_ACCOUNT).equals("aaa")
                &&
                MD5.md5(SPUtils.getValueStr(mActivity
                        , SPConstant.USER_CONFIG_XML
                        , SPConstant.USER_CONFIG_KEY_PASSWORD)).equals(MD5.md5("111"));

    }
}
