package com.knight.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.Toast;

import com.knight.helper.ui.BaseActivity;
import com.knight.helper.ui.BaseFragment;
import com.knight.helper.ui.fragment.ChatFragment;
import com.knight.helper.ui.fragment.HomeFragment;
import com.knight.helper.ui.fragment.MineFragment;
import com.knight.helper.ui.fragment.WeatherHistoryFragment;

import org.xutils.view.annotation.ContentView;

import java.util.HashMap;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    //@ViewInject(R.id.navigation)
    private BottomNavigationView navigation;

    /**
     * Integer : source id
     * <p>
     * Integer : 第几个fragment
     * 跟fragments配合使用
     */
    private HashMap<Integer, Integer> tabMap;
    private BaseFragment[] fragments;
    private FragmentTransaction manager;
    private Integer fragmentValue;
    private BaseFragment fragmentDefault;

    private String fragmentTag;
    private BaseFragment mHomeFragment, mChatFragment, mWeatherHistoryFragment, mMineFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentTag = "FRAGMENT_HOME";
                    fragmentValue = tabMap.get(R.id.navigation_home);
                    //setFragment(mHomeFragment, fragmentTag);
                    switchFragment(fragments[fragmentValue]);
                    Toast.makeText(mActivity, "主页😆", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_chat:

                    fragmentTag = "FRAGMENT_CHAT";
                    fragmentValue = tabMap.get(R.id.navigation_chat);
                    //setFragment(mChatFragment, fragmentTag);
                    switchFragment(fragments[fragmentValue]);
                    Toast.makeText(mActivity, "乐一乐😝", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_weather:


                    fragmentTag = "FRAGMENT_WEATHER_HISTORY";
                    fragmentValue = tabMap.get(R.id.navigation_weather);
                    //setFragment(mWeatherHistoryFragment, fragmentTag);
                    switchFragment(fragments[fragmentValue]);
                    Toast.makeText(mActivity, "历史天气😋", Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.navigation_mine:
                    fragmentTag = "FRAGMENT_MINE";
                    fragmentValue = tabMap.get(R.id.navigation_mine);
                    //setFragment(mWeatherHistoryFragment, fragmentTag);
                    switchFragment(fragments[fragmentValue]);
                    Toast.makeText(mActivity, "我的信息😋", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }

    };


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
        }

        if (mChatFragment == null) {
            mChatFragment = new ChatFragment();
        }

        if (mWeatherHistoryFragment == null) {
            mWeatherHistoryFragment = new WeatherHistoryFragment();
        }

        if (mMineFragment == null) {
            mMineFragment = new MineFragment();
        }

        if (tabMap == null) {
            tabMap = new HashMap<>();
        }

        if (!(tabMap.size() > 0)) {
            tabMap.put(R.id.navigation_home, 0);
            tabMap.put(R.id.navigation_chat, 1);
            tabMap.put(R.id.navigation_weather, 2);
            tabMap.put(R.id.navigation_mine, 3);

        }

        if (fragments == null) {
            fragments = new BaseFragment[]{mHomeFragment, mChatFragment, mWeatherHistoryFragment, mMineFragment};
        }

        if (manager == null) {
            manager = getSupportFragmentManager().beginTransaction();

            manager.add(R.id.content, mHomeFragment)
                    .add(R.id.content, mChatFragment)
                    .add(R.id.content, mWeatherHistoryFragment)
                    .add(R.id.content, mMineFragment)
                    .hide(mHomeFragment)
                    .hide(mChatFragment)
                    .hide(mWeatherHistoryFragment)
                    .hide(mMineFragment)
                    .show(mChatFragment)
                    .commit();
        }

        //底部栏切换事件
        //不知为何,如果用ViewInject的话,这里的切换事件就触发不了
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_chat);
    }


    /**
     * 切换fragment
     *
     * @param fragment
     * @param tag
     */
    private void setFragment(BaseFragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content
                        , fragment
                        , tag)
                .commit();
    }


    /**
     * 这样坐的好处,就是fragment没有被销毁,但是有另外一个问题,就是内存(未验证此说法)
     *
     * @param fragment
     */
    private void switchFragment(BaseFragment fragment) {

        if (fragmentDefault == null) {
            fragmentDefault = mChatFragment;
        }

        getSupportFragmentManager().beginTransaction().hide(fragmentDefault)
                .show(fragment)
                .commit();

        fragmentDefault = fragment;
    }

}
