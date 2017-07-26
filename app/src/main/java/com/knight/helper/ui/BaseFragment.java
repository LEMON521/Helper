package com.knight.helper.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.x;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/22.
 */

public abstract class BaseFragment extends Fragment implements Serializable {

    public View view;
    public BaseActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mActivity = (BaseActivity) getActivity();

        view = x.view().inject(this, inflater, null); //注入view和事件

        initView(inflater, container, savedInstanceState);

        return view;
    }

    /**
     * 用于初始化控件的方法
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    public abstract void initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
}
