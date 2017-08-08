package com.knight.helper.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.knight.helper.R;

import java.io.Serializable;

/**
 * Created by lemon on 2017/8/4.
 */

public class InformationView extends LinearLayout implements Serializable {

    private View rootView;


    TextView leftTextView;
    TextView rightTextView;

    ImageView leftImg;
    ImageView rightImg;

    public InformationView(Context context) {
        this(context, null);
    }

    public InformationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InformationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs
                , R.styleable.InformationView);

        rootView = LayoutInflater
                .from(context)
                .inflate(R.layout.view_mine_item, this, true);

        leftTextView = (TextView) rootView.findViewById(R.id.view_mine_information_tv_left);
        rightTextView = (TextView) rootView.findViewById(R.id.view_mine_information_tv_right);

        leftImg = (ImageView) rootView.findViewById(R.id.view_mine_information_iv_left);
        rightImg = (ImageView) rootView.findViewById(R.id.view_mine_information_iv_right);

        int resourceId = -1;
        int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                /**
                 * 左边的文字
                 */
                case R.styleable.InformationView_leftText:
                    resourceId = typedArray.getResourceId(
                            R.styleable.InformationView_leftText, 0);

                    leftTextView.setText(resourceId > 0 ? typedArray.getResources().getText(
                            resourceId) : typedArray
                            .getString(R.styleable.InformationView_leftText));
                    break;

                /**
                 * 右边的图片
                 */
                case R.styleable.InformationView_leftSrc:
                    resourceId = typedArray.getResourceId(
                            R.styleable.InformationView_leftSrc, 0);

                    if (resourceId > 0) {
                        leftImg.setImageResource(resourceId);
                    } else {
                        leftImg.setVisibility(GONE);
                    }

                    //leftImg.setImageResource(resourceId > 0 ? resourceId : R.mipmap.image_user_default);
                    break;

                /**
                 * 右边的文字
                 */
                case R.styleable.InformationView_rightText:
                    resourceId = typedArray.getResourceId(
                            R.styleable.InformationView_rightText, 0);

                    rightTextView.setText(resourceId > 0 ? typedArray.getResources().getText(
                            resourceId) : typedArray
                            .getString(R.styleable.InformationView_rightText));
                    break;

                /**
                 * 左边的文字
                 */
                case R.styleable.InformationView_rightSrc:
                    resourceId = typedArray.getResourceId(
                            R.styleable.InformationView_rightSrc, 0);

                    if (resourceId > 0) {
                        rightImg.setImageResource(resourceId);
                    } else {
                        rightImg.setVisibility(GONE);
                    }

//                    rightImg.setImageResource(resourceId > 0 ? resourceId : R.mipmap.image_user_default);
                    break;
            }
        }

        //addView(rootView);//addView()针对的应该是没有inflate（）方法的时候
//        addView(leftTextView);
//        addView(leftImg);
//        addView(rightTextView);
//        addView(rightImg);

        typedArray.recycle();
        //onFinishInflate();

    }


    /**
     * 获取左部文本框
     *
     * @return TextView
     */
    public TextView getLeftTextView() {
        return leftTextView;
    }

    /**
     * 获取右部文本框
     *
     * @return TextView
     */
    public TextView getRightTextView() {
        return rightTextView;
    }

    /**
     * 获取左部ImageView
     *
     * @return ImageView
     */
    public ImageView getLeftImg() {
        return this.leftImg;
    }

    /**
     * 获取右部ImageView
     *
     * @return ImageView
     */
    public ImageView getRightImg() {
        return rightImg;
    }
}
