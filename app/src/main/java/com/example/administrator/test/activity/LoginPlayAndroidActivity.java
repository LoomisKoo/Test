package com.example.administrator.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.administrator.test.base.activity.BaseActivity;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.util.ArouterHelper;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.activity
 * @ClassName: LoginPlayAndroidActivity
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/8 9:59 AM
 * @UpdateUser:
 * @UpdateDate: 2019/1/8 9:59 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Route(path = ArouterHelper.ROUTE_ACTIVITY_LOGIN_PLAY_ANDROID)
public class LoginPlayAndroidActivity extends BaseActivity {
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
}
