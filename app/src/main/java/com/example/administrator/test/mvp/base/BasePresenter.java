package com.example.administrator.test.mvp.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.administrator.test.http.AppConfigUtil;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.http.HttpUtil;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @author koo
 */
public abstract class BasePresenter implements LifecycleObserver {
    protected void getData(Map<String, String> map, HttpCallback<ResponseBody> callback) {
        Observable<ResponseBody> observable = HttpUtil.getInstance().getService().getData(AppConfigUtil.url);
        HttpUtil.query(observable, callback);
    }


    /**
     * Activity的onCreate
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate(@NotNull LifecycleOwner owner) {

    }

    /**
     * Activity的onStart
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onStart(@NotNull LifecycleOwner owner) {

    }

    /**
     * Activity的onResume
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void onResume(@NotNull LifecycleOwner owner) {

    }

    /**
     * Activity的onPause
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void onPause(@NotNull LifecycleOwner owner) {

    }

    /**
     * Activity的onStop
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void onStop(@NotNull LifecycleOwner owner) {

    }

    /**
     * Activity的onStop
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy(@NotNull LifecycleOwner owner) {

    }

    /**
     * Activity的生命周期改变
     *
     * @param owner
     * @param event
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    protected void onLifecycleChanged(@NotNull LifecycleOwner owner,
                                      @NotNull Lifecycle.Event event) {

    }


}
