package com.knight.helper.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.knight.helper.R;
import com.knight.helper.constant.SPConstant;
import com.knight.helper.ui.BaseActivity;
import com.knight.helper.ui.fragment.LoginFragment;
import com.knight.helper.ui.fragment.RegisterFragment;
import com.knight.helper.utils.MyToast;
import com.knight.helper.utils.SPUtils;

import org.xutils.common.util.MD5;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/25.
 */
@ContentView(R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements Serializable {

    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;

    private long exitTime = 0;//记录退出时间,第一次点击退出时间(物理按钮)

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        if (loginFragment == null) {
            loginFragment = new LoginFragment();
        }

        if (registerFragment == null) {
            registerFragment = new RegisterFragment();
        }

        mFragmentMaps.put("LoginFragment", loginFragment);
        mFragmentMaps.put("RegisterFragment", registerFragment);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, loginFragment, "LoginFragment")
                .add(R.id.content, registerFragment, "RegisterFragment")
                .hide(loginFragment)
                .hide(registerFragment)
                .show(loginFragment)
                .commit();

        mFragmentMaps.put("ShowFragment", loginFragment);


//        if (SPUtils.getValueBoolean(mActivity
//                , SPConstant.USER_CONFIG_XML
//                , SPConstant.USER_CONFIG_KEY_LOGIN_AUTO)) {
//            if (checkLogin()) {
//                Intent intent = new Intent(mActivity, MainActivity.class);
//                startActivity(intent);
//                finish();
//            } else {
//                SPUtils.setValue(mActivity
//                        , SPConstant.USER_CONFIG_XML
//                        , SPConstant.USER_CONFIG_KEY_PASSWORD
//                        , "");//将密码设置为空
//
//                showLogin();
//
//            }
//        } else {
//            showLogin();
//        }


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
     * <P>重写返回按钮监听事件</P>
     * <p>
     * <P>如果是注册界面,则返回到登录界面</P>
     * <p>
     * <P>如果是登录界面,则判断是否误按返回键,两秒内连续按两次则退出程序</P>
     *
     * @param event
     * @return
     */
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                if (mFragmentMaps.get("ShowFragment") == registerFragment) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .hide(registerFragment)
                            .show(loginFragment)
                            .commit();
                    mFragmentMaps.put("ShowFragment", mFragmentMaps.get("LoginFragment"));
                    exitTime = 0;
                    return false;
                } else if ((System.currentTimeMillis() - exitTime) > 2000) {
                    MyToast.showShort(mActivity, "再按一次退出程序");
                    exitTime = System.currentTimeMillis();
                    return false;
                } else {
                    finish();
                    System.exit(0);
                }
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Event(R.id.view_top_bar_back)
    private void onClick(View view) {
        switch (view.getId()) {

            case R.id.view_top_bar_back:
                if (mFragmentMaps.get("ShowFragment") == registerFragment) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .hide(registerFragment)
                            .show(loginFragment)
                            .commit();

                    mFragmentMaps.put("ShowFragment", mFragmentMaps.get("LoginFragment"));
                    exitTime = 0;
                } else if ((System.currentTimeMillis() - exitTime) > 2000) {
                    MyToast.showShort(mActivity, "再按一次退出程序");
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                }

                break;
        }
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
