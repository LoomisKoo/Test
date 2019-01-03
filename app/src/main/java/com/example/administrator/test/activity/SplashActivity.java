package com.example.administrator.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseActivity;
import com.example.administrator.test.mvp.contract.SplashContract;
import com.example.administrator.test.mvp.presenter.SplashPresenter;
import com.example.administrator.test.util.ArouterHelper;
import com.example.administrator.test.util.OnMultiClickListener;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.activity
 * @ClassName: SplashActivity
 * @Description: java类作用描述   显示欢迎页、广告页
 * @Author: koo
 * @CreateDate: 2018/11/28 11:28 AM
 * @UpdateUser:
 * @UpdateDate: 2018/11/28 11:28 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {
    /**
     * 开屏广告的imageView
     */
    private              ImageView ivAD;
    /**
     * 开屏广告的 跳过 按钮
     */
    private              Button    btnSkip;
    /**
     * 倒计时结束秒数
     */
    private static final int       COUNTDOWN_END_SECOND = 0;

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
        setContentView(R.layout.activiyt_splash);

        ivAD = (ImageView) findViewById(R.id.welcome_ad_iv);
        btnSkip = (Button) findViewById(R.id.welcome_btn_skip);
        presenter.showWelcome();
    }

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this, this);
    }

    @Override
    public void setListener() {
        //点击跳过开屏广告
        btnSkip.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                startMainActivity();
            }
        });
    }

    @Override
    public void onMenuClickListener(int menuId) {

    }

    @Override
    public void initData(Context mContext) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        presenter.onDestroy();
    }

    @Override
    public void showLocalAD(int imgRes) {
        ivAD.setVisibility(View.VISIBLE);
        btnSkip.setVisibility(View.VISIBLE);
        presenter.countDownAD();
        Glide.with(this).load(imgRes).into(ivAD);
    }

    @Override
    public void countDownAD(long countDown) {
        //开屏广告倒计时
        String skip = "跳过（" + countDown + "）";
        btnSkip.setText(skip);
        //倒计时为 0 则跳转主页面
        if (COUNTDOWN_END_SECOND == countDown) {
            startMainActivity();
        }
    }

    /**
     * 跳转主页面
     */
    private void startMainActivity() {
        System.out.println("startMainActivity");
        ARouter.getInstance().build(ArouterHelper.ROUTE_ACTIVITY_MAIN).navigation();
        //跳转动画
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
    }
}
