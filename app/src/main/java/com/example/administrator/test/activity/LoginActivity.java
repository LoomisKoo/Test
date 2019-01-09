package com.example.administrator.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseActivity;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.util.ArouterHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.activity
 * @ClassName: LoginActivity
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/8 9:36 AM
 * @UpdateUser:
 * @UpdateDate: 2019/1/8 9:36 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Route(path = ArouterHelper.ROUTE_ACTIVITY_LOGIN)
public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_github_btn)
    Button loginGithubBtn;
    @BindView(R.id.login_play_android_btn)
    Button loginPlayAndroidBtn;

    private static final String GITHUB_URL = "https://github.com/login";

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
        return R.layout.activity_login;
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
        showCenterTitle(true);
        setCenterTitle("登录");

        loginGithubBtn.setOnClickListener(v -> ARouter.getInstance().build(ArouterHelper.ROUTE_ACTIVITY_WEB).withString("title", "登录Github").withString("url", GITHUB_URL).navigation());

        loginPlayAndroidBtn.setOnClickListener(v -> ARouter.getInstance().build(ArouterHelper.ROUTE_ACTIVITY_LOGIN_PLAY_ANDROID).navigation());

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
