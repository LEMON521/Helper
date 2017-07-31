package com.knight.helper.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.knight.helper.app.MyApplication;

import org.xutils.x;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by lemon on 2017/7/22.
 */

public abstract class BaseActivity extends FragmentActivity implements Serializable {

    public BaseActivity mActivity;

    public MyApplication mApp;

    public HashMap<String,BaseFragment> mFragmentMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;

        mApp = (MyApplication) getApplication();

        if (mFragmentMaps == null) {
            mFragmentMaps = new HashMap<>();
        }


        x.view().inject(mActivity);

        initData(savedInstanceState);
    }

    /**
     *
     */
    public abstract void initData(@Nullable Bundle savedInstanceState);
}
