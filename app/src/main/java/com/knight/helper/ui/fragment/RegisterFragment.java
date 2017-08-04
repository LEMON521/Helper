package com.knight.helper.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.knight.helper.R;
import com.knight.helper.bean.user.UserBean;
import com.knight.helper.ui.BaseFragment;
import com.knight.helper.utils.MD5Utils;
import com.knight.helper.utils.MyToast;
import com.knight.helper.utils.UUIDUtils;

import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/28.
 */

@ContentView(R.layout.fragment_register)
public class RegisterFragment extends BaseFragment implements Serializable {


    @ViewInject(R.id.fragment_register_et_user)
    private EditText et_user;
    @ViewInject(R.id.fragment_register_tv_user)
    private TextView tv_user;
    @ViewInject(R.id.fragment_register_et_password)
    private EditText et_password;
    @ViewInject(R.id.fragment_register_tv_password)
    private TextView tv_password;
    @ViewInject(R.id.fragment_register_et_password_again)
    private EditText et_password_again;
    @ViewInject(R.id.fragment_register_tv_password_again)
    private TextView tv_password_again;
    @ViewInject(R.id.fragment_register_btn_register)
    private Button btn_register;

    private String str_user = "";
    private String str_password = "";
    private String str_password_again = "";

    private boolean boolean_user = false;
    private boolean boolean_password = false;
    private boolean boolean_password_again = false;

    private View.OnFocusChangeListener focusListener;


