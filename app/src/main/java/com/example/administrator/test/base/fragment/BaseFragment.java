package com.example.administrator.test.base.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.test.base.activity.BaseActivity;

/**
 * @author
 */
public abstract class BaseFragment<P> extends Fragment {
    protected boolean isInitView = false;

    protected P presenter;

    protected BaseActivity mActivity;

    /**
     * 设置内容布局
     *
     * @return
     */
    protected abstract int setContentLayout();

    /**
     * 初始化View
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 获取presenter实例
     *
     * @return
     */
    protected abstract P getPresenter();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (0 != setContentLayout()) {
            View view = inflater.inflate(setContentLayout(), container, false);
            ButterKnife.bind(this, view);
            return view;
        }
        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        //视图创建完成，将变量置为true
        isInitView = true;
        //如果Fragment可见进行数据加载
        if (getUserVisibleHint()) {
            initData();
            isInitView = false;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = getPresenter();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //视图变为可见并且是第一次加载
        if (isInitView && isVisibleToUser) {
            initData();
            isInitView = false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        isInitView = false;
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showLongToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示Toast
     *
     * @param resId
     */
    public void showToast(@StringRes int resId) {
        Toast.makeText(getContext(), getResources().getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示长时间Toast
     *
     * @param resId
     */
    public void showLongToast(@StringRes int resId) {
        Toast.makeText(getContext(), getResources().getString(resId), Toast.LENGTH_LONG).show();
    }

}