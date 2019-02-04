package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.entity.view.BaseViewEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.mvp.presenter.PlayAndroidPresenter;

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
        void getArticleList(int page, Integer cid, HttpCallback httpCallback);

        void collectArticle(int articleID, HttpCallback httpCallback);

        void unCollectArticle(int articleID, HttpCallback httpCallback);

        /**
         * @param originId 如果是收藏列表的话就是原始文章的id，如果是站外文章就是-1
         * @param id       bean里的id
         */
        void unCollectArticle(int originId, int id, HttpCallback httpCallback);
    }

    interface View {
        void showLoading();

        void hideLoading();

        /**
         * banner图请求成功回调
         *
         * @param entity
         * @param maxPage
         */
        void onSuccess(BaseViewEntity entity, int maxPage);

        void onError(String msg);

        void onComplete();

    }

    interface Presenter extends IBasePresenter {
        void getBannerImg();

        void getArticleList(int page);

        void collectArticle(int articleID, PlayAndroidPresenter.CallBack callBack);

        void unCollectArticle(int articleID, PlayAndroidPresenter.CallBack callBack);

        /**
         * @param isCollectList 是否是收藏列表
         * @param originId      如果是收藏列表的话就是原始文章的id，如果是站外文章就是-1
         * @param id            bean里的id
         */
        void unCollectArticle(boolean isCollectList, int originId, int id, PlayAndroidPresenter.CallBack callBack);

    }
}
