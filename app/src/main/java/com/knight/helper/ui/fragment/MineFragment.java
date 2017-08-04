package com.knight.helper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.knight.helper.R;
import com.knight.helper.bean.user.UserInfo;
import com.knight.helper.constant.SPConstant;
import com.knight.helper.ui.BaseFragment;
import com.knight.helper.ui.activity.MineActivity;
import com.knight.helper.utils.SPUtils;
import com.knight.helper.view.CircleImageView;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;

/**
 * Created by lemon on 2017/8/1.
 */

@ContentView(R.layout.fragment_mine)
public class MineFragment extends BaseFragment implements Serializable {

    @ViewInject(R.id.fragment_mine_ll_base)
    private LinearLayout ll_base;//基本信息栏

    @ViewInject(R.id.fragment_mine_iv_avatar)
    private CircleImageView iv_avatar;//基本信息栏--头像
    @ViewInject(R.id.fragment_mine_tv_name)
    private TextView tv_name;//基本信息栏--姓名
    @ViewInject(R.id.fragment_mine_tv_email)
    private TextView tv_email;//基本信息栏--邮箱


    @ViewInject(R.id.fragment_mine_ll_setting)
    private LinearLayout ll_setting;//设置栏

    @ViewInject(R.id.fragment_mine_ll_about)
    private LinearLayout ll_about;//关于lan


    @ViewInject(R.id.fragment_mine_tv_logout)
    private TextView tv_logout;//退出登录按钮

    private UserInfo userInfo;

    private String str_name = "";//用户名称
    private String str_email = "";//用户邮箱

    @Override
    public void initView(LayoutInflater inflater
            , @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {


        getData();
        setData();
    }


    /**
     * 设置数据
     */
    private void setData() {
        if (userInfo == null) {
            //iv_avatar     //设置头像
            tv_name.setText("尚未设置昵称");
            tv_email.setText("尚未设置邮箱");
        } else {
            str_name = userInfo.getNickName();
            str_email = userInfo.getEmail();
            tv_name.setText(str_name);
            tv_email.setText(str_email);
        }
    }


    /**
     * 获取到数据---这里从数据库获取
     */
    private void getData() {

        try {
            userInfo = mApp
                    .db
                    .findById(UserInfo.class
                            , SPUtils.getValueStr(mActivity
                                    , SPConstant.USER_CONFIG_XML
                                    , SPConstant.USER_CONFIG_KEY_UID));

        } catch (DbException e) {
            e.printStackTrace();
        }


    }


    /**
     * 点击事件
     *
     * @param view
     */
    @Event(value = {R.id.fragment_mine_ll_base
            , R.id.fragment_mine_ll_setting
            , R.id.fragment_mine_ll_about
            , R.id.fragment_mine_tv_logout})
    private void onClick(View view) {

        String fragTag = "";

        switch (view.getId()) {

            case R.id.fragment_mine_ll_base:
                fragTag = "MineBaseFragment";
                break;

            case R.id.fragment_mine_ll_setting:
                fragTag = "MineSettingFragment";
                break;

            case R.id.fragment_mine_ll_about:
                fragTag = "MineAboutFragment";
                break;

            case R.id.fragment_mine_tv_logout:
                logout();
                return;
        }

        Intent mineIntent = new Intent(mActivity, MineActivity.class);
        mineIntent.putExtra("fragmentTag", fragTag);
        startActivity(mineIntent);
    }

    /**
     * 退出登录
     */
    private void logout() {

        //设置登录状态
        SPUtils.setValue(mActivity
                , SPConstant.USER_CONFIG_XML
                , SPConstant.USER_CONFIG_KEY_IS_LOGIN
                , false);

        mActivity.finish();
    }

}
