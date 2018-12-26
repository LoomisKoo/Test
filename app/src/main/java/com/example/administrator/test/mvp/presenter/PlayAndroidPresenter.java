package com.example.administrator.test.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.entity.ArticleListEntity;
import com.example.administrator.test.entity.BannerEntity;
import com.example.administrator.test.entity.view.PlayAndroidViewEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.http.HttpRequestType;
import com.example.administrator.test.mvp.contract.PlayAndroidContract;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * @author koo
 */
public class PlayAndroidPresenter implements PlayAndroidContract.Presenter {

    private PlayAndroidContract.View  view;
    private PlayAndroidContract.Model model;

    private PlayAndroidViewEntity playAndroidViewEntity;

    public PlayAndroidPresenter(PlayAndroidContract.View view, PlayAndroidContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getBannerImg() {
        model.getBannerImg(new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                BannerEntity bannerEntity = null;
                try {
                    bannerEntity = JSON.parseObject(result.string(), BannerEntity.class);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                playAndroidViewEntity = new PlayAndroidViewEntity(bannerEntity, HttpRequestType.REQUEST_TYPE_BANNER);
                view.onSuccess(playAndroidViewEntity);
            }

            @Override
            public void onError(String msg) {
                view.onError(msg);
            }

            @Override
            public void onComplete() {
                view.onComplete();
            }
        });
    }

    @Override
    public void getArticleList(int page) {
        model.getArticleList(page, null, new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                try {
                    String            strResult         = result.string();
                    ArticleListEntity articleListEntity = JSON.parseObject(strResult, ArticleListEntity.class);

                    List<ArticleListEntity.DataBean.ArticleInfoBean> entityList = articleListEntity.getData().getArticleListBean();
                    for (ArticleListEntity.DataBean.ArticleInfoBean entity : entityList) {
                        playAndroidViewEntity = new PlayAndroidViewEntity(entity, HttpRequestType.REQUEST_TYPE_ARTICEL_LIST);
                        view.onSuccess(playAndroidViewEntity);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String msg) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void collectArticle(int articleID, CallBack callBack) {
        model.collectArticle(articleID, new HttpCallback() {
            @Override
            public void onSuccess(Object result) {
                callBack.onSuccess();
            }

            @Override
            public void onError(String msg) {
                callBack.onError();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void unCollectArticle(int articleID, CallBack callBack) {
        model.unCollectArticle(articleID, new HttpCallback() {
            @Override
            public void onSuccess(Object result) {
                callBack.onSuccess();
            }

            @Override
            public void onError(String msg) {
                callBack.onError();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void unCollectArticle(boolean isCollectList, int originId, int id, CallBack callBack) {
        if (isCollectList) {
            model.unCollectArticle(originId, new HttpCallback() {
                @Override
                public void onSuccess(Object result) {
                    callBack.onSuccess();
                }

                @Override
                public void onError(String msg) {
                    callBack.onError();
                }

                @Override
                public void onComplete() {

                }
            });
        }
        else {
            model.unCollectArticle(id, new HttpCallback() {
                @Override
                public void onSuccess(Object result) {

                }

                @Override
                public void onError(String msg) {

                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    public interface CallBack {
        void onSuccess();

        void onError();
    }
}