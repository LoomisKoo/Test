package com.example.administrator.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseActivity;
import com.example.administrator.test.entity.MovieBriefInformation;
import com.example.administrator.test.entity.MovieDetailEntity;
import com.example.administrator.test.mvp.contract.MovieDetailContract;
import com.example.administrator.test.mvp.model.MovieDetailModel;
import com.example.administrator.test.mvp.presenter.MovieDetailPresenter;
import com.example.administrator.test.util.ArouteHelper;
import com.example.administrator.test.viewholder.movie.MovieDetailIntroduction;
import com.example.administrator.test.viewholder.movie.MovieDetailViewActors;
import com.example.administrator.test.viewholder.movie.MovieDetailViewInfo;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

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
public class MovieDetailActivity extends BaseActivity<MovieDetailPresenter> implements MovieDetailContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    /**
     * 电影基本信息view
     */
    private MovieDetailViewInfo     movieInfoView;
    /**
     * 电影介绍view
     */
    private MovieDetailIntroduction introductionView;
    /**
     * 演员列表
     */
    private MovieDetailViewActors   actorsView;

    /**
     * 电影ID
     */
    public String movieID;
    /**
     * 头部布局数据
     */
    @Autowired
    MovieBriefInformation movieBriefInformation;

    @Override
    protected MovieDetailPresenter createPresenter() {
        return new MovieDetailPresenter(this, new MovieDetailModel());
    }

    @Override
    public void setListener() {
        toolbar.setNavigationOnClickListener(v -> finishAfterTransition());
    }

    @Override
    public void initData(Context mContext) {
        movieBriefInformation = (MovieBriefInformation) getIntent().getSerializableExtra("movieBriefInformation");
        movieID = movieBriefInformation.getId();

        presenter.getMovieDetail(movieID);
        movieInfoView.setData(movieBriefInformation);

        toolbar.setTitle(movieBriefInformation.getTitle());
        String starringNameFormat = getString(R.string.movie_detail_starring_name);
        String starringName       = String.format(starringNameFormat, movieInfoView.getCastsName(movieBriefInformation));
        toolbar.setSubtitle(starringName);
    }


    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.movie_detail_activity);
        ButterKnife.bind(this);
        BarUtils.setStatusBarAlpha(this, 0);
        movieInfoView = new MovieDetailViewInfo(this, getWindow().getDecorView());
        initToolbar();

        actorsView = new MovieDetailViewActors(this, getWindow().getDecorView());
    }

    @Override
    protected void onDestroy() {
        ButterKnife.bind(this)
                   .unbind();
        super.onDestroy();
    }

    @Override
    public void onLoadSuccess(MovieDetailEntity entity) {
        initMovieIntroduction(entity);
        movieInfoView.setRegion(entity.getCountries());
        actorsView.setData(entity);
    }

    @Override
    public void onLoadFailed(String msg) {
        showToast(msg);
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }

    /**
     * 初始化toolbar
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //不设置的话，toolbar.setTitle();无效
        getSupportActionBar().setTitle("");
    }

    /**
     * 初始化电影介绍
     */
    private void initMovieIntroduction(MovieDetailEntity entity) {
        introductionView = new MovieDetailIntroduction(getWindow().getDecorView());
        introductionView.setData(entity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
