package com.example.administrator.test.mvp.contract;

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
         * @param httpCallback 回调
         * @return
         */
        ResponseBody getBannerImg(HttpCallback httpCallback);
    }

    interface View {
        void showLoading();
        void hideLoading();
        void onSuccess(ResponseBody responseBody);
        void onError(String msg);
        void onComplete();
    }

    interface Presenter extends IBasePresenter {
        void getBannerImg();
    }
}
