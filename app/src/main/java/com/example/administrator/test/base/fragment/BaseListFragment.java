package com.example.administrator.test.base.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseActivity;
import com.example.administrator.test.base.adapter.HeaderFooterAdapter;
import com.example.administrator.test.base.adapter.HeaderFooterViewModel;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * @author koo
 */
public abstract class BaseListFragment<T, P> extends BaseFragment<P> {


    protected int page     = 1;
    protected int pageSize = 20;
    protected int maxPage  = 1;

    protected RecyclerView            recyclerView;
    protected QuickDelegateAdapter<T> adapter;
    protected HeaderFooterViewModel   headerViewModel, footerViewModel;

    protected BaseActivity mActivity;

    protected TextView     emptyTv;
    protected LinearLayout topLay, bottomLay;
    protected ConstraintLayout     rootLay;
    protected SmartRefreshLayout   refreshLayout;
    protected VirtualLayoutManager layoutManager;

    /**
     * 是否显示recycleView自带的分割线
     */
    private boolean isShowDefaultDivider = false;

    /**
     * 是否显示recycleView的item增删动画
     */
    private boolean isShowRvAnimation = true;


    @Override
    protected int setContentLayout() {
        return R.layout.fragment_base_list;
    }

    /**
     * 初始化View
     *
     * @param view
     */
    @Override
    protected void initView(View view) {
        emptyTv = view.findViewById(R.id.base_pager_list_empty_tv);
        rootLay = view.findViewById(R.id.base_pager_list_root);
        topLay = view.findViewById(R.id.base_pager_list_topLay);
        bottomLay = view.findViewById(R.id.base_pager_list_bottomLay);
        recyclerView = view.findViewById(R.id.base_pager_list_rv);
        refreshLayout = view.findViewById(R.id.base_pager_list_refreshLayout);
        refreshLayout.setOnRefreshListener(refreshLayout -> refresh());
        refreshLayout.setOnLoadmoreListener(refreshLayout -> loadMore());
        refreshLayout.autoRefresh();

        initRecycleView();
    }

    /**
     * 初始化recycleView
     */
    private void initRecycleView() {
        layoutManager = new VirtualLayoutManager(getContext());

        //添加Android自带的分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

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
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        }

        delegateAdapter.setAdapters(adapters);
    }

    protected DelegateAdapter.Adapter getHeadViewAdapter() {
        ArrayList list = new ArrayList<>();
        list.add(headerViewModel.object);
        return new HeaderFooterAdapter(getContext(), 119, headerViewModel.layoutId, headerViewModel, list);
    }

    private DelegateAdapter.Adapter getFooterViewAdapter() {
        ArrayList list = new ArrayList<>();
        list.add(footerViewModel.object);
        return new HeaderFooterAdapter(getContext(), 120, footerViewModel.layoutId, footerViewModel, list);
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
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), drawableRes));
        recyclerView.addItemDecoration(divider);
    }

    protected void refresh() {
        hideEmptyView();
        page = 0;
        adapter.clear();
        if (maxPage >= page) {
            getData(page, pageSize);
        }
        else {
            stopRefresh();
        }
    }

    protected void loadMore() {
        page++;
        if (maxPage > page) {
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
        if (adapter.getItemCount() == 0) {
            showEmptyView(text, resId);
        }
        else {
            hideEmptyView();
        }
    }

    /**
     * 是否显示recycleView自带的分割线
     *
     * @param showDefaultDivider
     */
    public void showDefaultDivider(boolean showDefaultDivider) {
        isShowDefaultDivider = showDefaultDivider;
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
     * 是否显示recycleView的增删动画
     *
     * @param showRvAnimation
     */
    public void setShowRvAnimation(boolean showRvAnimation) {
        isShowRvAnimation = showRvAnimation;
    }

    protected void setRootBackground(int color) {
        rootLay.setBackgroundColor(color);
    }

    protected void setLoadMoreEnable(boolean enable) {
        refreshLayout.setEnableLoadmore(enable);
    }

    protected abstract void getData(int page, int pageSize);

    protected abstract QuickDelegateAdapter getAdapter();

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
            return inflater.inflate(setContentLayout(), container, false);
        }
        return null;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
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
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}