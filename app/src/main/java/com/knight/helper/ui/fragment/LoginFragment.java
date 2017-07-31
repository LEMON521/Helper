package com.knight.helper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.knight.helper.MainActivity;
import com.knight.helper.R;
import com.knight.helper.bean.user.UserBean;
import com.knight.helper.constant.SPConstant;
import com.knight.helper.ui.BaseFragment;
import com.knight.helper.utils.MD5Utils;
import com.knight.helper.utils.MyToast;
import com.knight.helper.utils.SPUtils;

import org.xutils.DbManager;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/25.
 */
@ContentView(R.layout.fragment_login)
public class LoginFragment extends BaseFragment implements Serializable {

    @ViewInject(R.id.fragment_login_iv_logo)//页面logo
    private ImageView lv_logo;

    @ViewInject(R.id.fragment_login_et_account)//账号输入框
    private EditText et_account;

    @ViewInject(R.id.fragment_login_et_password)//密码输入框
    private EditText et_password;

    @ViewInject(R.id.fragment_login_cb_password)//记住密码
    private CheckBox cb_password;

    @ViewInject(R.id.fragment_login_cb_auto)//自动登录
    private CheckBox cb_auto;

    @ViewInject(R.id.fragment_login_btn_login)//登录按钮
    private Button btn_login;

    @ViewInject(R.id.fragment_login_tv_register)//注册
    private TextView tv_register;

    @ViewInject(R.id.fragment_login_tv_forget)//找回密码
    private TextView tv_forget;


    private String str_account = "";
    private String str_password = "";
    private boolean is_password = false;
    private boolean is_autoLogin = false;

