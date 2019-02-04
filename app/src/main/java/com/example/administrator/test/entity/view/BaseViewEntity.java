package com.example.administrator.test.entity.view;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.entity.view
 * @ClassName: BaseViewEntity
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/4 3:13 PM
 * @UpdateUser:
 * @UpdateDate: 2019/2/4 3:13 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseViewEntity {
    //----------------------------------玩安卓页面------------------------------------//
    /**
     * banner
     */
    public static final int PLAY_ANDROID_VIEW_TYPE_BANNER       = 0;
    /**
     * 文章列表
     */
    public static final int PLAY_ANDROID_VIEW_TYPE_ARTICLE_LIST = 1;

    //----------------------------------每日推荐页面------------------------------------//
    /**
     * banner（广告）
     */
    public static final int RECOMMEND_DAILY_VIEW_TYPE_BANNER    = 0;
    /**
     * 主菜单 4个按钮
     */
    public static final int RECOMMEND_DAILY_VIEW_TYPE_MAIN_MENU = 1;
    /**
     * 文章列表（包括android/ios等类型）
     */
    public static final int RECOMMEND_DAILY_VIEW_TYPE_ARTICLE   = 2;
    /**
     * 视频（一个）
     */
    public static final int RECOMMEND_DAILY_VIEW_TYPE_VIDEO     = 3;
    /**
     * 福利（一张照片）
     */
    public static final int RECOMMEND_DAILY_VIEW_TYPE_PHOTO     = 4;

    //----------------------------------自定义推荐页面------------------------------------//
    /**
     * title
     */
    public static final int RECOMMEND_CUSTOM_VIEW_TYPE_TITLE        = 0;
    /**
     * 文章列表
     */
    public static final int RECOMMEND_CUSTOM_VIEW_TYPE_ARTICLE_LIST = 1;

    //----------------------------------电影详情页面------------------------------------//
    /**
     * 头部布局
     */
    public static final int MOVIE_DETAIL_VIEW_TYPE_HEAD             = 0;
    /**
     * 电影简介
     */
    public static final int MOVIE_DETAIL_VIEW_TYPE_INTRODUCTION     = 1;
    /**
     * 演员信息
     */
    public static final int MOVIE_DETAIL_VIEW_TYPE_ACTORS_INFOATION = 2;


    private int viewType = -1;

    public BaseViewEntity(Object data, int viewType) {
        this.data = data;
        this.viewType = viewType;
    }

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
