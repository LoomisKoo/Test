package com.example.administrator.test.mvp.model;

import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.http.HttpUtil;
import com.example.administrator.test.mvp.contract.PlayAndroidContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @author koo
 */
public class PlayAndroidModel implements PlayAndroidContract.Model {

    @Override
    public void getBannerImg(HttpCallback httpCallback) {
        Observable<ResponseBody> observable = HttpUtil.getInstance().getService().getBannerImg();
        HttpUtil.query(observable, httpCallback);
    }

    @Override
    public void getArticleList(int page, Integer cid, HttpCallback httpCallback) {
        Observable<ResponseBody> observable = HttpUtil.getInstance().getService().getArticleList(page, cid);
        HttpUtil.query(observable, httpCallback);
    }

    @Override
    public void collectArticle(int articleID, HttpCallback httpCallback) {
        Observable<ResponseBody> observable = HttpUtil.getInstance().getService().collectArticle(articleID);
        HttpUtil.query(observable, httpCallback);
    }

    @Override
    public void unCollectArticle(int articleID, HttpCallback httpCallback) {
        Observable<ResponseBody> observable = HttpUtil.getInstance().getService().unCollectArticleOrigin(articleID);
        HttpUtil.query(observable, httpCallback);
    }

    @Override
    public void unCollectArticle(int originId, int id, HttpCallback httpCallback) {
        Observable<ResponseBody> observable = HttpUtil.getInstance().getService().unCollectArticle(id, originId);
        HttpUtil.query(observable, httpCallback);
    }
}
