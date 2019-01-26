package com.example.administrator.test.viewholder.recommend;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.util.ArouteHelper;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder.recommend
 * @ClassName: DailyArticleVH
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/23 6:27 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/23 6:27 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DailyMenuVH extends BaseViewHolder {
    private static final String RECOMMEND_MUSIC_TITLE = "网易云音乐";
    private static final String RECOMMEND_MUSIC_URL   = "https://music.163.com/m/";

    private static final String RECOMMEND_PLAY_ANDROID_TITLE = "玩安卓";
    private static final String RECOMMEND_PLAY_ANDROID_URL   = "http://www.wanandroid.com";

    private static final String RECOMMEND_READING_TITLE = "干货闲读";
    private static final String RECOMMEND_READING_URL   = "https://gank.io/xiandu";

    private static final String RECOMMEND_HIT_MOVIE_TITLE = "热映电影";
    private static final String RECOMMEND_HIT_MOVIE_URL   = "https://movie.douban.com";

    private ImageButton ibReading;
    private ImageButton ibDailyRecommend;
    private ImageButton ibPlayAndroid;
    private ImageButton ibHitMovies;

    public DailyMenuVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        ibReading = getView(R.id.ib_reading);
        ibDailyRecommend = getView(R.id.ib_daily_recommend);
        ibPlayAndroid = getView(R.id.ib_play_android);
        ibHitMovies = getView(R.id.ib_hit_movies);

        AnimatorHelper.setViewTouchListener(ibReading);
        AnimatorHelper.setViewTouchListener(ibDailyRecommend);
        AnimatorHelper.setViewTouchListener(ibPlayAndroid);
        AnimatorHelper.setViewTouchListener(ibHitMovies);

        ibReading.setOnClickListener(v -> ArouteHelper.buildWebWithAnimator(context, RECOMMEND_READING_TITLE, RECOMMEND_READING_URL));
        ibDailyRecommend.setOnClickListener(v -> ArouteHelper.buildWebWithAnimator(context, RECOMMEND_MUSIC_TITLE, RECOMMEND_MUSIC_URL));
        ibPlayAndroid.setOnClickListener(v -> ArouteHelper.buildWebWithAnimator(context, RECOMMEND_PLAY_ANDROID_TITLE, RECOMMEND_PLAY_ANDROID_URL));
        ibHitMovies.setOnClickListener(v -> ArouteHelper.buildWebWithAnimator(context, RECOMMEND_HIT_MOVIE_TITLE, RECOMMEND_HIT_MOVIE_URL));
    }
}