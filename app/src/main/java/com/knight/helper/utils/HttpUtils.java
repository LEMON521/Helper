package com.knight.helper.utils;

import android.content.Context;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;

/**
 * Created by lemon on 2017/7/22.
 */

public class HttpUtils implements Serializable{

    private OnSetData mOnCallBack;
    private Context mActivity;

    // 数据接口抽象方法
    public interface OnSetData {
        //abstract ArrayList<KnowledgeBean.ItemsDataDao> cacheItemsDataList();

        /**
         * 成功的方法
         * @param strJson 返回结果
         */
        abstract void onSuccess(String strJson);

        /**
         * 失败的方法
         * @param ex
         * @param isOnCallback
         */
        abstract void onError(Throwable ex, boolean isOnCallback);

        /**
         * 取消的方法
         * @param cex
         */
        abstract void onCancelled(Callback.CancelledException cex);

        /**
         * 最终完成的方法
         */
        abstract void onFinished();

    }

    // 数据接口设置,数据源接口传入
    public void OnCallBack(OnSetData back) {
        mOnCallBack = back;
    }


    public void post(Context activity, RequestParams params) {
        mActivity = activity;
//        params.addBodyParameter("token", SPUtil.getToken(activity));
//        params.addBodyParameter("appid", SPUtil.getAppid(activity));
//        params.addBodyParameter("secret", SPUtil.getSecret(activity));
        LogUtil.e("================params============" + params.toString());
        x.http().post(params, callBack);

    }

    public void get(Context activity, RequestParams params) {
        mActivity = activity;
//        params.addBodyParameter("token", SPUtil.getToken(activity));
//        params.addBodyParameter("appid", SPUtil.getAppid(activity));
//        params.addBodyParameter("secret", SPUtil.getSecret(activity));
        LogUtil.e("================params============" + params.toString());
        x.http().get(params, callBack);

    }

    private Callback.CommonCallback callBack = new Callback.CommonCallback<String>() {
        @Override
        public void onSuccess(String result) {
            mOnCallBack.onSuccess(result);
        }
        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            mOnCallBack.onError(ex, isOnCallback);
        }
        @Override
        public void onCancelled(CancelledException cex) {
            mOnCallBack.onCancelled(cex);
        }

        @Override
        public void onFinished() {
            mOnCallBack.onFinished();
        }
    };
}
