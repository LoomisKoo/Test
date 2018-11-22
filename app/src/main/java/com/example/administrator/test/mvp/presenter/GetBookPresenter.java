package com.example.administrator.test.mvp.presenter;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

import com.example.administrator.test.http.AppConfigUtil;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.http.HttpUtil;
import com.example.administrator.test.mvp.base.BasePresenter;
import com.example.administrator.test.mvp.contract.GetBookContract;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

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

    @Override
    public void onCreate(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onStart(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onResume(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onPause(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onStop(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner, Lifecycle.@NotNull Event event) {

    }
}
