package com.example.administrator.test.mvp.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author koo
 */
public interface  IBasePresenter extends LifecycleObserver {
//    public V baseView;
//
//    public IBasePresenter(V baseView) {
//        this.baseView = baseView;
//    }
    /**
     * 返回 view
     *
     * @return
     */
//    public V getBaseView() {
//        return baseView;
//    }

//    private CompositeDisposable compositeDisposable;
//
//
//    public void addDisposable(Observable<?> observable, BaseObserver observer) {
//        if (compositeDisposable == null) {
//            compositeDisposable = new CompositeDisposable();
//        }
//        compositeDisposable.add(observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(observer));
//
//
//    }
//
//    public void removeDisposable() {
//        if (compositeDisposable != null) {
//            compositeDisposable.dispose();
//        }
//    }


    /**
     * Activity的onCreate
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    abstract void onCreate(@NotNull LifecycleOwner owner);

    /**
     * Activity的onStart
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    abstract void onStart(@NotNull LifecycleOwner owner);

    /**
     * Activity的onResume
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    abstract void onResume(@NotNull LifecycleOwner owner);

    /**
     * Activity的onPause
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    abstract void onPause(@NotNull LifecycleOwner owner);

    /**
     * Activity的onStop
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    abstract void onStop(@NotNull LifecycleOwner owner);

    /**
     * Activity的onStop
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    abstract void onDestroy(@NotNull LifecycleOwner owner);

    /**
     * Activity的生命周期改变
     *
     * @param owner
     * @param event
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    abstract void onLifecycleChanged(@NotNull LifecycleOwner owner,
                                     @NotNull Lifecycle.Event event);


}
