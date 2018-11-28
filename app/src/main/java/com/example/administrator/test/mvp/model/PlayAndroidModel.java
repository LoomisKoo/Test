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
        Observable<ResponseBody> observable = HttpUtil.getInstance().getService().getBanerImg();
        HttpUtil.query(observable, httpCallback);
    }

    @Override
    public void getArticleList(HttpCallback httpCallback) {
        Observable<ResponseBody> observable = HttpUtil.getInstance().getService().getBanerImg();
        HttpUtil.query(observable, httpCallback);
    }
}
