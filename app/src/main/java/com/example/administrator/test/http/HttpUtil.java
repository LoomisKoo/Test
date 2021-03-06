package com.example.administrator.test.http;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.administrator.test.http.interceptor.BasicParamsInterceptor;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author koo
 */
public class HttpUtil {
    private        Api                  api;
    private        HttpUtilBuilder<Api> httpUtilBuilder;
    private static HttpUtil             instance;
    private        boolean              iaDebug;
    private        Context              context;

//    private HttpUtil() {
//        httpUtilBuilder = new HttpUtilBuilder<Api>(Api.class, new BasicParamsInterceptor.Builder().build(), "");
//    }

    public static HttpUtil getInstance() {
        if (instance == null) {
            synchronized (HttpUtil.class) {
                if (instance == null) {
                    instance = new HttpUtil();
                }
            }
        }
        return instance;
    }

    public void init(Context context, boolean debug) {
        this.context = context;
        this.iaDebug = debug;
//        httpUtilBuilder.setContext(context);
//        HttpHead.init(context);
    }


    public Api getService() {
        return api == null ? api = httpUtilBuilder.getService() : api;
    }

    /**
     * 重置
     */
    public void resetService() {
        httpUtilBuilder.resetBaseUrl(HttpConfigUtil.baseUrl);
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

                      @SuppressLint("CheckResult")
                      @Override
                      public void onError(Throwable e) {
                          observable.unsubscribeOn(AndroidSchedulers.mainThread());
                          callBack.onError(e.toString());
                      }

                      @SuppressLint("CheckResult")
                      @Override
                      public void onComplete() {
                          observable.unsubscribeOn(AndroidSchedulers.mainThread());
                          callBack.onComplete();
                      }
                  });

    }
}
