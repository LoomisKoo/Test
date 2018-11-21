package com.example.administrator.test.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

import com.example.administrator.test.mvp.contract.TestContract;
import com.orhanobut.logger.Logger;

import org.jetbrains.annotations.NotNull;

/**
 * @author koo
 */
public class TestPresenter implements TestContract.Presenter {
    private TestContract.Model model;
    private TestContract.View view;

    public TestPresenter(TestContract.Model model, TestContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void calculate(double a, double b) {
        view.addResult(model.add(a, b));
    }

    @Override
    public void onCreate(@NotNull LifecycleOwner owner) {
        Logger.i("LifecycleOwner:onCreate");
    }

    @Override
    public void onStart(@NotNull LifecycleOwner owner) {
        Logger.i("LifecycleOwner:onStart");
    }

    @Override
    public void onResume(@NotNull LifecycleOwner owner) {
        Logger.i("LifecycleOwner:onResume");
    }

    @Override
    public void onPause(@NotNull LifecycleOwner owner) {
        Logger.i("LifecycleOwner:onPause");
    }

    @Override
    public void onStop(@NotNull LifecycleOwner owner) {
        Logger.i("LifecycleOwner:onStop");
    }

    @Override
    public void onDestroy(@NotNull LifecycleOwner owner) {
        Logger.i("LifecycleOwner:onDestroy");
    }

    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner, Lifecycle.@NotNull Event event) {
        Logger.i("LifecycleOwner:onLifecycleChanged");
    }
}
