package com.example.administrator.test.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.entity.MovieTop250Entity;
import com.example.administrator.test.mvp.contract.MovieTop250Contract;
import com.example.administrator.test.mvp.model.MovieTop250Model;
import com.example.administrator.test.mvp.presenter.MovieTop250Presenter;
import com.example.administrator.test.viewholder.movie.MovieTop250Vh;
import com.example.administrator.test.viewholder.movie.MovieVh;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.fragment
 * @ClassName: MovieUpcomingFragment   即将上映电影
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/3 11:04 AM
 * @UpdateUser:
 * @UpdateDate: 2019/2/3 11:04 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MovieTop250Fragment extends BaseListFragment<MovieTop250Entity.SubjectsEntity, MovieTop250Presenter> implements MovieTop250Contract.View {
    /**
     * 网格列表列数
     */
    private static final int SPAN_COUNT = 3;

    @Override
    protected void getData(int page, int pageSize) {
        presenter.loadData(page, 21);
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<MovieTop250Entity.SubjectsEntity>(getContext(),
                                                                          R.layout.fragment_base_list) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, MovieTop250Entity.SubjectsEntity item, int viewType, int position) {
                ((MovieTop250Vh) holder).setData(item);
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new GridLayoutHelper(SPAN_COUNT);
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new MovieTop250Vh(getContext(), parent, R.layout.movie_top_250_vh_item);
            }
        };
    }

    @Override
    public int bindTopLayout() {
        return 0;
    }

    @Override
    public int bindBottomLayout() {
        return 0;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected MovieTop250Presenter getPresenter() {
        return new MovieTop250Presenter(this, new MovieTop250Model());
    }

    @Override
    public void onLoadSuccess(MovieTop250Entity entity) {
        stopRefresh();
        adapter.addAll(entity.getSubjects());
        maxPage = entity.getTotal();
    }

    @Override
    public void onLoadFailed(String msg) {
        stopRefresh();
    }
}
