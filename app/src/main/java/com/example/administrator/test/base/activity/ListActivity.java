package com.example.administrator.test.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.HeaderFooterAdapter;
import com.example.administrator.test.base.adapter.HeaderFooterViewModel;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class ListActivity<T> extends BaseActivity {
    protected int page = 1;
    protected int pageSize = 20;
    SmartRefreshLayout refreshLayout;
    protected RecyclerView recyclerView;
    protected QuickDelegateAdapter<T> adapter;
    HeaderFooterViewModel headerViewModel, footerViewModel;
    protected LinearLayout topLay, bottomLay;
    protected ConstraintLayout mainLay;
    protected TextView emptyTv;
    protected VirtualLayoutManager layoutManager;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParameter(Bundle parameter) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.layout_base_list;
    }

    @Override
    public int bindMenu() {
        return 0;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        emptyTv = findViewById(R.id.base_list_empty_tv);
        mainLay = findViewById(R.id.base_list_mainLay);
        topLay = findViewById(R.id.base_list_topLay);
        bottomLay = findViewById(R.id.base_list_bottomLay);
        recyclerView = findViewById(R.id.base_list_rv);
        refreshLayout = findViewById(R.id.act_nav_list_refreshLayout);
        refreshLayout.setOnRefreshListener(refreshLayout -> refresh());
        refreshLayout.setOnLoadmoreListener(refreshLayout -> loadMore());
        refreshLayout.autoRefresh();
        if (getTopLayId() != 0) {
            getLayoutInflater().inflate(getTopLayId(), topLay, true);
        }
        if (getBottomLayId() != 0) {
            getLayoutInflater().inflate(getBottomLayId(), bottomLay, true);
        }

        initRecycleView();
    }

    private void initRecycleView() {
        layoutManager = new VirtualLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        recyclerView.setRecycledViewPool(viewPool);

        viewPool.setMaxRecycledViews(0, 28);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);

        recyclerView.setAdapter(delegateAdapter);

        final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        headerViewModel = getHeaderView();
        if (headerViewModel != null) {
            adapters.add(getHeadViewAdapter());
        }

        adapters.add(adapter = getAdapter());

        footerViewModel = getFooterView();
        if (footerViewModel != null) {
            adapters.add(getFooterViewAdapter());
        }

        delegateAdapter.setAdapters(adapters);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onMenuClickListener(int menuId) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    protected boolean isRefreshFinish() {
        return refreshLayout.getState() == RefreshState.None;
    }

    protected void stopRefresh() {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        } else if (refreshLayout.isLoading()) {
            refreshLayout.finishLoadmore();
        }
    }

    protected int getTopLayId() {
        return 0;
    }

    protected int getBottomLayId() {
        return 0;
    }

    protected void hideEmptyView() {
        if (emptyTv.getVisibility() == View.VISIBLE) {
            emptyTv.setVisibility(View.INVISIBLE);
        }
    }

    protected void showEmptyView(String text, @DrawableRes int resId) {
        emptyTv.setVisibility(View.VISIBLE);
        emptyTv.setText(text);
        emptyTv.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
    }

    public void checkEmpty(String text, @DrawableRes int resId) {
        if (adapter.getItemCount() == 0) {
            showEmptyView(text, resId);
        } else {
            hideEmptyView();
        }
    }

    protected void refresh() {
        hideEmptyView();
        page = 1;
        adapter.clear();
        getData(page, pageSize);
    }

    protected void loadMore() {
        page++;
        getData(page, pageSize);
    }

    protected void setRefreshEnable(boolean enable) {
        refreshLayout.setEnableRefresh(enable);
    }

    protected HeaderFooterViewModel getHeaderView() {
        return null;
    }

    protected HeaderFooterViewModel getFooterView() {
        return null;
    }

    protected DelegateAdapter.Adapter getHeadViewAdapter() {
        ArrayList list = new ArrayList<>();
        list.add(headerViewModel.object);
        return new HeaderFooterAdapter(this, 119, headerViewModel.layoutId, headerViewModel, list);
    }

    private DelegateAdapter.Adapter getFooterViewAdapter() {
        ArrayList list = new ArrayList<>();
        list.add(footerViewModel.object);
        return new HeaderFooterAdapter(this, 120, footerViewModel.layoutId, footerViewModel, list);
    }

    protected void setRootBackground(int color) {
        mainLay.setBackgroundColor(color);
    }

    protected void setLoadMoreEnable(boolean enable) {
        refreshLayout.setEnableLoadmore(enable);
    }

    protected abstract void getData(int page, int pageSize);

    protected abstract QuickDelegateAdapter getAdapter();
}
