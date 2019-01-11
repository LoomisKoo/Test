package com.example.administrator.test.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.activity.BaseActivity;
import com.example.administrator.test.animation.interpolator.LoginInterpolator;
import com.example.administrator.test.mvp.contract.LoginContract;
import com.example.administrator.test.mvp.model.LoginModel;
import com.example.administrator.test.mvp.presenter.LoginPresenter;
import com.example.administrator.test.util.ArouterHelper;

import androidx.cardview.widget.CardView;
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
public class LoginPlayAndroidActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
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

    String userName;
    String password;

    @BindView(R.id.tv_user_name)
    AutoCompleteTextView tvUserName;
    @BindView(R.id.tv_password)
    AutoCompleteTextView tvPassword;
    @BindView(R.id.btn_login)
    Button               btnLogin;
    @BindView(R.id.btn_register)
    Button               btnRegister;
    @BindView(R.id.pgb_loading)
    ProgressBar          pgbLoading;
    @BindView(R.id.card_view)
    CardView             cardView;

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
        AnimatorHelper.setViewTouchListener(btnRegister);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this, new LoginModel(), this);
    }

    @Override
    public void setListener() {
        //登录
        btnLogin.setOnClickListener(v -> {
            userName = tvUserName.getText().toString().trim();
            password = tvPassword.getText().toString().trim();

            boolean isInsertEmpty = isInsertEmpty(userName, password);
            if (!isInsertEmpty) {
                login();
//                inputAnimator(true);
            }
        });

        //注册
        btnRegister.setOnClickListener(v -> {
            String userName   = tvUserName.getText().toString().trim();
            String password   = tvPassword.getText().toString().trim();
            String rePassword = tvPassword.getText().toString().trim();

            isInsertEmpty(userName, password);
            presenter.register(userName, password, rePassword);
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

    /**
     * 检查账号密码是否为空
     *
     * @param userName
     * @param password
     */
    private boolean isInsertEmpty(String userName, String password) {
        boolean isEmptyUserName = StringUtils.isEmpty(userName);
        boolean isEmptyPassword = StringUtils.isEmpty(password);
        if (isEmptyUserName || isEmptyPassword) {
            showToast("账号和密码不能为空");
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 输入框的动画效果
     *
     * @param isLogin
     */
    private void inputAnimator(boolean isLogin) {
        LoginLayoutAnimatorSet = new AnimatorSet();
        ValueAnimator animator = ValueAnimator.ofFloat(0, cardView.getMeasuredWidth());
        animator.addUpdateListener(animation -> {
            float value = (Float) animation.getAnimatedValue();
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) cardView
                    .getLayoutParams();
            params.leftMargin = (int) value;
            params.rightMargin = (int) value;
            cardView.setLayoutParams(params);
        });
        ObjectAnimator animator2 = null;
        if (isLogin) {
            animator2 = ObjectAnimator.ofFloat(cardView,
                                               "scaleX", LOGIN_INSERT_LAYOUT_ANIMATOR_SCALE_MAX, LOGIN_INSERT_LAYOUT_ANIMATOR_SCALE_MIN);
        }
        else {
            animator2 = ObjectAnimator.ofFloat(cardView,
                                               "scaleX", LOGIN_INSERT_LAYOUT_ANIMATOR_SCALE_MIN, LOGIN_INSERT_LAYOUT_ANIMATOR_SCALE_MAX);
        }
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
                if (isLogin) {
                    login();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
    }

    /**
     * 登录
     */
    private void login() {
        progressAnimator(pgbLoading);
        cardView.setVisibility(View.INVISIBLE);
        btnLogin.setEnabled(false);
        presenter.login(userName, password);
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
     * 获取LoadingView的中心坐标
     *
     * @return
     */
    private int[] getLoadingViewPoint() {
        int[] point = new int[2];
        pgbLoading.getLocationInWindow(point);
        point[0] += pgbLoading.getWidth() / 2;
        point[1] += pgbLoading.getHeight() / 2;
        return point;
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
        inputAnimator(false);
        cardView.setVisibility(View.VISIBLE);
    }

    @Override
    public void loginSuccess() {
        ARouter.getInstance().build(ArouterHelper.ROUTE_ACTIVITY_MAIN).withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION).withInt("x", getLoadingViewPoint()[0]).withInt("y", getLoadingViewPoint()[1]).navigation(this);
        finishActivity();

    }

    @Override
    public void loginFail(String msg) {
        showToast(msg);
        btnLogin.setEnabled(true);
        cancelAnimator();
    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void registerFail(String msg) {
        showToast(msg);
    }
}
