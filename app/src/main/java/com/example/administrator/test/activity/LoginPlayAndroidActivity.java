package com.example.administrator.test.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseActivity;
import com.example.administrator.test.animation.interpolator.LoginInterpolator;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.util.ArouterHelper;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import butterknife.BindView;

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
    /**
     * 进度条动画参数
     */
    private static final int   PROGRESS_ANIMATOR_DURATION             = 1000;
    private static final float PROGRESS_ANIMATOR_SCALE_MAX            = 1f;
    private static final float PROGRESS_ANIMATOR_SCALE_MIN            = 0.5f;
    /**
     * 账号密码布局动画参数
     */
    private static final int   LOGIN_INSERT_LAYOUT_ANIMATOR_DURATION  = 300;
    private static final float LOGIN_INSERT_LAYOUT_ANIMATOR_SCALE_MAX = 1f;
    private static final float LOGIN_INSERT_LAYOUT_ANIMATOR_SCALE_MIN = 0.4f;

    private AnimatorSet    LoginLayoutAnimatorSet;
    private ObjectAnimator loadAnimator;

    @BindView(R.id.tv_account)
    EditText    tvAccount;
    @BindView(R.id.tv_password)
    EditText    tvPassword;
    @BindView(R.id.btn_login)
    Button      btnLogin;
    @BindView(R.id.btn_register)
    Button      btnRegister;
    @BindView(R.id.pgb_loading)
    ProgressBar pgbLoading;
    @BindView(R.id.cardView)
    CardView    cardView;

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
        return R.layout.activity_login_paly_android;
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
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    public void setListener() {
        btnLogin.setOnClickListener(v -> {

//            ARouter.getInstance().build(ArouterHelper.ROUTE_ACTIVITY_MAIN).withFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).navigation();
//            finish();
            // 隐藏输入框
            cardView.setVisibility(View.VISIBLE);
            presentActivity(pgbLoading);
//            inputAnimator(cardView);
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
        cancelAnimator();
    }

    public void presentActivity(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                                                                     makeSceneTransitionAnimation(this, view, "transition");
//
        int[] position = new int[2];
        view.getLocationInWindow(position);

        int revealX = (position[0]);
        int revealY = (position[1] + view.getHeight() / 2);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(MainActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }

    /**
     * 输入框的动画效果
     *
     * @param view 控件
     */
    private void inputAnimator(final View view) {
        LoginLayoutAnimatorSet = new AnimatorSet();
        ValueAnimator animator = ValueAnimator.ofFloat(0, view.getMeasuredWidth());
        animator.addUpdateListener(animation -> {
            float value = (Float) animation.getAnimatedValue();
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view
                    .getLayoutParams();
            params.leftMargin = (int) value;
            params.rightMargin = (int) value;
            view.setLayoutParams(params);
        });

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(cardView,
                                                          "scaleX", LOGIN_INSERT_LAYOUT_ANIMATOR_SCALE_MAX, LOGIN_INSERT_LAYOUT_ANIMATOR_SCALE_MIN);
        LoginLayoutAnimatorSet.setDuration(LOGIN_INSERT_LAYOUT_ANIMATOR_DURATION);
        LoginLayoutAnimatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        LoginLayoutAnimatorSet.playTogether(animator, animator2);
        LoginLayoutAnimatorSet.start();
        LoginLayoutAnimatorSet.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * 动画结束后，先显示加载的动画，然后再隐藏输入框
                 */
                progressAnimator(pgbLoading);
                cardView.setVisibility(View.INVISIBLE);
                presentActivity(pgbLoading);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
    }

    /**
     * 出现进度动画
     *
     * @param view
     */
    private void progressAnimator(final View view) {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                                                                     PROGRESS_ANIMATOR_SCALE_MIN, PROGRESS_ANIMATOR_SCALE_MAX);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                                                                      PROGRESS_ANIMATOR_SCALE_MIN, PROGRESS_ANIMATOR_SCALE_MAX);
        loadAnimator = ObjectAnimator.ofPropertyValuesHolder(view,
                                                             animator, animator2);
        loadAnimator.setDuration(PROGRESS_ANIMATOR_DURATION);
        loadAnimator.setInterpolator(new LoginInterpolator());
        loadAnimator.start();
    }

    /**
     * 取消动画
     */
    private void cancelAnimator() {
        if (null != LoginLayoutAnimatorSet && LoginLayoutAnimatorSet.isRunning()) {
            LoginLayoutAnimatorSet.cancel();
        }
        if (null != loadAnimator && loadAnimator.isRunning()) {
            loadAnimator.cancel();
        }
    }
}
