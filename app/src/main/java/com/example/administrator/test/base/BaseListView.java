package com.example.administrator.test.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.HeaderFooterViewModel;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.koo.loomis.swiperecyclerview.SwipeMenuRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @param <T>
 * @author koo
 */
public abstract class BaseListView<T> extends LinearLayout {
    public    boolean isInited = false;
    protected int     page     = 1;
    protected int     pageSize = 20;

    protected QuickDelegateAdapter<T> adapter;
    protected HeaderFooterViewModel   headerViewModel, footerViewModel;

    @BindView(R.id.base_pager_list_topLay)
    LinearLayout          basePagerListTopLay;
    @BindView(R.id.base_pager_list_rv)
    SwipeMenuRecyclerView basePagerListRv;
    @BindView(R.id.base_pager_list_refreshLayout)
    SmartRefreshLayout    basePagerListRefreshLayout;
    @BindView(R.id.base_pager_list_empty_tv)
    TextView              basePagerListEmptyTv;
    @BindView(R.id.base_pager_list_bottomLay)
    LinearLayout          basePagerListBottomLay;
    @BindView(R.id.base_pager_list_root)
    ConstraintLayout      basePagerListRoot;

    public BaseListView(Context context) {
        super(context);
        initView(context);
    }

    /**
     * 初始化View
     *
     * @param context
     */
    private void initView(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.layout_base_list, this);
        ButterKnife.bind(this);

        basePagerListRefreshLayout.setOnRefreshListener(refreshLayout -> refresh());
        basePagerListRefreshLayout.setOnLoadmoreListener(refreshLayout -> loadMore());

        if (getTopLayId() != 0) {
            layoutInflater.inflate(getTopLayId(), basePagerListTopLay, true);
        }
        if (getBottomLayId() != 0) {
            layoutInflater.inflate(getBottomLayId(), basePagerListBottomLay, true);
        }
        initRv();
        postDelayed(() -> basePagerListRefreshLayout.autoRefresh(), 100);
        isInited = true;

    }

    protected int getTopLayId() {
        return 0;
    }

    protected int getBottomLayId() {
        return 0;
    }

    protected void initRv() {
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(getContext());

        basePagerListRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            }
        });

        basePagerListRv.setLayoutManager(layoutManager);

        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        basePagerListRv.setRecycledViewPool(viewPool);

        viewPool.setMaxRecycledViews(0, 28);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);

        basePagerListRv.setAdapter(delegateAdapter);

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

    protected HeaderFooterViewModel getHeaderView() {
        return null;
    }

    protected HeaderFooterViewModel getFooterView() {
        return null;
    }

    protected DelegateAdapter.Adapter getHeadViewAdapter() {
        ArrayList list = new ArrayList<>();
        list.add(headerViewModel.object);
        return new HeaderFooterAdapter(BaseListView.this.getContext(), 119, headerViewModel.layoutId, headerViewModel, list);
    }

    private DelegateAdapter.Adapter getFooterViewAdapter() {
        ArrayList list = new ArrayList<>();
        list.add(footerViewModel.object);
        return new HeaderFooterAdapter(BaseListView.this.getContext(), 120, footerViewModel.layoutId, footerViewModel, list);
    }


    class HeaderFooterAdapter<G> extends QuickDelegateAdapter<G> {
        HeaderFooterViewModel model;
        int                   viewType;

        private HeaderFooterAdapter(Context context, int viewType, int layoutResId, HeaderFooterViewModel footerViewModel, ArrayList list) {
            super(context, layoutResId, list);
            this.model = footerViewModel;
            this.viewType = viewType;
        }

        @Override
        protected void onSetItemData(BaseViewHolder holder, G item, int viewType, int position) {
            model.setData(holder, item);
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new LinearLayoutHelper(1);
        }

        @Override
        public int getItemViewType(int position) {
            return viewType;
        }

    }

    protected void stopRefresh() {
        if (basePagerListRefreshLayout.isRefreshing()) {
            basePagerListRefreshLayout.finishRefresh();
        }
        else if (basePagerListRefreshLayout.isLoading()) {
            basePagerListRefreshLayout.finishLoadmore();
        }
    }

    public void refresh() {
        adapter.clear();
        page = 1;
        getData(page, pageSize);
    }

    protected void loadMore() {
        page++;
        getData(page, pageSize);
    }

    protected void setRefreshEnable(boolean enable) {
        basePagerListRefreshLayout.setEnableRefresh(enable);
    }

    protected void setLoadMoreEnable(boolean enable) {
        basePagerListRefreshLayout.setEnableLoadmore(enable);
    }

    /**
     * 获取适配器
     *
     * @return
     */
    protected abstract QuickDelegateAdapter getAdapter();

    /**
     * 获取数据
     *
     * @param page
     * @param pageSize
     */
    protected abstract void getData(int page, int pageSize);
}
