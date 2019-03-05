package com.example.administrator.test.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.entity.MovieBriefInformation;
import com.example.administrator.test.viewholder.movie.MovieVH;

import androidx.core.app.ActivityOptionsCompat;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.util
 * @ClassName: ArouteHelper
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2018/11/28 4:32 PM
 * @UpdateUser:
 * @UpdateDate: 2018/11/28 4:32 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ArouteHelper {
    public final static String ROUTE_ACTIVITY_MAIN                    = "/koo/MainActivity";
    public final static String ROUTE_ACTIVITY_SECOND_ACTIVITY         = "/koo/SecondActivity";
    public final static String ROUTE_ACTIVITY_TAB_ACTIVITY            = "/koo/TabActivity";
    public final static String ROUTE_ACTIVITY_LIST_ACTIVITY           = "/koo/ListActivity";
    public final static String ROUTE_ACTIVITY_WEB                     = "/koo/web";
    public final static String ROUTE_ACTIVITY_LOGIN                   = "/koo/login";
    public final static String ROUTE_ACTIVITY_LOGIN_PLAY_ANDROID      = "/koo/login/play_android";
    public final static String ROUTE_ACTIVITY_ARTICLE_LIST            = "/koo/article_list";
    public final static String ROUTE_ACTIVITY_BIG_IMAGE               = "/koo/big_image";
    public final static String ROUTE_ACTIVITY_MOVIE_DETAIL            = "/koo/movie_detail";
    public final static String ROUTE_ACTIVITY_PROJECT_HOME            = "/koo/project_home";
    public final static String ROUTE_ACTIVITY_LOTTIE                  = "/koo/lottie";
    public final static String ROUTE_ACTIVITY_COLLECTION_ARTICLE_LIST = "/koo/collection_article_list";


    public static void buildWebWithAnimator(Context context, String title, String url) {
        ARouter.getInstance()
               .build(ROUTE_ACTIVITY_WEB)
               .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
               .withString("title", title)
               .withString("url", url)
               .withInt("x", AnimatorHelper.getDownX())
               .withInt("y", AnimatorHelper.getDownY())
               .navigation(context);
    }

    public static void buildMovieDetail(Context context, MovieBriefInformation entity, View shareView) {
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, shareView, "movie_detail");

        ARouter.getInstance()
               .build(ArouteHelper.ROUTE_ACTIVITY_MOVIE_DETAIL)
               .withSerializable("movieBriefInformation", entity)
               .withOptionsCompat(optionsCompat)
               .navigation(context);
    }
}
