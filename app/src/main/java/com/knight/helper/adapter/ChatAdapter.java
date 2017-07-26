package com.knight.helper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.knight.helper.R;
import com.knight.helper.bean.ChatBean;
import com.knight.helper.utils.TimeUtils;
import com.knight.helper.view.CircleImageView;

import java.util.ArrayList;

/**
 * Created by lemon on 2017/7/23.
 */

public class ChatAdapter extends ListBaseAdapter {
    public ChatAdapter(Context context, ArrayList<?> list) {
        super(context, list);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder tag = null;
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(context)
                    .inflate(R.layout.item_chat
                            , null);
            tag = new Holder();
            tag.img_other = (CircleImageView) convertView.findViewById(R.id.item_chat_avatar_other);
            tag.img_self = (CircleImageView) convertView.findViewById(R.id.item_chat_avatar_self);
            tag.tv_time = (TextView) convertView.findViewById(R.id.item_chat_tv_time);
            tag.tv_other = (TextView) convertView.findViewById(R.id.item_chat_tv_other);
            tag.tv_self = (TextView) convertView.findViewById(R.id.item_chat_tv_self);
            tag.rl_other = (RelativeLayout) convertView.findViewById(R.id.item_chat_rl_other);
            tag.rl_self = (RelativeLayout) convertView.findViewById(R.id.item_chat_rl_self);

            convertView.setTag(tag);
        }
        //设置数据

        Holder holder = (Holder) convertView.getTag();

        ChatBean chatBean = (ChatBean) list.get(position);

        holder.rl_other.setVisibility(View.GONE);
        holder.rl_self.setVisibility(View.GONE);
        switch (chatBean.type){

            case -1://错误数据

                break;

            case 0://机器人
                holder.rl_other.setVisibility(View.VISIBLE);
                holder.tv_other.setText(chatBean.text);
                break;
            case 1://人
                holder.rl_self.setVisibility(View.VISIBLE);
                holder.tv_self.setText(chatBean.text);
                break;
        }

        holder.tv_time.setText(TimeUtils.getFormateTime(Long.parseLong(chatBean.time),"-",":"));


        return convertView;
    }


    public static class Holder {

        public CircleImageView img_other,img_self;
        public TextView tv_time, tv_other, tv_self;
        public RelativeLayout rl_other,rl_self;

    }
}
