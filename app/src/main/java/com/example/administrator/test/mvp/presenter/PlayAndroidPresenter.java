package com.example.administrator.test.mvp.presenter;

import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.PlayAndroidContract;

import okhttp3.ResponseBody;

/**
 * @author koo
 */
public class PlayAndroidPresenter implements PlayAndroidContract.Presenter {
    PlayAndroidContract.View view;
    PlayAndroidContract.Model model;

    public PlayAndroidPresenter(PlayAndroidContract.View view, PlayAndroidContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getBannerImg() {
        model.getBannerImg(new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                view.onSuccess(result);
            }

            @Override
            public void onError(String msg) {
                System.out.println(msg);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
