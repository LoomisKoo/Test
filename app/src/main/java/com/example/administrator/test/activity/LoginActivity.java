package com.example.administrator.test.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.activity.BaseActivity;
import com.example.administrator.test.mvp.contract.LogoutContract;
import com.example.administrator.test.mvp.model.LogoutModel;
import com.example.administrator.test.mvp.presenter.LogoutPresenter;
import com.example.administrator.test.util.ACache;
import com.example.administrator.test.util.ArouterHelper;

import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;

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
public class LoginActivity extends BaseActivity<LogoutPresenter> implements LogoutContract.View {
    @BindView(R.id.login_github_btn)
    Button           loginGithubBtn;
    @BindView(R.id.login_play_android_btn)
    Button           loginPlayAndroidBtn;
    @BindView(R.id.root_layout)
    ConstraintLayout rootLayout;

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
        setCenterTitle("选择登录平台");
        AnimatorHelper.setViewTouchListener(loginGithubBtn);
        AnimatorHelper.setViewTouchListener(loginPlayAndroidBtn);


        Bitmap bg = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_login);
        bg = rsBlur(this, bg, 25);
        rootLayout.setBackground(new BitmapDrawable(getResources(), bg));

        initPlayAndroidBtnStyle();
    }

    @Override
    protected LogoutPresenter createPresenter() {
        return new LogoutPresenter(this, new LogoutModel(), this);
    }

    @Override
    public void setListener() {
        loginGithubBtn.setOnClickListener(v -> ARouter.getInstance()
                                                      .build(ArouterHelper.ROUTE_ACTIVITY_WEB)
                                                      .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                                      .withString("title", "登录Github")
                                                      .withString("url", GITHUB_URL)
                                                      .withInt("x", AnimatorHelper.getDownX())
                                                      .withInt("y", AnimatorHelper.getDownY())
                                                      .navigation());
    }

    @Override
    public void onMenuClickListener(int menuId) {

    }

    @Override
    public void initData(Context mContext) {

    }

    /**
     * 高斯模糊
     *
     * @param context
     * @param source
     * @param radius
     * @return
     */
    private static Bitmap rsBlur(Context context, Bitmap source, int radius) {

        Bitmap inputBmp = source;
        //(1)
        RenderScript renderScript = RenderScript.create(context);


        // Allocate memory for Renderscript to work with
        //(2)
        final Allocation input  = Allocation.createFromBitmap(renderScript, inputBmp);
        final Allocation output = Allocation.createTyped(renderScript, input.getType());
        //(3)
        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        //(4)
        scriptIntrinsicBlur.setInput(input);
        //(5)
        // Set the blur radius
        scriptIntrinsicBlur.setRadius(radius);
        //(6)
        // Start the ScriptIntrinisicBlur
        scriptIntrinsicBlur.forEach(output);
        //(7)
        // Copy the output to the blurred bitmap
        output.copyTo(inputBmp);
        //(8)
        renderScript.destroy();

        return inputBmp;
    }

    @Override
    public void logoutSuccess() {
        showToast("已退出 玩安卓");
        initPlayAndroidBtnStyle();
    }

    @Override
    public void logoutFail(String msg) {
        showToast(msg);
        initPlayAndroidBtnStyle();
    }

    /**
     * 初始化玩安卓登录按钮
     */
    private void initPlayAndroidBtnStyle() {
        ACache mCache = ACache.get(this);
        if (null == mCache.getAsObject("user")) {
            loginPlayAndroidBtn.setText("玩安卓");
            loginPlayAndroidBtn.setOnClickListener(v -> ARouter.getInstance()
                                                               .build(ArouterHelper.ROUTE_ACTIVITY_LOGIN_PLAY_ANDROID)
                                                               .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                                               .withInt("x", AnimatorHelper.getDownX())
                                                               .withInt("y", AnimatorHelper.getDownY())
                                                               .navigation(this));
        }
        else {
            loginPlayAndroidBtn.setText("退出玩安卓");
            loginPlayAndroidBtn.setOnClickListener(v -> presenter.logout());
        }
    }
}
