package com.example.administrator.test.activity;

import android.os.Bundle;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseListActivity;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.entity.MovieBriefInformation;
import com.example.administrator.test.entity.MovieDetailEntity;
import com.example.administrator.test.entity.view.BaseViewEntity;
import com.example.administrator.test.mvp.contract.MovieDetailContract;
import com.example.administrator.test.mvp.model.MovieDetailModel;
import com.example.administrator.test.mvp.presenter.MovieDetailPresenter;
import com.example.administrator.test.util.ArouteHelper;
import com.example.administrator.test.viewholder.movie.MovieDetailActorsVH;
import com.example.administrator.test.viewholder.movie.MovieDetailHeadVH;
import com.example.administrator.test.viewholder.movie.MovieDetailIntroductionVH;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.activity
 * @ClassName: MovieDetailActivity
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/4 2:32 PM
 * @UpdateUser:
 * @UpdateDate: 2019/2/4 2:32 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Route(path = ArouteHelper.ROUTE_ACTIVITY_MOVIE_DETAIL)
public class MovieDetailActivity extends BaseListActivity<BaseViewEntity, MovieDetailPresenter> implements MovieDetailContract.View {
    /**
     * 电影ID
     */
    public String movieID;
    /**
     * 头部布局数据
     */
    @Autowired
    MovieBriefInformation movieBriefInformation;

    /**
     * 电影头部ViewHolder
     */
    private MovieDetailHeadVH headVH;

    @Override
    protected void getData(int page, int pageSize) {
        presenter.getMovieDetail(movieID);
    }


    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<BaseViewEntity>(this, R.layout.layout_base_list) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, BaseViewEntity item, int viewType, int position) {

                switch (viewType) {
                    case BaseViewEntity.MOVIE_DETAIL_VIEW_TYPE_INTRODUCTION:
                        ((MovieDetailIntroductionVH) holder).setData((MovieDetailEntity) item.getData());
                        break;
                    case BaseViewEntity.MOVIE_DETAIL_VIEW_TYPE_ACTORS_INFOATION:
                        ((MovieDetailActorsVH) holder).setData((MovieDetailEntity) item.getData());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new LinearLayoutHelper();
            }

            @Override
            protected RecyclerView.ViewHolder onGetViewHolder(ViewGroup parent, int viewType) {
                switch (viewType) {
                    case BaseViewEntity.MOVIE_DETAIL_VIEW_TYPE_INTRODUCTION:
                        return new MovieDetailIntroductionVH(context, parent, R.layout.movie_detail_introduction_vh_item);
                    case BaseViewEntity.MOVIE_DETAIL_VIEW_TYPE_ACTORS_INFOATION:
                        return new MovieDetailActorsVH(context, parent, R.layout.movie_detail_actors_list_vh_item);
                    default:
                        return super.onGetViewHolder(parent, viewType);
                }
            }

            @Override
            public int getItemViewType(int position) {
                return adapter.getData()
                              .get(position)
                              .getViewType();
            }
        };

    }

    @Override
    public int bindTopLayout() {
        return R.layout.movie_detail_head;
    }

    @Override
    public int bindBottomLayout() {
        return 0;
    }

    @Override
    protected MovieDetailPresenter createPresenter() {
        return new MovieDetailPresenter(this, new MovieDetailModel());
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setAllowActivityAnimator(false);
        initHeadVH();

        setToolBarTitle(movieBriefInformation.getTitle());
    }


    @Override
    public void onLoadSuccess(MovieDetailEntity entity) {
        stopRefresh();
        headVH.setRegion(entity.getCountries());

        BaseViewEntity viewEntity;
        viewEntity = new BaseViewEntity(entity, BaseViewEntity.MOVIE_DETAIL_VIEW_TYPE_INTRODUCTION);
        adapter.add(viewEntity);

        viewEntity = new BaseViewEntity(entity, BaseViewEntity.MOVIE_DETAIL_VIEW_TYPE_ACTORS_INFOATION);
        adapter.add(viewEntity);
    }

    @Override
    public void onLoadFailed(String msg) {
        stopRefresh();
        showToast(msg);
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }

    @Override
    protected void OnNavigationOnClick() {
        finishAfterTransition();
    }

    /**
     * 初始化HeadVH
     */
    private void initHeadVH() {
        movieBriefInformation = (MovieBriefInformation) getIntent().getSerializableExtra("movieBriefInformation");
        if (null == movieBriefInformation) {
            return;
        }
        movieID = movieBriefInformation.getId();
        headVH = new MovieDetailHeadVH(this, getWindow().getDecorView());
        headVH.setData(movieBriefInformation);
    }
}
