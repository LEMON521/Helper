package com.knight.helper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.knight.helper.R;
import com.knight.helper.adapter.ChatAdapter;
import com.knight.helper.bean.ChatPushBean;
import com.knight.helper.bean.ChatReceiveBean;
import com.knight.helper.bean.user.UserChat;
import com.knight.helper.constant.AddressURL;
import com.knight.helper.constant.SPConstant;
import com.knight.helper.ui.BaseFragment;
import com.knight.helper.utils.GsonUtil;
import com.knight.helper.utils.HttpUtils;
import com.knight.helper.utils.MyToast;
import com.knight.helper.utils.SPUtils;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lemon on 2017/7/22.
 */

@ContentView(R.layout.fragment_chat)
public class ChatFragment extends BaseFragment implements Serializable, OnRefreshListener, OnLoadMoreListener {


    @ViewInject(R.id.swipe_target)
    private ListView fun_lv;

    @ViewInject(R.id.view_chat_reply_et)
    private EditText reply_et;
    @ViewInject(R.id.view_chat_reply_send)
    private TextView reply_send;

    //    @ViewInject(R.id.swipeToLoadLayout)
    private SwipeToLoadLayout swipeToLoadLayout;//刷新加载控件

    private String reply = "";
    //    private ChatBean chatBean;
    private ChatPushBean pushBean;
    private ChatReceiveBean receiveBean;
    private ArrayList<UserChat> chatBeanArrayList;
    private ChatAdapter chatAdapter;

    private UserChat userChat = null;

    private int offset = 0;//从第几个查询数据

    @Override
    public void initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        swipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);

//        fun_lv = (ListView) view.findViewById(R.id.swipe_target);

        initData();

//        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setLoadMoreEnabled(false);//设置是否可以上拉加载更多

        swipeToLoadLayout.setOnRefreshListener(this);

