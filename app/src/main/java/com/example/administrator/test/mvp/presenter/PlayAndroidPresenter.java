package com.example.administrator.test.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.entity.BannerEntity;
import com.example.administrator.test.entity.view.PlayAndroidViewEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.http.HttpRequestType;
import com.example.administrator.test.mvp.contract.PlayAndroidContract;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @author koo
 */
public class PlayAndroidPresenter implements PlayAndroidContract.Presenter {
    private PlayAndroidContract.View view;
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
                } catch (IOException e) {
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
    public void getArticleList() {
        model.getArticleList(new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                try {
                    String strResult = result.string();
                    System.out.println(strResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                playAndroidViewEntity = new PlayAndroidViewEntity(bannerEntity, HttpRequestType.REQUEST_TYPE_BANNER);
//                view.onSuccess(playAndroidViewEntity);
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
