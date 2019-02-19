package com.example.administrator.test.base.activity;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.mvp.base.IBasePresenter;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.base.activity
 * @ClassName: BaseAnimationActivity
 * @Description: java类作用描述    转场动画的activity基类
 * @Author: koo
 * @CreateDate: 2019/2/19 5:34 PM
 * @UpdateUser:
 * @UpdateDate: 2019/2/19 5:34 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class BaseAnimationActivity<P extends IBasePresenter> extends BaseActivity<P> {
    /**
     * 转场动画中心坐标
     */
    private int revealX;
    private int revealY;

    /**
     * activity转场动画持续时间
     */
    private static final int ACTIVITY_ANIMATOR_DURATION = 400;

    private boolean isFinishBeforeAnimator  = false;
    /**
     * 是否允许activity转场动画
     */
    private boolean isAllowActivityAnimator = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isAllowActivityAnimator) {
            startActivityAnimation();
        }
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    protected P createPresenter() {
        return null;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData(Context mContext) {

    }

    @Override
    public void onBackPressed() {
        finishActivity();
    }

    @Override
    public void finish() {
        super.finish();
        //防止转场动画闪屏
        overridePendingTransition(0, 0);
    }

    /**
     * activity跳转时揭露动画
     */
    private void startActivityAnimation() {
        //view绘制完成后开始动画
        getWindow().getDecorView()
                   .addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                       @Override
                       public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                               v.removeOnLayoutChangeListener(this);
                               revealX = getIntent().getIntExtra("x", 0);
                               revealY = getIntent().getIntExtra("y", 0);
                               AnimatorHelper.resetXY();
                               Animator animator = createRevealAnimator(false, revealX, revealY);
                               animator.start();
                           }
                       }
                   });
    }

    /**
     * 结束activity
     */
    protected void finishActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = createRevealAnimator(true, revealX, revealY);
            animator.start();
        }
        else {
            finish();
        }
    }

    /**
     * 揭露动画
     *
     * @param reversed
     * @param x
     * @param y
     * @return
     */
    private Animator createRevealAnimator(boolean reversed, int x, int y) {
        float maxRadius = (float) Math.hypot(getWindow().getDecorView()
                                                        .getHeight(), getWindow().getDecorView()
                                                                                 .getWidth());
        float startRadius = reversed ? maxRadius : 0;
        float endRadius   = reversed ? 0 : maxRadius;

        Animator animator = null;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animator = ViewAnimationUtils.createCircularReveal(
                    getWindow().getDecorView(), x, y,
                    startRadius,
                    endRadius);
        }
        animator.setDuration(ACTIVITY_ANIMATOR_DURATION);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (reversed) {
                    //避免闪屏问题
                    getWindow().getDecorView()
                               .setVisibility(View.INVISIBLE);
                    finish();
                }
                if (isFinishBeforeAnimator) {
                    finish();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return animator;
    }

    public boolean isAllowActivityAnimator() {
        return isAllowActivityAnimator;
    }

    public void setAllowActivityAnimator(boolean allowActivityAnimator) {
        isAllowActivityAnimator = allowActivityAnimator;
    }

    public boolean isFinishBeforeAnimator() {
        return isFinishBeforeAnimator;
    }

    public void setFinishBeforeAnimator(boolean finishBeforeAnimator) {
        isFinishBeforeAnimator = finishBeforeAnimator;
    }
}
