package com.example.administrator.test.base.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.HeaderFooterAdapter;
import com.example.administrator.test.base.adapter.HeaderFooterViewModel;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.koo.loomis.swiperecyclerview.SwipeMenuRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * @param <T>
 * @author koo
 */
public abstract class BaseListActivity<T, P extends IBasePresenter> extends BaseActivity<P> {
    protected int page      = 1;
    protected int pageSize  = 20;
    protected int totalPage = 1;

    protected SwipeMenuRecyclerView   recyclerView;
    protected QuickDelegateAdapter<T> adapter;
    protected HeaderFooterViewModel   headerViewModel, footerViewModel;

    protected SmartRefreshLayout refreshLayout;
    protected LinearLayout       topLay;
    protected LinearLayout       bottomLay;
    protected ConstraintLayout   rootLay;
    protected TextView           emptyTv;
    /**
     * 是否显示recycleView自带的分割线
     */
    private   boolean            isShowDefaultDivider = false;

    /**
     * 是否显示recycleView的item增删动画
     */
    private boolean isShowRvAnimation = true;

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
    public int bindContentLayout() {
        return R.layout.layout_base_list;
    }

    @Override
    public int bindMenu() {
        return 0;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        emptyTv = (TextView) findViewById(R.id.base_pager_list_empty_tv);
        rootLay = (ConstraintLayout) findViewById(R.id.base_pager_list_root);
        topLay = (LinearLayout) findViewById(R.id.base_pager_list_topLay);
        bottomLay = (LinearLayout) findViewById(R.id.base_pager_list_bottomLay);
        recyclerView = (SwipeMenuRecyclerView) findViewById(R.id.base_pager_list_rv);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.base_pager_list_refreshLayout);
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

    protected void initRecycleView() {
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        recyclerView.setRecycledViewPool(viewPool);

        viewPool.setMaxRecycledViews(0, 28);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);

        setRecyclerViewAnimation(delegateAdapter);


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
        if (isShowDefaultDivider) {
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }

        delegateAdapter.setAdapters(adapters);
    }

    /**
     * 设置recycleView的item增删动画
     *
     * @param delegateAdapter
     */
    private void setRecyclerViewAnimation(DelegateAdapter delegateAdapter) {
        if (isShowRvAnimation) {
            //增加动画
            AnimationAdapter animationAdapter = new ScaleInAnimationAdapter(delegateAdapter);
            animationAdapter.setFirstOnly(false);
            animationAdapter.setDuration(300);
            //删除动画
            SlideInLeftAnimator animator = new SlideInLeftAnimator();
            animator.setInterpolator(new AccelerateInterpolator());
            recyclerView.setItemAnimator(animator);
            recyclerView.getItemAnimator().setRemoveDuration(300);

            recyclerView.setAdapter(animationAdapter);
        }
        else {
            recyclerView.setAdapter(delegateAdapter);
        }
    }

    /**
     * 添加自定义分割线
     *
     * @param drawableRes
     */
    public void addCustomDivider(@DrawableRes int drawableRes) {
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, drawableRes));
        recyclerView.addItemDecoration(divider);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void setListener() {
    }

    @Override
    public void onMenuClickListener(int menuId) {

    }

    @Override
    public void initData(Context mContext) {

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
        }
        else {
            hideEmptyView();
        }
    }

    protected void refresh() {
        hideEmptyView();
        page = 1;
        adapter.clear();
        if (totalPage >= page) {
            getData(page, pageSize);
        }
        else {
            stopRefresh();
        }
    }

    protected void loadMore() {
        page++;
        if (totalPage >= page) {
            getData(page, pageSize);
        }
        else {
            stopRefresh();
        }
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
        rootLay.setBackgroundColor(color);
    }

    protected void setLoadMoreEnable(boolean enable) {
        refreshLayout.setEnableLoadmore(enable);
    }

    protected abstract void getData(int page, int pageSize);

    protected abstract QuickDelegateAdapter getAdapter();

    /**
     * 是否显示recycleView自带的分割线
     *
     * @param showDefaultDivider
     */
    public void showDefaultDivider(boolean showDefaultDivider) {
        isShowDefaultDivider = showDefaultDivider;
    }

    /**
     * 是否显示recycleView的增删动画
     *
     * @param showRvAnimation
     */
    public void setShowRvAnimation(boolean showRvAnimation) {
        isShowRvAnimation = showRvAnimation;
    }
}
