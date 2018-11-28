package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.entity.view.PlayAndroidViewEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.base.IBasePresenter;

import okhttp3.ResponseBody;

/**
 * @author koo
 */
public interface PlayAndroidContract {
    interface Model {
        /**
         * 获取banner图片数据
         *
         * @param httpCallback 回调
         */
        void getBannerImg(HttpCallback httpCallback);

        /**
         * 获取日常文章列表
         *
         * @param httpCallback 回调
         */
        void getArticleList(HttpCallback httpCallback);
    }

    interface View {
        void showLoading();

        void hideLoading();

        void onSuccess(PlayAndroidViewEntity playAndroidViewEntity);

        void onError(String msg);

        void onComplete();
    }

    interface Presenter extends IBasePresenter {
        void getBannerImg();

        void getArticleList();
    }
}