    private CompoundButton.OnCheckedChangeListener checkedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }
    };

    private LoginFragment mFragment;

    private DbManager db;


    @Override
    public void initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragment = this;


        try {
            mApp.db.delete(UserBean.class);//清空user表数据
        } catch (DbException e) {
            e.printStackTrace();
        }

        is_password = SPUtils.getValueBoolean(mActivity
                , SPConstant.USER_CONFIG_XML
                , SPConstant.USER_CONFIG_KEY_PASSWORD_REMEMBER);

        is_autoLogin = SPUtils.getValueBoolean(mActivity
                , SPConstant.USER_CONFIG_XML
                , SPConstant.USER_CONFIG_KEY_LOGIN_AUTO);


        checkedListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()) {

                    case R.id.fragment_login_cb_password:
                        is_password = isChecked;
                        break;

                    case R.id.fragment_login_cb_auto:
                        is_autoLogin = isChecked;
                        break;
                }
            }
        };

        cb_auto.setChecked(is_autoLogin);
        cb_auto.setOnCheckedChangeListener(checkedListener);


        cb_password.setOnCheckedChangeListener(checkedListener);

        if (is_password) {//如果记住密码,就把缓存的密码显示在界面上

            cb_password.setChecked(is_password);

            str_account = SPUtils.getValueStr(mActivity
                    , SPConstant.USER_CONFIG_XML
                    , SPConstant.USER_CONFIG_KEY_ACCOUNT);

            et_account.setText(str_account);


            str_password = SPUtils.getValueStr(mActivity
                    , SPConstant.USER_CONFIG_XML
                    , SPConstant.USER_CONFIG_KEY_PASSWORD);

            et_password.setText(str_password);

        }


        is_password = cb_password.isChecked();
        is_autoLogin = cb_auto.isChecked();

        if(is_autoLogin){
            loginExamine();
        }

    }


    /**
     * 登录检查
     */
    private void loginExamine() {
        str_account = et_account.getText().toString().trim();
        str_password = et_password.getText().toString().trim();

        if (TextUtils.isEmpty(str_account)) {
            MyToast.showShort(mActivity, "用户名不能为空!");
            return;
        }

        if (TextUtils.isEmpty(str_account)) {
            MyToast.showShort(mActivity, "密码不能为空!");
            return;
        }


        checkLogin();
    }


    /**
     * 检查登录
     */
    private void checkLogin() {


        /**
         * 登陆成功,检查是否记住密码,是否自动登录
         */

        long count = -1;
        try {
            count = mApp
                    .db
                    .selector(UserBean.class)
                    .where("username", "=", str_account)
                    .and("password", "=", MD5Utils.getMD5(str_password))
                    .count();//返回符合条件的记录数


            LogUtil.e("密码==" + MD5Utils.getMD5(str_password));
            for (UserBean userBean : mApp.db.findAll(UserBean.class)) {
                LogUtil.e("===" + userBean.toString() + "===");
            }

        } catch (DbException e) {
            count = -1;
            e.printStackTrace();
        } finally {
            if (count == 1) {
                rememberPassword();
                Intent intent = new Intent(mActivity, MainActivity.class);
                startActivity(intent);
                mActivity.finish();
            } else if (count == 0) {
                MyToast.showShort(mActivity, "账号或密码错误!");
            } else if (count == -1) {
                MyToast.showShort(mActivity, "登录失败,请联系管理员");
            }
        }


    }


    /**
     * 是否记住密码
     */
    private void rememberPassword() {


        if (!is_password) {
            /**
             * 省略插入到数据库,后期维护
             */

            is_password = false;
            str_account = "";
            str_password = "";

        } else {

        }

        SPUtils.setValue(mActivity
                , SPConstant.USER_CONFIG_XML
                , SPConstant.USER_CONFIG_KEY_PASSWORD_REMEMBER
                , is_password);


        SPUtils.setValue(mActivity
                , SPConstant.USER_CONFIG_XML
                , SPConstant.USER_CONFIG_KEY_ACCOUNT
                , str_account);


        SPUtils.setValue(mActivity
                , SPConstant.USER_CONFIG_XML
                , SPConstant.USER_CONFIG_KEY_PASSWORD
                , str_password);


        SPUtils.setValue(mActivity
                , SPConstant.USER_CONFIG_XML
                , SPConstant.USER_CONFIG_KEY_LOGIN_AUTO
                , is_autoLogin);


    }


    /**
     * 点击事件
     *
     * @param view
     */
    @Event(value = {R.id.fragment_login_btn_login
            , R.id.fragment_login_tv_register
            , R.id.fragment_login_tv_forget})
    private void onClick(View view) {

        switch (view.getId()) {

            //登录按钮
            case R.id.fragment_login_btn_login:

                loginExamine();
                break;

            case R.id.fragment_login_tv_register://注册
                getFragmentManager()
                        .beginTransaction()
                        .hide(mActivity.mFragmentMaps.get("LoginFragment"))
                        .show(mActivity.mFragmentMaps.get("RegisterFragment"))
                        .commit();
                mActivity.mFragmentMaps.put("ShowFragment", mActivity.mFragmentMaps.get("RegisterFragment"));

                break;

            case R.id.fragment_login_tv_forget://找回密码
                //ArrayList<String> photoPaths = new ArrayList<>();


                break;
        }

    }


    //以下为测试切换Fragment时走的方法
//
//    @Override
//    public Object getEnterTransition() {
//        LogUtil.e("Login---getEnterTransition");
//        return super.getEnterTransition();
//    }
//
//    @Override
//    public Object getExitTransition() {
//        LogUtil.e("Login---getExitTransition");
//        return super.getExitTransition();
//
//    }
//
//    @Override
//    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
//        LogUtil.e("Login---onCreateAnimation");
//
//        LogUtil.e("Login===transit" + transit);
//        LogUtil.e("Login===enter" + enter);
//        LogUtil.e("Login===nextAnim" + nextAnim);
//        return super.onCreateAnimation(transit, enter, nextAnim);
//    }
//
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        LogUtil.e("Login---onHiddenChanged");
//    }
}
