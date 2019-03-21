package com.example.administrator.test.base.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
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

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * @param <T>
 * @author koo
 */
public abstract class BaseListActivity<T, P extends IBasePresenter> extends BaseViewActivity<P> {
    protected int page     = 1;
    protected int pageSize = 20;
    protected int maxPage  = 1;

    protected QuickDelegateAdapter<T> adapter;
    protected HeaderFooterViewModel   headerViewModel, footerViewModel;

    @BindView(R.id.base_pager_list_topLay)
    LinearLayout basePagerListTopLay;
    @BindView(R.id.base_pager_list_rv)
    protected SwipeMenuRecyclerView basePagerListRv;
    @BindView(R.id.base_pager_list_refreshLayout)
    SmartRefreshLayout basePagerListRefreshLayout;
    @BindView(R.id.base_pager_list_empty_tv)
    TextView           basePagerListEmptyTv;
    @BindView(R.id.base_pager_list_bottomLay)
    LinearLayout       basePagerListBottomLay;
    @BindView(R.id.base_pager_list_root)
    ConstraintLayout   basePagerListRoot;
    /**
     * 是否显示recycleView自带的分割线
     */
    private boolean isShowDefaultDivider = false;

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
        basePagerListRefreshLayout.setOnRefreshListener(refreshLayout -> refresh());
        basePagerListRefreshLayout.setOnLoadmoreListener(refreshLayout -> loadMore());
        basePagerListRefreshLayout.autoRefresh();
        //不启用列表惯性滑动到底部时自动加载更多
        basePagerListRefreshLayout.setEnableAutoLoadmore(false);
        if (getTopLayId() != 0) {
            getLayoutInflater().inflate(getTopLayId(), basePagerListTopLay, true);
        }
        if (getBottomLayId() != 0) {
            getLayoutInflater().inflate(getBottomLayId(), basePagerListBottomLay, true);
        }

        initRecycleView();
    }

    protected void initRecycleView() {
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);

        basePagerListRv.setLayoutManager(layoutManager);
        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        basePagerListRv.setRecycledViewPool(viewPool);

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
            basePagerListRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
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
            basePagerListRv.setItemAnimator(animator);
            basePagerListRv.getItemAnimator()
                           .setRemoveDuration(300);

            basePagerListRv.setAdapter(animationAdapter);
        }
        else {
            basePagerListRv.setAdapter(delegateAdapter);
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
        basePagerListRv.addItemDecoration(divider);
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
        return basePagerListRefreshLayout.getState() == RefreshState.None;
    }

    protected void stopRefresh() {
        if (basePagerListRefreshLayout.isRefreshing()) {
            basePagerListRefreshLayout.finishRefresh();
        }
        else if (basePagerListRefreshLayout.isLoading()) {
            basePagerListRefreshLayout.finishLoadmore();
        }
    }

    protected int getTopLayId() {
        return 0;
    }

    protected int getBottomLayId() {
        return 0;
    }

    protected void hideEmptyView() {
        if (basePagerListEmptyTv.getVisibility() == View.VISIBLE) {
            basePagerListEmptyTv.setVisibility(View.INVISIBLE);
        }
    }

    protected void showEmptyView(String text, @DrawableRes int resId) {
        basePagerListEmptyTv.setVisibility(View.VISIBLE);
        basePagerListEmptyTv.setText(text);
        basePagerListEmptyTv.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
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
        basePagerListRefreshLayout.setEnableRefresh(enable);
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
        basePagerListRoot.setBackgroundColor(color);
    }

    protected void setLoadMoreEnable(boolean enable) {
        basePagerListRefreshLayout.setEnableLoadmore(enable);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.bind(this)
                   .unbind();
        super.onDestroy();
    }
}
