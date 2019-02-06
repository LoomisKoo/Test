package com.example.administrator.test.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.entity.MovieBriefInformation;
import com.example.administrator.test.entity.MoviewHitEntity;
import com.example.administrator.test.mvp.contract.MovieHitContract;
import com.example.administrator.test.mvp.contract.MovieUpcomingContract;
import com.example.administrator.test.mvp.model.MovieUpcomingModel;
import com.example.administrator.test.mvp.presenter.MovieUpcomingPresenter;
import com.example.administrator.test.viewholder.movie.MovieVH;

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
public class MovieUpcomingFragment extends BaseListFragment<MovieBriefInformation, MovieUpcomingPresenter> implements MovieHitContract.View, MovieUpcomingContract.View {
    @Override
    protected void getData(int page, int pageSize) {
        presenter.loadData(page, pageSize);
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<MovieBriefInformation>(getContext(),
                                                                        R.layout.fragment_base_list) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, MovieBriefInformation item, int viewType, int position) {
                ((MovieVH) holder).setData(item);
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new LinearLayoutHelper();
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new MovieVH(getContext(), parent, R.layout.movie_hit_vh_item);
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
    protected MovieUpcomingPresenter getPresenter() {
        return new MovieUpcomingPresenter(this, new MovieUpcomingModel());
    }

    @Override
    public void onLoadSuccess(MoviewHitEntity entity) {
        stopRefresh();
        adapter.addAll(entity.getSubjects());
    }

    @Override
    public void onLoadFailed(String msg) {
        stopRefresh();
    }
}
