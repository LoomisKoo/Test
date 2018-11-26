package com.example.administrator.test.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;
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

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * @param <T>
 * @author koo
 */
public abstract class BaseListActivity<T> extends BaseActivity {
    protected int page = 1;
    protected int pageSize = 20;
    SmartRefreshLayout refreshLayout;
    protected RecyclerView recyclerView;
    protected QuickDelegateAdapter<T> adapter;
    HeaderFooterViewModel headerViewModel, footerViewModel;
    protected LinearLayout topLay, bottomLay;
    protected ConstraintLayout rootLay;
    protected TextView emptyTv;
    protected VirtualLayoutManager layoutManager;


    private int distance;
    private boolean visible = true;

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
        emptyTv = (TextView) findViewById(R.id.base_pager_list_empty_tv);
        rootLay = (ConstraintLayout) findViewById(R.id.base_pager_list_root);
        topLay = (LinearLayout) findViewById(R.id.base_pager_list_topLay);
        bottomLay = (LinearLayout) findViewById(R.id.base_pager_list_bottomLay);
        recyclerView = (RecyclerView) findViewById(R.id.base_pager_list_rv);
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

    private void initRecycleView() {
        layoutManager = new VirtualLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        recyclerView.setRecycledViewPool(viewPool);

        viewPool.setMaxRecycledViews(0, 28);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);


        //增加动画
        ScaleInAnimationAdapter animationAdapter=new ScaleInAnimationAdapter(delegateAdapter);
        animationAdapter.setDuration(300);
        recyclerView.setAdapter(animationAdapter);
        //删除动画
        SlideInLeftAnimator animator=new SlideInLeftAnimator();
        animator.setInterpolator(new OvershootInterpolator());
        recyclerView.setItemAnimator(animator);
        recyclerView.getItemAnimator().setRemoveDuration(300);

        recyclerView.setAdapter(animationAdapter);

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

//        recyclerView.setItemAnimator(new SlideInLeftAnimator());
//        SlideInUpAnimator animator = new SlideInUpAnimator(new OvershootInterpolator(1f));
//        recyclerView.setItemAnimator(animator);

//        recyclerView.getItemAnimator().setAddDuration(1000);
//        recyclerView.getItemAnimator().setRemoveDuration(1000);
//        recyclerView.getItemAnimator().setMoveDuration(1000);
//        recyclerView.getItemAnimator().setChangeDuration(1000);

    }

    private void refreshAnimation() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onMenuClickListener(int menuId) {

    }

    @Override
    public void onEvent(Context mContext) {

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

    private double mDistanceY;

    /**
     * toolbar动画
     */
    private void setToolBarAnimation() {
        //        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        //            @Override
        //            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        //                //滑动的距离
        //                mDistanceY += dy;
        //                //toolbar的高度
        //                int toolbarHeight = getToolBarBottom();
        //
        //                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
        //                if (mDistanceY <= toolbarHeight) {
        //                    float scale = (float) mDistanceY / toolbarHeight;
        //                    float alpha = scale * 255;
        //                    setToolBarBackgroundColor(Color.argb((int) alpha, 128, 0, 0));
        //                } else {
        //                    //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
        //                    //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
        //                    //将标题栏的颜色设置为完全不透明状态
        ////                    setToolBarBackgroundResource(R.color.colorPrimary);
        //                }
        //
        //            }
        //        });


    }


    protected void setRootBackground(int color) {
        rootLay.setBackgroundColor(color);
    }

    protected void setLoadMoreEnable(boolean enable) {
        refreshLayout.setEnableLoadmore(enable);
    }

    protected abstract void getData(int page, int pageSize);

    protected abstract QuickDelegateAdapter getAdapter();
}
