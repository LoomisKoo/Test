package com.example.administrator.test.mvp.model;

import android.app.Activity;
import android.content.Context;

import com.example.administrator.test.R;
import com.example.administrator.test.mvp.contract.SplashContract;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.model
 * @ClassName: SplashModel
 * @Description: java类作用描述   延迟加载本地广告图片（延迟是为了显示欢迎页面，欢迎页为SplashActivity的背景图）
 * @Author: koo
 * @CreateDate: 2018/11/28 11:31 AM
 * @UpdateUser:
 * @UpdateDate: 2018/11/28 11:31 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SplashModel implements SplashContract.Model {

    private              Disposable              welcomeDisposable;
    private              Disposable              countDownDisposable;
    private              SplashContract.CallBack CallBack;
    private              Context                 context;
    /**
     * 欢迎页显示时间
     */
    private static final int                     SHOW_WELCOME_TIME_SECOND   = 0;
    /**
     * 广告倒计时开始时间（秒）
     */
    private static final int                     AD_COUNTDOWN_START_SECOND  = 1;
    /**
     * 广告倒计时结束时间（秒）
     */
    private static final int                     AD_COUNTDOWN_END_SECOND    = 2;
    /**
     * 广告倒计时延迟时间（秒）
     */
    private static final int                     AD_COUNTDOWN_DELAY_SECOND  = 0;
    /**
     * 广告倒计时间隔时间（秒）
     */
    private static final int                     AD_COUNTDOWN_PERIOD_SECOND = 1;


    public SplashModel(Context context, SplashContract.CallBack CallBack) {
        this.context = context;
        this.CallBack = CallBack;
    }

    @Override
    public void showWelcome() {
        Observable.timer(SHOW_WELCOME_TIME_SECOND, TimeUnit.SECONDS)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Observer<Long>() {
                      @Override
                      public void onSubscribe(Disposable d) {
                          welcomeDisposable = d;
                      }

                      @Override
                      public void onNext(Long aLong) {

                      }

                      @Override
                      public void onError(Throwable e) {
                          CallBack.finishShowWelcome();
                      }

                      @Override
                      public void onComplete() {
                          closeTimer();
                          CallBack.finishShowWelcome();
                      }
                  });
    }

    @Override
    public int getLocalADResID() {
        return R.mipmap.ad_eiffel_tower;
    }

    @Override
    public void countDownAD() {
        countDownDisposable = Observable.intervalRange(AD_COUNTDOWN_START_SECOND, AD_COUNTDOWN_END_SECOND, AD_COUNTDOWN_DELAY_SECOND, AD_COUNTDOWN_PERIOD_SECOND, TimeUnit.SECONDS)
                                        .subscribe(aLong -> ((Activity) context).runOnUiThread(() -> CallBack.countDown(convertCountDownTime(aLong))));
        if (AD_COUNTDOWN_END_SECOND == 0) {
            CallBack.countDown(0);
        }

    }

    @Override
    public void onDestroy() {
        if (!countDownDisposable.isDisposed()) {
            countDownDisposable.dispose();
        }
    }


    /**
     * 关闭定时器
     */
    private void closeTimer() {
        if (welcomeDisposable != null && !welcomeDisposable.isDisposed()) {
            welcomeDisposable.dispose();
        }
    }

    /**
     * 计时器是从0到4开始计时，需要反转一下
     *
     * @param countDownTime
     * @return
     */
    private long convertCountDownTime(long countDownTime) {
        countDownTime = AD_COUNTDOWN_END_SECOND - countDownTime;
        return countDownTime;
    }
}
