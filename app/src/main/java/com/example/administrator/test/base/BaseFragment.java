package com.example.administrator.test.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseFragment extends Fragment {

    private Context context;
    private static BaseFragment instance;
    private BaseActivity baseActivity;

    View.OnClickListener mClickListener;

    private static final String STATE_SAVE_OR_HIDDEN = "STATE_SAVE_OR_HIDDEN";

    public BaseFragment() {
        instance = this;
    }

    public static BaseFragment newInstance() {
        synchronized (BaseFragment.class) {
            if (null == instance)
                new BaseFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    protected void initView() {

    }

    protected void initRegister() {
        if (null == mClickListener) initClickListener();

    }

    private void initClickListener() {
        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewClick(v);
            }
        };
    }

    protected void onViewClick(View view) {

    }

    private void init(Bundle savedInstanceState) {
//        getBaseActivity().setToolBarItemClickListener(this);

        //处理内存重启导致的多个Fragment重叠问题
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_OR_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Override
    public Context getContext() {
        if (null == context) context = getActivity();
        return context;
    }

    /**
     * Get base Activity
     *
     * @return
     */
    public BaseActivity getBaseActivity() {
        if (null == baseActivity) baseActivity = (BaseActivity) getActivity();
        return baseActivity;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_OR_HIDDEN, isHidden());
        super.onSaveInstanceState(outState);
    }

    public void reloadData() {

    }

    public boolean onBackPressed() {
        return false;
    }

    public void currFocused(int index) {

    }

    /**
     * 显示长时间Toast
     *
     * @param msg
     */
    public void showLongToast(String msg) {
//        getBaseActivity().showLongToast(msg);
    }

    /**
     * 显示长时间Toast
     *
     * @param msg
     */
    public void showLongToast(String msg, int code) {
//        getBaseActivity().showLongToast(msg, code);
    }

    /**
     * 显示长时间Toast
     *
     * @param resId
     */
    public void showLongToast(@StringRes int resId) {
//        getBaseActivity().showLongToast(resId);
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        getBaseActivity().showToast(msg);
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    public void showToast(int code, String msg) {
        getBaseActivity().showToast(code, msg);
    }

    /**
     * 显示Toast
     *
     * @param resId
     */
    public void showToast(@StringRes int resId) {
        getBaseActivity().showToast(resId);
    }

    /**
     * 显示Toast
     *
     * @param resId
     */
    public void showToast(@StringRes int resId, int code) {
        showToast(code, getResources().getString(resId));
    }

    /**
     * show the progress dialog
     * <p/>
     * the dialog can cancle when touched outside the window
     * and with the{@link KeyEvent#KEYCODE_BACK BACK} key
     *
     * @param msg
     */
    public void showProgress(String msg) {
        getBaseActivity().showProgress(msg, false, true);
    }

    /**
     * show the progress dialog
     *
     * @param msg                    msg
     * @param touchOutsideCancleable the dialog should be canceled when touched outside the window.
     * @param cancleable             this dialog is cancelable with the {@link KeyEvent#KEYCODE_BACK BACK} key.
     */
    public void showProgress(String msg, boolean touchOutsideCancleable, boolean cancleable) {
        getBaseActivity().showProgress(msg, touchOutsideCancleable, cancleable);
    }

    /**
     * hide the progress dialog
     */
    public void hideProgress() {
        getBaseActivity().hideProgress();
    }

    /**
     * 设置标题栏左导航按钮图标
     *
     * @param resId 资源Id
     */
    protected void setToolBarLeftItemIco(@DrawableRes int resId) {
//        getBaseActivity().setToolBarLeftItemIco(resId);
    }

    /**
     * 设置标题栏右导航按钮图标
     *
     * @param resId 资源Id
     */
    protected void setToolBarRightItemIco(@DrawableRes int resId) {
//        getBaseActivity().setToolBarRightItemIco(resId);
    }

    /**
     * 设置标题栏左导航按钮图标和文字
     *
     * @param resId 资源Id
     * @param text  文本
     */
    protected void setToolBarLeftItem(@DrawableRes int resId, String text) {
//        getBaseActivity().setToolBarLeftItem(resId, text);
    }

    /**
     * 设置标题栏右导航按钮图标和文字
     *
     * @param resId 资源Id
     * @param text  文本
     */
    protected void setToolBarRightItem(@DrawableRes int resId, String text) {
//        getBaseActivity().setToolBarRightItem(resId, text);
    }

    /**
     * 设置标题
     *
     * @param text
     */
    protected void setToolBarTitle(String text) {
//        getBaseActivity().setToolBarTitle(text);
    }

    /**
     * 设置标题
     *
     * @param resId
     */
    protected void setToolBarTitle(@StringRes int resId) {
//        getBaseActivity().setToolBarTitle(resId);
    }

    @Override
    public boolean onToolBarLeftItemClick(View view) {
        return false;
    }

    @Override
    public boolean onToolBarRightItemClick(View view) {
        return false;
    }

    @Override
    public boolean onToolBarRight2ItemClick(View view) {
        return false;
    }

    /**
     * 设置点击监听器
     *
     * @param view
     */
    public void setClickListener(View view) {
        if (null == mClickListener) initClickListener();
        if (null != view)
            view.setOnClickListener(mClickListener);
    }