//        swipeToLoadLayout.setOnLoadMoreListener(this);

        autoRefresh();


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private void initData() {

//        if (chatBean == null) {
//            chatBean = new ChatBean();
//        }

        if (userChat == null) {
            userChat = new UserChat();
        }

        if (pushBean == null) {
            pushBean = new ChatPushBean();
        }

        if (receiveBean == null) {
            receiveBean = new ChatReceiveBean();
        }

        if (chatBeanArrayList == null) {
            chatBeanArrayList = new ArrayList();
        } else {
            chatBeanArrayList.clear();
        }

        if (chatAdapter == null) {
            chatAdapter = new ChatAdapter(mActivity, chatBeanArrayList);
        }

        fun_lv.setAdapter(chatAdapter);

        fun_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(mActivity, "手贱,点我干嘛哦?😆", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getData() {


        RequestParams params = new RequestParams(AddressURL.URL_ROBOT);
        params.addBodyParameter("key", AddressURL.APPKEY_ROBOT);

        params.addBodyParameter("info", reply);

        HttpUtils http = new HttpUtils();
        http.get(mActivity, params);
        http.OnCallBack(new HttpUtils.OnSetData() {
            @Override
            public void onSuccess(String strJson) {

                LogUtil.e("=====聊天成功!=====");

                LogUtil.e(strJson);

                receiveBean = GsonUtil.getBean(strJson, ChatReceiveBean.class);

                if (receiveBean.error_code.equals("0")) {
                    userChat = new UserChat();

                    userChat.setWho(0);
                    userChat.setContent(receiveBean.result.text);
                    userChat.setTime(System.currentTimeMillis());


                    save2Db(userChat);

                    chatAdapter.notifyDataSetChanged();
                    fun_lv.setSelection(fun_lv.getBottom());
                } else {
                    Toast.makeText(mActivity, "哎呀!网络打盹啦!😆", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(mActivity, "哎呀!网络打盹啦!😆", Toast.LENGTH_SHORT).show();
                LogUtil.e("=====聊天失败!=====");
            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {
                LogUtil.e("=====聊天取消!=====");
            }

            @Override
            public void onFinished() {
                LogUtil.e("=====聊天完成!=====");
            }
        });


    }


    /**
     * 保存数据到数据库
     *
     * @param chat
     */
    private void save2Db(UserChat chat) {
        //userChat.clean();


        switch (chat.getWho()) {

            case 0:
                chat.setUid("robot");
                break;
            case 1:
                chat.setUid(SPUtils.getValueStr(mActivity, SPConstant.USER_CONFIG_XML, SPConstant.USER_CONFIG_KEY_UID));
                break;
        }
        chat.setChatId(SPUtils.getValueStr(mActivity, SPConstant.USER_CONFIG_XML, SPConstant.USER_CONFIG_KEY_UID));

        try {
            mApp.db.save(chat);
            chatBeanArrayList.add(chat);
            offset++;//更新查询游标
//            for (UserChat c : mApp.db.findAll(UserChat.class)) {
//                LogUtil.e("获取到的 chat==" + c.toString());
//            }
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    /**
     * 从数据库中获取数据
     */
    private void getDbData() {
        try {
            List<UserChat> userChats = mApp
                    .db
                    .selector(UserChat.class)
                    .where("chatId", "=", SPUtils.getValueStr(mActivity, SPConstant.USER_CONFIG_XML, SPConstant.USER_CONFIG_KEY_UID))
                    .orderBy("time", true)//true:从后往前查询，走的路线：先查询全部，然后根据time降序，然后走limit的数量
                    .offset(offset)//从第几个查询，注意：0是第一个
                    .limit(10)
                    .findAll();
            LogUtil.e("分组查询::" + offset + "\n" + "数量==" + userChats.size() + "\n" + userChats.toString());

            int size = userChats.size();

            if (size > 0) {
                for (UserChat uChat : userChats) {
                    chatBeanArrayList.add(0, uChat);
                }


                offset += size;
                chatAdapter.notifyDataSetChanged();
                fun_lv.setSelection(size);//设置选中的位置。注意：应当在chatAdapter.notifyDataSetChanged();之后设置选中条目，因为在那之前，数据条目的属兔还没有更新
            } else {
                if (offset==0) {
                    MyToast.showShort(mActivity, "你还没有聊天哟，快开启🔛聊天之路吧");
                }else {
                    MyToast.showShort(mActivity, "你已经把我看完啦！👀");
                }

            }

            LogUtil.e("==========================");
            //如果为true，则会一直刷新，所以说，这个是控制刷新状态的
            swipeToLoadLayout.setRefreshing(false);


        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    /**
     * 条目点击事件
     *
     * @param item
     */
    @Event(type = View.OnClickListener.class, value = {R.id.view_chat_reply_send})
    private void onClick(View item) {

        reply = reply_et.getText().toString().trim();
        if (TextUtils.isEmpty(reply)) {
            Toast.makeText(mActivity, "需要输入内容才可以聊天哦~", Toast.LENGTH_SHORT).show();
            return;
        } else {
            userChat = new UserChat();
            userChat.setWho(1);
            userChat.setContent(reply);
            userChat.setTime(System.currentTimeMillis());

            save2Db(userChat);

            chatAdapter.notifyDataSetChanged();
            reply_et.setText("");
            fun_lv.setSelection(fun_lv.getBottom());

//            chatBeanArrayList.add(userChat);//
            getData();
        }


    }


    /**
     * 上拉刷新事件
     */
    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getDbData();
            }

        }, 2000);
    }


    /**
     * 下拉加载事件
     */
    @Override
    public void onLoadMore() {

        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setLoadingMore(false);
            }
        }, 2000);

    }

    /**
     * 自动刷新
     */
    private void autoRefresh() {
        swipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                getDbData();
                swipeToLoadLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("FunFragment----onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();

        LogUtil.e("FunFragment----onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.e("FunFragment----onPause");
    }

}
