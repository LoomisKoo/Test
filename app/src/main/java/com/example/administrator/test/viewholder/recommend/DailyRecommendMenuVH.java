package com.example.administrator.test.viewholder.recommend;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.util.ArouterHelper;
import com.example.administrator.test.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder.recommend
 * @ClassName: DailyRecommendArticleVH
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/23 6:27 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/23 6:27 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DailyRecommendMenuVH extends BaseViewHolder {
    private static final String RECOMMEND_MUSIC_TITLE        = "网易云音乐";
    private static final String RECOMMEND_MUSIC_URL          = "https://music.163.com/m/";
    private static final String RECOMMEND_PLAY_ANDROID_TITLE = "玩安卓";
    private static final String RECOMMEND_PLAY_ANDROID_URL   = "http://www.wanandroid.com";
    private static final String RECOMMEND_READING_TITLE      = "干货闲读";
    private static final String RECOMMEND_READING_URL        = "https://gank.io/xiandu";
    private static final String RECOMMEND_HIT_MOVIE_TITLE    = "热映电影";
    private static final String RECOMMEND_HIT_MOVIE_URL      = "https://movie.douban.com";

    private ImageButton ibReading;
    private ImageButton ibDailyRecommend;
    private ImageButton ibPlayAndroid;
    private ImageButton ibHitMovies;

    public DailyRecommendMenuVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        ibReading = getView(R.id.ib_reading);
        ibDailyRecommend = getView(R.id.ib_daily_recommend);
        ibPlayAndroid = getView(R.id.ib_play_android);
        ibHitMovies = getView(R.id.ib_hit_movies);

        AnimatorHelper.setViewTouchListener(ibReading);
        AnimatorHelper.setViewTouchListener(ibDailyRecommend);
        AnimatorHelper.setViewTouchListener(ibPlayAndroid);
        AnimatorHelper.setViewTouchListener(ibHitMovies);
        ibReading.setOnClickListener(v -> ARouter.getInstance()
                                                 .build(ArouterHelper.ROUTE_ACTIVITY_WEB)
                                                 .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                                 .withString("title", RECOMMEND_READING_TITLE)
                                                 .withString("url", RECOMMEND_READING_URL)
                                                 .withInt("x", AnimatorHelper.getDownX())
                                                 .withInt("y", AnimatorHelper.getDownY())
                                                 .navigation(context));
        ibDailyRecommend.setOnClickListener(v -> ARouter.getInstance()
                                                        .build(ArouterHelper.ROUTE_ACTIVITY_WEB)
                                                        .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                                        .withString("title", RECOMMEND_MUSIC_TITLE)
                                                        .withString("url", RECOMMEND_MUSIC_URL)
                                                        .withInt("x", AnimatorHelper.getDownX())
                                                        .withInt("y", AnimatorHelper.getDownY())
                                                        .navigation(context));
        ibPlayAndroid.setOnClickListener(v -> ARouter.getInstance()
                                                     .build(ArouterHelper.ROUTE_ACTIVITY_WEB)
                                                     .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                                     .withString("title", RECOMMEND_PLAY_ANDROID_TITLE)
                                                     .withString("url", RECOMMEND_PLAY_ANDROID_URL)
                                                     .withInt("x", AnimatorHelper.getDownX())
                                                     .withInt("y", AnimatorHelper.getDownY())
                                                     .navigation(context));
        ibHitMovies.setOnClickListener(v -> ARouter.getInstance()
                                                   .build(ArouterHelper.ROUTE_ACTIVITY_WEB)
                                                   .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                                   .withString("title", RECOMMEND_HIT_MOVIE_TITLE)
                                                   .withString("url", RECOMMEND_HIT_MOVIE_URL)
                                                   .withInt("x", AnimatorHelper.getDownX())
                                                   .withInt("y", AnimatorHelper.getDownY())
                                                   .navigation(context));
    }
}