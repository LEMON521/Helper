package com.knight.helper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.knight.helper.R;
import com.knight.helper.ui.BaseFragment;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/22.
 */

@ContentView(R.layout.fragment_weather_history)
public class WeatherHistoryFragment extends BaseFragment implements Serializable {
//    @ViewInject(R.id.fragment_weather_history_lv)
//    private ListView home_lv;

    @ViewInject(R.id.tv)
    private TextView tv;

    @ViewInject(R.id.animation_view)
    private LottieAnimationView lottie;

    @Override
    public void initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


//    /**
//     * 条目点击事件
//     * @param item
//     */
//    @Event(type = AdapterView.OnItemSelectedListener.class,value = {R.id.fragment_weather_history_lv})
//    private void itemSelected(View item){
//
//
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("WeatherHistoryFragment----onCreate");

    }

    @Override
    public void onStart() {
        super.onStart();
//        lottie.loop(false);//控制循环
        lottie.getDuration();
        lottie.getProgress();
        LogUtil.e("WeatherHistoryFragment----onStart" + lottie.getDuration() + "==" + lottie.getProgress());
    }

    @Override
    public void onPause() {
        super.onPause();

        LogUtil.e("WeatherHistoryFragment----onPause");
    }
}
