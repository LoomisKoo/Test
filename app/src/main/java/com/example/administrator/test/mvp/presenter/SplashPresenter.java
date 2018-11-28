package com.example.administrator.test.mvp.presenter;

import android.content.Context;

import com.example.administrator.test.mvp.contract.SplashContract;
import com.example.administrator.test.mvp.model.SplashModel;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.presenter
 * @ClassName: SplashPresenter
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2018/11/28 11:31 AM
 * @UpdateUser:
 * @UpdateDate: 2018/11/28 11:31 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SplashPresenter implements SplashContract.Presenter, SplashContract.CallBack {
    private SplashContract.View view;
    private SplashModel         model;

    public SplashPresenter(Context context, SplashContract.View view) {
        this.view = view;
        this.model = new SplashModel(context, this);
    }


    @Override
    public void showWelcome() {
        model.showWelcome();
    }

    @Override
    public void countDownAD() {
        model.countDownAD();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void countDown(long countDown) {
        view.countDownAD(countDown);
    }

    @Override
    public void finishShowWelcome() {
        view.showLocalAD(model.getLocalADResID());
    }
}
