package com.example.administrator.test.base.activity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.test.animation.AnimatorHelper;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.test.R;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.util.OnMultiClickListener;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * @author koo
 */
public abstract class BaseActivity<P extends IBasePresenter> extends SwipeBackActivity implements View.OnClickListener {
    protected       DrawerLayout    drawerRootLayout;
    protected       NavigationView  navView;
    protected       Toolbar         mToolbar;
    private         TextView        tvCenterTitle;
    protected       P               presenter;
    private         SwipeBackLayout backLayout;
    /**
     * 是否沉浸状态栏
     **/
    private         boolean         isSetStatusBar     = true;
    /**
     * 是否允许全屏
     **/
    private         boolean         mAllowFullScreen   = true;
    /**
     * 是否禁止旋转屏幕
     **/
    private         boolean         isAllowScreenRoate = false;
    /**
     * 当前Activity渲染的视图View
     **/
    private         View            mContextView       = null;
    /**
     * 日志输出标志
     **/
    protected final String          TAG                = this.getClass().getSimpleName();

    /**
     * 转场动画中心坐标
     */
    private int revealX;
    private int revealY;

    /**
     * activity转场动画持续时间
     */
    private static final int ACTIVITY_ANIMATOR_DURATION = 400;

    /**
     * View点击
     **/
    public abstract void widgetClick(View v);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);

        Bundle bundle = getIntent().getExtras();
        initParameter(bundle);

        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        initMainLayout();
        ButterKnife.bind(this);
        initToolbar();

        drawerRootLayout = (DrawerLayout) findViewById(R.id.base_root_dl);
        navView = (NavigationView) findViewById(R.id.base_root_nav_view);
        presenter = createPresenter();

        startActivityAnimation();

        initSwipeLayout();
        initView(savedInstanceState);
        setListener();

        if (isSetStatusBar) {
            steepStatusBar();
        }
        //安卓版本是否8.0
        boolean isOreoSDK = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O || Build.VERSION.SDK_INT < Build.VERSION_CODES.P;
        //安卓8.0固定透明背景的activity方向会崩溃
        if (!isOreoSDK) {
            if (!isAllowScreenRoate) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }
        initData(this);
    }

    @Override
    public void onBackPressed() {
        finishActivity();
    }

    /**
     * 结束activity
     */
    private void finishActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = createRevealAnimator(true, revealX, revealY);
            animator.start();
        }
        else {
            finish();
        }
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
        drawerRootLayout.post(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                revealX = getIntent().getIntExtra("x", 0);
                revealY = getIntent().getIntExtra("y", 0);
                AnimatorHelper.resetXY();
                Animator animator = createRevealAnimator(false, revealX, revealY);
                animator.start();
            }
            drawerRootLayout.setVisibility(View.VISIBLE);
        });

    }

    /**
     * 初始化主布局
     */
    private void initMainLayout() {
        setContentView(getRootLayoutId());

        if (0 != bindContentLayout()) {
            getLayoutInflater().inflate(bindContentLayout(), (ViewGroup) findViewById(R.id.content_layout_ll));
        }
        if (0 != bindTopLayout()) {
            getLayoutInflater().inflate(bindTopLayout(), (ViewGroup) findViewById(R.id.base_root_top_layout_ll));
        }
        if (0 != bindBottomLayout()) {
            getLayoutInflater().inflate(bindBottomLayout(), (ViewGroup) findViewById(R.id.base_root_bottom_layout_ll));
        }
    }

    /**
     * 初始化滑动布局
     */
    private void initSwipeLayout() {
        //滑动退出布局
        backLayout = getSwipeBackLayout();
        backLayout.setEnabled(true);
        backLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    /**
     * 初始化toolbar
     */
    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        tvCenterTitle = (TextView) findViewById(R.id.base_root_title_tv);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(getHomeButtonEnabled());
        getSupportActionBar().setDisplayHomeAsUpEnabled(getDisplayHomeAsUpEnabled());
        mToolbar.setOnMenuItemClickListener(menuItem -> {
            onMenuClickListener(menuItem.getItemId());
            return true;
        });

        mToolbar.setBackgroundResource(R.color.tool_bar_base_background);

        mToolbar.setNavigationOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                OnNavigationOnClick();
            }
        });
    }

    protected boolean getHomeButtonEnabled() {
        return false;
    }

    protected boolean getDisplayHomeAsUpEnabled() {
        return true;
    }

    /**
     * [沉浸状态栏]
     */
    private void steepStatusBar() {
        setStatusBarFullTransparent();
    }

    /**
     * 全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {
            //21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
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
        float maxRadius   = (float) Math.hypot(drawerRootLayout.getHeight(), drawerRootLayout.getWidth());
        float startRadius = reversed ? maxRadius : 0;
        float endRadius   = reversed ? 0 : maxRadius;

        Animator animator = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            animator = ViewAnimationUtils.createCircularReveal(
                    drawerRootLayout, x, y,
                    startRadius,
                    endRadius);
        }
        animator.setDuration(ACTIVITY_ANIMATOR_DURATION);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        if (reversed) {
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (reversed) {
                        drawerRootLayout.setVisibility(View.INVISIBLE);
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
        }
        return animator;
    }

    /**
     * [初始化参数]
     *
     * @param parameter
     */
    public abstract void initParameter(Bundle parameter);

    /**
     * [绑定视图]
     *
     * @return
     */
    public abstract View bindView();

    /**
     * [绑定内容布局]
     *
     * @return
     */
    public abstract int bindContentLayout();

    /**
     * [绑定顶部布局]
     *
     * @return
     */
    public abstract int bindTopLayout();

    /**
     * [绑定底部布局]
     *
     * @return
     */
    public abstract int bindBottomLayout();

    /**
     * [绑定菜单]
     *
     * @return
     */
    public abstract int bindMenu();

    /**
     * [初始化控件]
     *
     * @param savedInstanceState
     */
    public abstract void initView(Bundle savedInstanceState);

    protected abstract P createPresenter();

    /**
     * [toolbar点击回调]
     */
    protected void OnNavigationOnClick() {
        finishActivity();
    }

    /**
     * [绑定控件]
     *
     * @param resId
     * @return
     */
    protected <T extends View> T $(int resId) {
        return (T) super.findViewById(resId);
    }

    /**
     * [设置监听]
     */
    public abstract void setListener();

    /**
     * [按钮监听回调]
     *
     * @param menuId
     */
    public abstract void onMenuClickListener(int menuId);

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    /**
     * [业务操作]
     *
     * @param mContext
     */
    public abstract void initData(Context mContext);


    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        try {
            getMenuInflater().inflate(bindMenu(), menu);
        }
        catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showLongToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示Toast
     *
     * @param resId
     */
    public void showToast(@StringRes int resId) {
        Toast.makeText(this, getResources().getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示长时间Toast
     *
     * @param resId
     */
    public void showLongToast(@StringRes int resId) {
        Toast.makeText(this, getResources().getString(resId), Toast.LENGTH_LONG).show();
    }


    /**
     * [是否允许屏幕旋转]
     *
     * @param isAllowScreenRoate
     */
    public void setScreenRoate(boolean isAllowScreenRoate) {
        this.isAllowScreenRoate = isAllowScreenRoate;
    }

    /**
     * [是否允许全屏]
     *
     * @param allowFullScreen
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    /**
     * [是否设置沉浸状态栏]
     *
     * @param isSetStatusBar
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * 设置toolbar的子标题
     *
     * @param subtitle
     */
    public void setSubtitle(String subtitle) {
        mToolbar.setSubtitle(subtitle);
    }

    /**
     * 设置toolbar的标题
     *
     * @param title
     */
    public void setTooBarTitle(String title) {
        mToolbar.setTitle(title);
    }

    /**
     * 设置toolBar左边图标
     *
     * @param resId
     */
    public void setNavigationIcon(@DrawableRes int resId) {
        mToolbar.setNavigationIcon(resId);
    }

    /**
     * 设置toolbar的子标题
     *
     * @param subtitleId
     */
    public void setToobarSubTitle(int subtitleId) {
        mToolbar.setSubtitle(subtitleId);
    }

    /**
     * 设置toolbar的标题
     *
     * @param titleId
     */
    public void setTooBarTitle(int titleId) {
        mToolbar.setTitle(titleId);
    }

    /**
     * 设置toolbar的logo
     *
     * @param logo
     */
    public void setToolbarLogo(Drawable logo) {
        mToolbar.setLogo(logo);
    }

    /**
     * 是否显示中心标题
     *
     * @param isShowCenterTitle
     */
    public void showCenterTitle(boolean isShowCenterTitle) {
        if (isShowCenterTitle) {
            tvCenterTitle.setVisibility(View.VISIBLE);
        }
        else {
            tvCenterTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 设置侧边滑动模式
     *
     * @param mode SwipeBackLayout.EDGE_ALL     设置为向所有方向都可以滑动
     *             SwipeBackLayout.EDGE_LEFT    设置为向左滑动
     *             SwipeBackLayout.EDGE_RIGHT   设置为向右滑动
     *             SwipeBackLayout.EDGE_BOTTOM  设置为向下滑动
     */
    public void setEdgeTrackingMode(int mode) {
        ////设置为向所有方向都可以滑动
        backLayout.setEdgeTrackingEnabled(mode);
    }

    /**
     * 设置是否支持侧边滑动退出
     *
     * @param enable
     */
    public void setEnableGesture(boolean enable) {
        //设置为不能滑动
        backLayout.setEnableGesture(enable);
    }

    /**
     * 设置中心标题size
     *
     * @param titleSize
     */
    public void setCenterTitleSize(int titleSize) {
        tvCenterTitle.setTextSize(titleSize);
    }

    /**
     * 设置中心标题
     *
     * @param title
     */
    public void setCenterTitle(String title) {
        tvCenterTitle.setText(title);
    }

    /**
     * 设置中心标题
     *
     * @param titleId
     */
    public void setCenterTitle(int titleId) {
        tvCenterTitle.setText(titleId);
    }

    /**
     * 设置title color
     *
     * @param color
     */
    public void setBarTitleColor(int color) {
        mToolbar.setTitleTextColor(color);
    }

    /**
     * 设置返回按钮样式
     *
     * @param icon
     */
    public void setBarNaviIcon(int icon) {
        mToolbar.setNavigationIcon(icon);
    }

    /**
     * 设置根背景颜色
     *
     * @param color
     */
    public void setRootLayoutBackGround(int color) {
        findViewById(R.id.base_root_cl).setBackgroundColor(color);
    }

    public void hideToolBar() {
        mToolbar.setVisibility(View.GONE);
    }

    /**
     * 设置返回按钮样式
     *
     * @param icon
     */
    public void setBarNaviIcon(Drawable icon) {
        mToolbar.setNavigationIcon(icon);
    }

    public int getToolBarBottom() {
        return mToolbar.getBottom();
    }

    public void setToolBarBackgroundColor(int color) {
        mToolbar.setBackgroundColor(color);
    }

    public void setToolBarBackgroundResource(@ColorRes int resource) {
        mToolbar.setBackgroundResource(resource);
    }

    public void setToolBarVisible(boolean isVisible) {
        if (isVisible) {
        }
    }

    /**
     * 获取根布局
     *
     * @return R.layout.layout_base_root(toolbar不会根据列表滑动而滑动)
     * R.layout.layout_base_root_animation(toolbar会根据列表滑动而滑动)
     */
    private int getRootLayoutId() {
        if (isToolBarAnimation()) {
            return R.layout.layout_base_root_animation;
        }
        return R.layout.layout_base_root;
    }

    /**
     * 是否使用toolbar动画布局
     *
     * @return
     */
    protected boolean isToolBarAnimation() {
        return false;
    }

    /**
     * 打开侧边菜单
     */
    protected void openDrawer() {
        // 开启菜单
        drawerRootLayout.openDrawer(GravityCompat.START);
    }
}