package com.example.administrator.test.mvp.contract;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import org.jetbrains.annotations.NotNull;

public interface TestContract {
    interface Model {
        double add(double a, double b);
    }

    interface View {
        void addResult(double result);
    }

    interface Presenter extends LifecycleObserver {
        void calculate(double a, double b);

        /**
         * Activity的onCreate
         * @param owner
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        void onCreate(@NotNull LifecycleOwner owner);
        /**
         * Activity的onStart
         * @param owner
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void onStart(@NotNull LifecycleOwner owner);
        /**
         * Activity的onResume
         * @param owner
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResume(@NotNull LifecycleOwner owner);
        /**
         * Activity的onPause
         * @param owner
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPause(@NotNull LifecycleOwner owner);
        /**
         * Activity的onStop
         * @param owner
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        void onStop(@NotNull LifecycleOwner owner);
        /**
         * Activity的onStop
         * @param owner
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void onDestroy(@NotNull LifecycleOwner owner);

        /**
         * Activity的生命周期改变
         * @param owner
         * @param event
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        void onLifecycleChanged(@NotNull LifecycleOwner owner,
                                @NotNull Lifecycle.Event event);
    }
}
