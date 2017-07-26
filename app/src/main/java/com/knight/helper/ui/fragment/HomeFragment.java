package com.knight.helper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.knight.helper.R;
import com.knight.helper.ui.BaseFragment;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/22.
 */

@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment implements Serializable {
    @ViewInject(R.id.fragment_home_lv)
    private ListView home_lv;

    @Override
    public void initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    }

    /**
     * 条目点击事件
     * @param item
     */
    @Event(type = AdapterView.OnItemSelectedListener.class,value = {R.id.fragment_home_lv})
    private void itemSelected(View item){


    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("HomeFragment----onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();

        LogUtil.e("HomeFragment----onPause");
    }

    @Override
    public void onPause() {
        super.onPause();

        LogUtil.e("HomeFragment----onPause");
    }
}
