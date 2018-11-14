package com.example.administrator.test.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author
 */
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
            if (null == instance) {
                new BaseFragment();
            }
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
        if (null == mClickListener) {
            initClickListener();
        }

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
        if (null == context) {
            context = getActivity();
        }
        return context;
    }

    /**
     * Get base Activity
     *
     * @return
     */
    public BaseActivity getBaseActivity() {
        if (null == baseActivity) {
            baseActivity = (BaseActivity) getActivity();
        }
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
        getBaseActivity().showLongToast(msg);
    }

    /**
     * 显示长时间Toast
     *
     * @param resId
     */
    public void showLongToast(@StringRes int resId) {
        getBaseActivity().showLongToast(resId);
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
     * @param resId
     */
    public void showToast(@StringRes int resId) {
        getBaseActivity().showToast(resId);
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

    /**
     * 设置点击监听器
     *
     * @param view
     */
    public void setClickListener(View view) {
        if (null == mClickListener) {
            initClickListener();
        }
        if (null != view) {
            view.setOnClickListener(mClickListener);
        }
    }
}