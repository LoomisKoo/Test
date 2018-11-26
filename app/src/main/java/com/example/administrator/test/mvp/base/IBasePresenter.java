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
public interface  IBasePresenter {
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

}