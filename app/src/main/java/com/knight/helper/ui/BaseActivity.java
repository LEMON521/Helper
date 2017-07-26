package com.knight.helper.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import org.xutils.x;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/22.
 */

public abstract class BaseActivity extends FragmentActivity implements Serializable {

    public BaseActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;

        x.view().inject(mActivity);

        initData(savedInstanceState);
    }

    /**
     *
     */
    public abstract void initData(@Nullable Bundle savedInstanceState);
}
