package com.example.administrator.test.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseActivity;
import com.example.administrator.test.entity.MovieBriefInformation;
import com.example.administrator.test.entity.MovieDetailEntity;
import com.example.administrator.test.mvp.contract.MovieDetailContract;
import com.example.administrator.test.mvp.model.MovieDetailModel;
import com.example.administrator.test.mvp.presenter.MovieDetailPresenter;
import com.example.administrator.test.util.ArouteHelper;
import com.example.administrator.test.viewholder.movie.MovieDetailIntroduction;
import com.example.administrator.test.viewholder.movie.MovieDetailViewInfo;
import com.example.administrator.test.viewholder.movie.MovieDetailViewActors;

import androidx.appcompat.widget.Toolbar;

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
    /**
     * toolbar
     */
    private Toolbar                 mToolbar;
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
        mToolbar.setNavigationOnClickListener(v -> finishAfterTransition());
    }

    @Override
    public void initData(Context mContext) {
        movieBriefInformation = (MovieBriefInformation) getIntent().getSerializableExtra("movieBriefInformation");
        movieID = movieBriefInformation.getId();

        presenter.getMovieDetail(movieID);
        movieInfoView.setData(movieBriefInformation);

        mToolbar.setTitle(movieBriefInformation.getTitle());
        mToolbar.setSubtitle("主演：" + movieInfoView.getCastsName(movieBriefInformation));
    }


    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.movie_detail_activity);
        setStatusBarFullTransparent();

        movieInfoView = new MovieDetailViewInfo(this, getWindow().getDecorView());
        initToolbar();

        actorsView = new MovieDetailViewActors(this, getWindow().getDecorView());
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
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //不设置的话，mToolbar.setTitle();无效
        getSupportActionBar().setTitle("");
    }

    /**
     * 初始化电影介绍
     */
    private void initMovieIntroduction(MovieDetailEntity entity) {
        introductionView = new MovieDetailIntroduction(getWindow().getDecorView());
        introductionView.setData(entity);
    }

    /**
     * 全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        //高于Android5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView()
                  .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                                 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        //高于Android 4.4
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}
