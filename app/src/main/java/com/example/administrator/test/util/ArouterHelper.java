package com.example.administrator.test.util;

import android.content.Intent;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.test.animation.AnimatorHelper;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.util
 * @ClassName: ArouterHelper
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2018/11/28 4:32 PM
 * @UpdateUser:
 * @UpdateDate: 2018/11/28 4:32 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ArouterHelper {
    public final static String ROUTE_ACTIVITY_MAIN               = "/koo/MainActivity";
    public final static String ROUTE_ACTIVITY_SECOND_ACTIVITY    = "/koo/SecondActivity";
    public final static String ROUTE_ACTIVITY_TAB_ACTIVITY       = "/koo/TabActivity";
    public final static String ROUTE_ACTIVITY_LIST_ACTIVITY      = "/koo/ListActivity";
    public final static String ROUTE_ACTIVITY_WEB                = "/koo/web";
    public final static String ROUTE_ACTIVITY_LOGIN              = "/koo/login";
    public final static String ROUTE_ACTIVITY_LOGIN_PLAY_ANDROID = "/koo/login/play_android";
    public final static String ROUTE_ACTIVITY_ARTICLE_LIST       = "/koo/article_list";


    public static Postcard buildWithAnimator(String route) {
        return ARouter.getInstance()
                      .build(route)
                      .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                      .withInt("x", AnimatorHelper.getDownX())
                      .withInt("y", AnimatorHelper.getDownY());
    }
}
