package com.example.administrator.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseActivity;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.mvp.contract.SplashContract;
import com.example.administrator.test.util.ArouterHelper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.activity
 * @ClassName: SplashActivity
 * @Description: java类作用描述  该activity比较简单，所以不采用mvp模式
 * @Author: koo
 * @CreateDate: 2018/11/28 11:28 AM
 * @UpdateUser:
 * @UpdateDate: 2018/11/28 11:28 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SplashActivity extends BaseActivity implements SplashContract.View {
    private Disposable mDisposable;
    private static final int SHOW_WELCOME_TIME_SECOND = 3;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParameter(Bundle parameter) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindContentLayout() {
        return 0;
    }

    @Override
    public int bindTopLayout() {
        return 0;
    }

    @Override
    public int bindBottomLayout() {
        return 0;
    }

    @Override
    public int bindMenu() {
        return 0;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        //隐藏toolbar、把背景设透明，显示出欢迎页
        hideToolBar();
        setRootLayoutBackGround(getResources().getColor(R.color.activity_root_layout_background));

        final View view = View.inflate(this, R.layout.activiyt_welcome, null);
        setContentView(view);

        welcome();
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onMenuClickListener(int menuId) {

    }

    @Override
    public void initData(Context mContext) {

    }


    @Override
    public void showLocalAD(List<String> localADPath) {

    }

    @Override
    public void showWelcome() {

    }

    private void welcome() {
        Observable.timer(SHOW_WELCOME_TIME_SECOND, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Long value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        closeTimer();
                        ARouter.getInstance().build(ArouterHelper.ROUTE_ACTIVITY_MAIN).navigation();
                        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
                        finish();
                    }
                });
    }

    /**
     * 关闭定时器
     */
    public void closeTimer() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