    @Override
    public void initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        focusListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus) {
                    switch (v.getId()) {

                        case R.id.fragment_register_et_user:
                            boolean_user = checkUser();//检查用户名
                            LogUtil.e("fragment_register_et_user===" + hasFocus);
                            break;

                        case R.id.fragment_register_et_password:
                            boolean_password = checkPassword();
                            LogUtil.e("fragment_register_et_password===" + hasFocus);
                            break;

                        case R.id.fragment_register_et_password_again:
                            boolean_password_again = checkPasswordAgain();
                            LogUtil.e("fragment_register_tv_password_again===" + hasFocus);
                            break;
                    }
                }

            }
        };

        et_user.setOnFocusChangeListener(focusListener);
        et_password.setOnFocusChangeListener(focusListener);
        et_password_again.setOnFocusChangeListener(focusListener);

    }

    /**
     * 检查数据库中是否存在该用户名
     *
     * @return 符合规则 true;不符合,false
     */
    private boolean checkUser() {
        str_user = et_user.getText().toString().trim();
        if (str_user.length() < 1) {//如果输入框为空,不查询
            tv_user.setTextColor(Color.parseColor("#FF0000"));
            tv_user.setText("用户名不能为空!");
            return false;
        }

        long result = -1;


        try {
            //注意,where中,如果字段名写错,返回结果依然是0,并不会报异常
            result = mApp.db.selector(UserBean.class).where("username", "=", str_user).count();//返回复合条件的记录数
        } catch (DbException e) {
            result = -1;
            e.printStackTrace();
        } finally {
            if (result == 0) {
                tv_user.setTextColor(Color.parseColor("#008F0A"));
                tv_user.setText("恭喜,用户名可用!");
                return true;
            } else if (result > 0) {
                tv_user.setTextColor(Color.parseColor("#FF0000"));
                tv_user.setText("该用户名已存在");
                return false;
            } else if (result == -1) {
                tv_user.setTextColor(Color.parseColor("#565656"));
                tv_user.setText("检查出错");
                return false;
            } else {
                return false;
            }
        }

    }

    /**
     * 判断密码规则
     *
     * @return 符合规则 true;不符合,false
     */
    private boolean checkPassword() {

        str_password = et_password.getText().toString();

        if (str_password.length() < 1) {
            tv_password.setTextColor(Color.parseColor("#FF0000"));
            tv_password.setText("密码不能为空!");
            return false;
        } else if (str_password.length() > 1 && str_password.length() < 6) {
            tv_password.setTextColor(Color.parseColor("#FF0000"));
            tv_password.setText("密码长度应不少于6位!");
            return false;
        } else {
            tv_password.setTextColor(Color.parseColor("#008F0A"));
            tv_password.setText("密码可用!");
            return true;
        }
    }

    /**
     * 检查再次输入密码
     *
     * @return 符合规则 true;不符合,false
     */
    private boolean checkPasswordAgain() {
        str_password_again = et_password_again.getText().toString();

        if (str_password_again.length() < 1) {
            tv_password_again.setTextColor(Color.parseColor("#FF0000"));
            tv_password_again.setText("确认密码不能为空!");
            return false;
        } else if (str_password_again.length() > 1 && str_password_again.length() < 6) {
            tv_password_again.setTextColor(Color.parseColor("#FF0000"));
            tv_password_again.setText("密码长度应不少于6位!");
            return false;
        } else if (!str_password.contentEquals(str_password_again)) {
            tv_password_again.setTextColor(Color.parseColor("#FF0000"));
            tv_password_again.setText("两次输入的密码不同!");
            return false;
        } else {
            tv_password_again.setTextColor(Color.parseColor("#008F0A"));
            tv_password_again.setText("密码可用!");
            return true;
        }
    }


    /**
     * 注册方法
     */
    private void registerUser() {
        /**
         * <P>1.网络注册</P>
         *
         * <P>2.本地注册</P>
         */


        //2.本地注册----向数据库插入一行数据
        UserBean addUserBean = new UserBean();
        addUserBean.setUid(UUIDUtils.getUUID());
        addUserBean.setUsername(str_user);
        addUserBean.setPassword(MD5Utils.getMD5(str_password));
        addUserBean.setSalt(MD5Utils.getMD5Salt(str_user, str_password));

        try {
            mApp.db.save(addUserBean);
            back();//返回到登录界面
            LogUtil.e("注册成功!");
            MyToast.showShort(mActivity, "注册成功,请登录!");
        } catch (DbException e) {
            e.printStackTrace();
            MyToast.showShort(mActivity, "注册失败,请询问管理员");
        }


    }


    /**
     * 返回到登录界面
     */
    private void back() {

        getFragmentManager()
                .beginTransaction()
                .hide(mActivity.mFragmentMaps.get("RegisterFragment"))
                .show(mActivity.mFragmentMaps.get("LoginFragment"))
                .commit();
        mActivity.mFragmentMaps.put("ShowFragment", mActivity.mFragmentMaps.get("LoginFragment"));

    }


    @Event(value = R.id.fragment_register_btn_register)
    private void onClickRegister(View view) {

        switch (view.getId()) {

            case R.id.fragment_register_btn_register:

                //点击注册,应该让输入框的焦点都失去
                if (!boolean_user) {
                    boolean_user = checkUser();

                    if (!boolean_user) {
                        MyToast.showShort(mActivity, "用户名或密码输入有误,请检查!");
                        return;
                    }
                }

                if (!boolean_password) {
                    boolean_password = checkPassword();

                    if (!boolean_password) {
                        MyToast.showShort(mActivity, "用户名或密码输入有误,请检查!");
                        return;
                    }
                }

                if (!boolean_password_again) {
                    boolean_password_again = checkPasswordAgain();

                    if (!boolean_password_again) {
                        MyToast.showShort(mActivity, "用户名或密码输入有误,请检查!");
                        return;
                    }
                }

                if (boolean_user && boolean_password && boolean_password_again) {
                    registerUser();
                } else {
                    MyToast.showShort(mActivity, "用户名或密码输入有误,请检查!");
                }
                break;

        }
    }


    /**
     * 当fragment显示或者隐藏的时候会调用
     * @param hidden 显示-false ,隐藏-true
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        et_user.setText("");
        et_password.setText("");
        et_password_again.setText("");
        tv_user.setText("");
        tv_password.setText("");
        tv_password_again.setText("");

        LogUtil.e("是否显示==" + hidden);

    }


    //以下为测试切换Fragment时走的方法
//    @Override
//    public Object getEnterTransition() {
//        LogUtil.e("Register===getEnterTransition");
//        return super.getEnterTransition();
//    }
//
//    @Override
//    public Object getExitTransition() {
//        LogUtil.e("Register===getExitTransition");
//        return super.getExitTransition();
//
//    }
//
//    @Override
//    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
//        LogUtil.e("Register===onCreateAnimation");
//
//        LogUtil.e("Register===transit" + transit);
//        LogUtil.e("Register===enter" + enter);
//        LogUtil.e("Register===nextAnim" + nextAnim);
//        return super.onCreateAnimation(transit, enter, nextAnim);
//    }
//
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        LogUtil.e("Register===onHiddenChanged");
//    }
}
