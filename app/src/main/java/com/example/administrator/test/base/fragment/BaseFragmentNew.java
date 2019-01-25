package com.example.administrator.test.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseActivity;
import com.example.administrator.test.base.adapter.HeaderFooterViewModel;
import com.example.administrator.test.manager.FragmentUserVisibleManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

/**
 * @author koo
 */
public abstract class BaseFragmentNew<P> extends Fragment implements FragmentUserVisibleManager.UserVisibleCallback {
    private FragmentUserVisibleManager userVisibleManager;

    /**
     * 是否已经加载数据
     */
    protected boolean isLoadData = false;

    protected P presenter;

    protected BaseActivity mActivity;

    protected TextView     emptyTv;
    protected LinearLayout topLay, bottomLay, llContent;
    protected ConstraintLayout      rootLay;
    protected SmartRefreshLayout    refreshLayout;
    protected HeaderFooterViewModel headerViewModel, footerViewModel;

    public BaseFragmentNew() {
        userVisibleManager = new FragmentUserVisibleManager(this, this);
    }

    /**
     * 初始化View
     *
     * @param view
     */
    protected void initRootView(View view) {
        emptyTv = view.findViewById(R.id.base_pager_list_empty_tv);
        rootLay = view.findViewById(R.id.base_pager_list_root);
        topLay = view.findViewById(R.id.base_pager_list_topLay);
        bottomLay = view.findViewById(R.id.base_pager_list_bottomLay);
        llContent = view.findViewById(R.id.base_pager_list_ll);
        refreshLayout = view.findViewById(R.id.base_pager_list_refreshLayout);
        refreshLayout.setOnRefreshListener(refreshLayout -> refreshData());
        refreshLayout.setOnLoadmoreListener(refreshLayout -> loadMoreData());
        initMainLayout();
    }

    /**
     * 初始化主布局
     */
    private void initMainLayout() {
        if (0 != bindContentLayout()) {
            getLayoutInflater().inflate(bindContentLayout(), llContent);
        }
        if (0 != bindTopLayout()) {
            getLayoutInflater().inflate(bindTopLayout(), topLay);
        }
        if (0 != bindBottomLayout()) {
            getLayoutInflater().inflate(bindBottomLayout(), bottomLay);
        }
    }

    protected void refreshData() {
    }

    protected void loadMoreData() {

    }

    protected void setRefreshEnable(boolean enable) {
        refreshLayout.setEnableRefresh(enable);
    }

    /**
     * 隐藏空列表提示
     */
    protected void hideEmptyView() {
        if (emptyTv.getVisibility() == View.VISIBLE) {
            emptyTv.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 显示空列表提示
     *
     * @param text
     * @param resId
     */
    protected void showEmptyView(String text, @DrawableRes int resId) {
        emptyTv.setVisibility(View.VISIBLE);
        emptyTv.setText(text);
        emptyTv.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
    }

    /**
     * 检查列表是否为空
     *
     * @param text
     * @param resId
     */
    public void checkEmpty(String text, @DrawableRes int resId) {
    }

    protected boolean isRefreshFinish() {
        return refreshLayout.getState() == RefreshState.None;
    }

    protected void stopRefresh() {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }
        else if (refreshLayout.isLoading()) {
            refreshLayout.finishLoadmore();
        }
    }

    /**
     * [绑定内容布局]
     *
     * @return
     */
    public abstract int bindContentLayout();

    /**
     * [绑定顶部布局]
     *
     * @return
     */
    public abstract int bindTopLayout();

    /**
     * [绑定底部布局]
     *
     * @return
     */
    public abstract int bindBottomLayout();

    protected void setRootBackground(int color) {
        rootLay.setBackgroundColor(color);
    }

    protected void setLoadMoreEnable(boolean enable) {
        refreshLayout.setEnableLoadmore(enable);
    }

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
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRootView(view);
        initView(llContent);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = getPresenter();
        userVisibleManager.activityCreated();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        userVisibleManager.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void setWaitingShowToUser(boolean waitingShowToUser) {
        userVisibleManager.setWaitingShowToUser(waitingShowToUser);
    }

    @Override
    public boolean isWaitingShowToUser() {
        return userVisibleManager.isWaitingShowToUser();
    }

    @Override
    public boolean isVisibleToUser() {
        return userVisibleManager.isVisibleToUser();
    }

    @Override
    public void callSuperSetUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {
        if (isVisibleToUser && !isLoadData) {
            initData();
            isLoadData = true;
        }
    }

    /**
     * 获取presenter实例
     *
     * @return
     */
    protected abstract P getPresenter();

    protected abstract void initView(View view);

    protected void initData() {
        refreshLayout.autoRefresh();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        userVisibleManager.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        userVisibleManager.pause();
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
        isLoadData = false;
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
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT)
             .show();
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showLongToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG)
             .show();
    }

    /**
     * 显示Toast
     *
     * @param resId
     */
    public void showToast(@StringRes int resId) {
        Toast.makeText(getContext(), getResources().getString(resId), Toast.LENGTH_SHORT)
             .show();
    }

    /**
     * 显示长时间Toast
     *
     * @param resId
     */
    public void showLongToast(@StringRes int resId) {
        Toast.makeText(getContext(), getResources().getString(resId), Toast.LENGTH_LONG)
             .show();
    }

}