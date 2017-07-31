package com.knight.helper.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.SharedElementCallback;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.knight.helper.app.MyApplication;

import org.xutils.common.util.LogUtil;
import org.xutils.x;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * Created by lemon on 2017/7/22.
 */

public abstract class BaseFragment extends Fragment implements Serializable {

    public View view;
    public BaseActivity mActivity;
    public MyApplication mApp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        LogUtil.e("BaseFragment---onCreateView");
        mActivity = (BaseActivity) getActivity();

        mApp = mActivity.mApp;

        view = x.view().inject(this, inflater, null); //注入view和事件

        initView(inflater, container, savedInstanceState);

        return view;
    }

    /**
     * 用于初始化控件的方法
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    public abstract void initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("BaseFragment---onResume");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("BaseFragment---onCreate");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.e("BaseFragment---onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.e("BaseFragment---onStart");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.e("BaseFragment---onAttach");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.e("BaseFragment---onActivityCreated");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        LogUtil.e("BaseFragment---onAttach");
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        LogUtil.e("BaseFragment---onAttachFragment");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.e("BaseFragment---setUserVisibleHint");
    }

    public BaseFragment() {
        super();
        LogUtil.e("BaseFragment---BaseFragment");
    }

    @Override
    public String toString() {
        LogUtil.e("BaseFragment---toString");
        return super.toString();

    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        LogUtil.e("BaseFragment---setArguments");
    }

    @Override
    public void setInitialSavedState(SavedState state) {
        super.setInitialSavedState(state);
        LogUtil.e("BaseFragment---setInitialSavedState");
    }

    @Override
    public void setTargetFragment(Fragment fragment, int requestCode) {
        super.setTargetFragment(fragment, requestCode);
        LogUtil.e("BaseFragment---setTargetFragment");
    }

    @Override
    public Context getContext() {
        LogUtil.e("BaseFragment---getContext");
        return super.getContext();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtil.e("BaseFragment---onHiddenChanged");
    }

    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(retain);
        LogUtil.e("BaseFragment---setRetainInstance");
    }

    @Override
    public void setHasOptionsMenu(boolean hasMenu) {
        super.setHasOptionsMenu(hasMenu);
        LogUtil.e("BaseFragment---setHasOptionsMenu");
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        LogUtil.e("BaseFragment---setMenuVisibility");
    }

    @Override
    public boolean getUserVisibleHint() {
        LogUtil.e("BaseFragment---getUserVisibleHint");
        return super.getUserVisibleHint();

    }

    @Override
    public LoaderManager getLoaderManager() {
        LogUtil.e("BaseFragment---getLoaderManager");
        return super.getLoaderManager();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        LogUtil.e("BaseFragment---startActivity");
    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
        LogUtil.e("BaseFragment---startActivity");
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        LogUtil.e("BaseFragment---startActivityForResult");
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        LogUtil.e("BaseFragment---startActivityForResult");
    }

    @Override
    public void startIntentSenderForResult(IntentSender intent, int requestCode, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
        LogUtil.e("BaseFragment---startIntentSenderForResult");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.e("BaseFragment---onActivityResult");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtil.e("BaseFragment---onRequestPermissionsResult");
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        LogUtil.e("BaseFragment---shouldShowRequestPermissionRationale");
        return super.shouldShowRequestPermissionRationale(permission);
    }

//    @Override
//    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
//        LogUtil.e("BaseFragment---setUserVisibleHint");
//        return super.getLayoutInflater(savedInstanceState);
//    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        LogUtil.e("BaseFragment---onInflate");
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        LogUtil.e("BaseFragment---onInflate");
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        LogUtil.e("BaseFragment---onCreateAnimation");
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtil.e("BaseFragment---onViewCreated");
    }

    @Nullable
    @Override
    public View getView() {
        LogUtil.e("BaseFragment---getView");
        return super.getView();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        LogUtil.e("BaseFragment---onViewStateRestored");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtil.e("BaseFragment---onSaveInstanceState");
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        LogUtil.e("BaseFragment---onMultiWindowModeChanged");
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        LogUtil.e("BaseFragment---onPictureInPictureModeChanged");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.e("BaseFragment---onConfigurationChanged");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.e("BaseFragment---onStop");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LogUtil.e("BaseFragment---onLowMemory");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.e("BaseFragment---onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e("BaseFragment---onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.e("BaseFragment---onDetach");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        LogUtil.e("BaseFragment---onCreateOptionsMenu");
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        LogUtil.e("BaseFragment---onPrepareOptionsMenu");
    }

    @Override
    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
        LogUtil.e("BaseFragment---onDestroyOptionsMenu");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LogUtil.e("BaseFragment---onOptionsItemSelected");
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        LogUtil.e("BaseFragment---onOptionsMenuClosed");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        LogUtil.e("BaseFragment---onCreateContextMenu");
    }

    @Override
    public void registerForContextMenu(View view) {
        super.registerForContextMenu(view);
        LogUtil.e("BaseFragment---registerForContextMenu");
    }

    @Override
    public void unregisterForContextMenu(View view) {
        super.unregisterForContextMenu(view);
        LogUtil.e("BaseFragment---unregisterForContextMenu");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        LogUtil.e("BaseFragment---onContextItemSelected");
        return super.onContextItemSelected(item);
    }

    @Override
    public void setEnterSharedElementCallback(SharedElementCallback callback) {
        super.setEnterSharedElementCallback(callback);
        LogUtil.e("BaseFragment---setEnterSharedElementCallback");
    }

    @Override
    public void setExitSharedElementCallback(SharedElementCallback callback) {
        super.setExitSharedElementCallback(callback);
        LogUtil.e("BaseFragment---setExitSharedElementCallback");
    }

    @Override
    public void setEnterTransition(Object transition) {
        super.setEnterTransition(transition);
        LogUtil.e("BaseFragment---setEnterTransition");
    }

    @Override
    public Object getEnterTransition() {
        LogUtil.e("BaseFragment---getEnterTransition");
        return super.getEnterTransition();
    }

    @Override
    public void setReturnTransition(Object transition) {
        super.setReturnTransition(transition);
        LogUtil.e("BaseFragment---setReturnTransition");
    }

    @Override
    public Object getReturnTransition() {
        LogUtil.e("BaseFragment---getReturnTransition");
        return super.getReturnTransition();
    }

    @Override
    public void setExitTransition(Object transition) {
        super.setExitTransition(transition);
        LogUtil.e("BaseFragment---setExitTransition");
    }

    @Override
    public Object getExitTransition() {
        LogUtil.e("BaseFragment---getExitTransition");
        return super.getExitTransition();
    }

    @Override
    public void setReenterTransition(Object transition) {
        super.setReenterTransition(transition);
        LogUtil.e("BaseFragment---setReenterTransition");
    }

    @Override
    public Object getReenterTransition() {
        LogUtil.e("BaseFragment---getReenterTransition");
        return super.getReenterTransition();
    }

    @Override
    public void setSharedElementEnterTransition(Object transition) {
        super.setSharedElementEnterTransition(transition);
        LogUtil.e("BaseFragment---setSharedElementEnterTransition");
    }

    @Override
    public Object getSharedElementEnterTransition() {
        LogUtil.e("BaseFragment---getSharedElementEnterTransition");
        return super.getSharedElementEnterTransition();
    }

    @Override
    public void setSharedElementReturnTransition(Object transition) {
        super.setSharedElementReturnTransition(transition);
        LogUtil.e("BaseFragment---setSharedElementReturnTransition");
    }

    @Override
    public Object getSharedElementReturnTransition() {
        LogUtil.e("BaseFragment---getSharedElementReturnTransition");
        return super.getSharedElementReturnTransition();
    }

    @Override
    public void setAllowEnterTransitionOverlap(boolean allow) {
        super.setAllowEnterTransitionOverlap(allow);
        LogUtil.e("BaseFragment---setAllowEnterTransitionOverlap");
    }

    @Override
    public boolean getAllowEnterTransitionOverlap() {
        LogUtil.e("BaseFragment---getAllowEnterTransitionOverlap");
        return super.getAllowEnterTransitionOverlap();
    }

    @Override
    public void setAllowReturnTransitionOverlap(boolean allow) {
        super.setAllowReturnTransitionOverlap(allow);
        LogUtil.e("BaseFragment---setAllowReturnTransitionOverlap");
    }

    @Override
    public boolean getAllowReturnTransitionOverlap() {
        LogUtil.e("BaseFragment---getAllowReturnTransitionOverlap");
        return super.getAllowReturnTransitionOverlap();
    }

    @Override
    public void postponeEnterTransition() {
        super.postponeEnterTransition();
        LogUtil.e("BaseFragment---postponeEnterTransition");
    }

    @Override
    public void startPostponedEnterTransition() {
        super.startPostponedEnterTransition();
        LogUtil.e("BaseFragment---startPostponedEnterTransition");
    }

    @Override
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(prefix, fd, writer, args);
        LogUtil.e("BaseFragment---dump");
    }
}
