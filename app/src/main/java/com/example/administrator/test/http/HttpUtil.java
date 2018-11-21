package com.example.administrator.test.http;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HttpUtil {
    private Api api;
    private HttpUtilBuilder<Api> httpUtilBuilder;

    private static class HttpUtilSingleton {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

    private HttpUtil() {
        httpUtilBuilder = new HttpUtilBuilder<>(Api.class, AppConfigUtil.baseUrl, new BasicParamsInterceptor.Builder().build());
    }

    public static HttpUtil getInstance() {
        return HttpUtilSingleton.INSTANCE;
    }

    public Api getService() {
        return api == null ? api = httpUtilBuilder.getService() : api;
    }

    public void resetService() {
        httpUtilBuilder.resetBaseUrl(AppConfigUtil.baseUrl);
        api = httpUtilBuilder.getService();
    }


    public static <T> void query(Observable<T> observable, HttpCallback<T> callBack) {
        observable.subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(T t) {
                        callBack.onSuccess(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        callBack.onComplete();
                    }
                });

    }
}
