package com.example.administrator.test.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.entity.MoviewHitEntity;
import com.example.administrator.test.mvp.contract.MovieHitContract;
import com.example.administrator.test.mvp.model.MovieHitModel;
import com.example.administrator.test.mvp.presenter.MovieHitPresenter;
import com.example.administrator.test.viewholder.movie.MovieVh;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.fragment
 * @ClassName: MovieHitFragment   热门电影列表
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/3 11:04 AM
 * @UpdateUser:
 * @UpdateDate: 2019/2/3 11:04 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MovieHitFragment extends BaseListFragment<MoviewHitEntity.SubjectsEntity, MovieHitPresenter> implements MovieHitContract.View {
    @Override
    protected void getData(int page, int pageSize) {
        presenter.loadData();
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<MoviewHitEntity.SubjectsEntity>(getContext(),
                                                                        R.layout.fragment_base_list) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, MoviewHitEntity.SubjectsEntity item, int viewType, int position) {
                ((MovieVh) holder).setData(item);
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new LinearLayoutHelper();
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new MovieVh(getContext(), parent, R.layout.movie_hit_vh_item);
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
    protected MovieHitPresenter getPresenter() {
        return new MovieHitPresenter(this, new MovieHitModel());
    }

    @Override
    public void onLoadSuccess(MoviewHitEntity entity) {
        stopRefresh();
        adapter.addAll(entity.getSubjects());
        //电影接口只有一页数据，此处maxPage = -1；为了上拉不会继续加载
        maxPage = -1;
    }

    @Override
    public void onLoadFailed(String msg) {
        stopRefresh();
    }
}
