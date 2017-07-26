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

import com.knight.helper.R;
import com.knight.helper.adapter.ChatAdapter;
import com.knight.helper.bean.ChatBean;
import com.knight.helper.bean.ChatPushBean;
import com.knight.helper.bean.ChatReceiveBean;
import com.knight.helper.constant.AddressURL;
import com.knight.helper.ui.BaseFragment;
import com.knight.helper.utils.GsonUtil;
import com.knight.helper.utils.HttpUtils;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lemon on 2017/7/22.
 */

@ContentView(R.layout.fragment_chat)
public class ChatFragment extends BaseFragment implements Serializable {


    @ViewInject(R.id.fragment_chat_lv)
    private ListView fun_lv;

    @ViewInject(R.id.view_chat_reply_et)
    private EditText reply_et;
    @ViewInject(R.id.view_chat_reply_send)
    private TextView reply_send;


    private String reply = "";
    private ChatBean chatBean;
    private ChatPushBean pushBean;
    private ChatReceiveBean receiveBean;
    private ArrayList<ChatBean> chatBeanArrayList;
    private ChatAdapter chatAdapter;


    @Override
    public void initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initData();
    }

    private void initData(){

        if (chatBean==null) {
            chatBean = new ChatBean();
        }

        if (pushBean==null) {
            pushBean = new ChatPushBean();
        }

        if (receiveBean==null) {
            receiveBean = new ChatReceiveBean();
        }

        if (chatBeanArrayList==null) {
            chatBeanArrayList = new ArrayList();
        }else {
            chatBeanArrayList.clear();
        }

        if (chatAdapter==null) {
            chatAdapter = new ChatAdapter(mActivity,chatBeanArrayList);
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

                receiveBean = GsonUtil.getBean(strJson,ChatReceiveBean.class);

                if (receiveBean.error_code.equals("0")) {
                    chatBean = new ChatBean();

                    chatBean.type = 0;
                    chatBean.text = receiveBean.result.text;
                    chatBean.time = System.currentTimeMillis()+"";

                    chatBeanArrayList.add(chatBean);
                    chatAdapter.notifyDataSetChanged();
                    fun_lv.setSelection(fun_lv.getBottom());
                }else {
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


//    /**
//     * 条目点击事件
//     *
//     * @param item
//     */
//    @Event(type = AdapterView.OnItemSelectedListener.class, value = {R.id.fragment_chat_lv})
//    private void itemSelected(View item) {
//
//        Toast.makeText(mActivity, "手贱,点我干嘛哦?😆", Toast.LENGTH_SHORT).show();
//    }

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
        }else {
            chatBean = new ChatBean();
            chatBean.type = 1;
            chatBean.text = reply;
            chatBean.time = System.currentTimeMillis()+"";

            chatBeanArrayList.add(chatBean);
            chatAdapter.notifyDataSetChanged();
            reply_et.setText("");
            fun_lv.setSelection(fun_lv.getBottom());
            getData();
        }


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
