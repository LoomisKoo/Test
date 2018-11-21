package com.example.administrator.test.mvp.presenter;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

import com.example.administrator.test.entity.TesttttBean;
import com.example.administrator.test.http.AppConfigUtil;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.http.HttpUtil;
import com.example.administrator.test.mvp.contract.GetBookContract;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public class GetBookPresenter implements GetBookContract.Presenter {

    public GetBookPresenter() {
    }

    @SuppressLint("CheckResult")
    @Override
    public void getSearchBooks() {
        Observable<TesttttBean> observable = HttpUtil.getInstance().getService().getGetData(AppConfigUtil.url);
        HttpUtil.query(observable, new HttpCallback<TesttttBean>() {
            @Override
            public void onSuccess(TesttttBean result) {

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
