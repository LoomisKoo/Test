package com.example.administrator.test.mvp.presenter;

import android.annotation.SuppressLint;
import com.example.administrator.test.http.AppConfigUtil;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.http.HttpUtil;
import com.example.administrator.test.mvp.base.BasePresenter;
import com.example.administrator.test.mvp.contract.GetBookContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @author koo
 */
public class GetBookPresenter extends BasePresenter implements GetBookContract.Presenter {

    public GetBookPresenter() {
    }

    @SuppressLint("CheckResult")
    @Override
    public void getData() {
        Observable<ResponseBody> observable = HttpUtil.getInstance().getService().getGetData(AppConfigUtil.url);
        HttpUtil.query(observable, new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {

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
