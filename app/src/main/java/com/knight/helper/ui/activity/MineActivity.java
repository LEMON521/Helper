package com.knight.helper.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.knight.helper.R;
import com.knight.helper.ui.BaseActivity;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;

/**
 * Created by lemon on 2017/8/2.
 */

@ContentView(R.layout.activity_mine)
public class MineActivity extends BaseActivity implements Serializable{


    @ViewInject(R.id.view_top_bar_title)
    private TextView title;

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        LogUtil.e("自定义控件页面");

        title.setText("个人信息");



    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        Integer i = new Integer("");
        //通知栏
//        Notification notification = new Notification(R.mipmap.ta,
//                "有通知到来", System.currentTimeMillis());
//        Intent notificationIntent = new Intent(this, MineActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
//                notificationIntent, 0);
//        notification.setLatestEventInfo(this, "这是通知的标题", "这是通知的内容",
//                pendingIntent);
//        startForeground(1, notification);
    }
}
