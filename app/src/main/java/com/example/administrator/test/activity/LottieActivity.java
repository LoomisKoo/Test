package com.example.administrator.test.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseViewActivity;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.util.ArouteHelper;

import butterknife.BindView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.activity
 * @ClassName: LottieActivity
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/3/3 14:24
 * @UpdateUser:
 * @UpdateDate: 2019/3/3 14:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Route(path = ArouteHelper.ROUTE_ACTIVITY_LOTTIE)
public class LottieActivity extends BaseViewActivity {
    @BindView(R.id.lottie)
    LottieAnimationView lottie;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
//        ButterKnife.bind(this);

        lottie.loop(true);
        lottie.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


        lottie.addAnimatorUpdateListener(animation -> {
        });
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
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
        return R.layout.layout_lottie;
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
    public void onMenuClickListener(int menuId) {

    }
}
