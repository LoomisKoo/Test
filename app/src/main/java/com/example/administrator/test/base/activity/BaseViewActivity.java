package com.example.administrator.test.base.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.test.R;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.util.OnMultiClickListener;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.base.activity
 * @ClassName: BaseViewActivity  封装了基本布局的activity
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/18 4:35 PM
 * @UpdateUser:
 * @UpdateDate: 2019/2/18 4:35 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class BaseViewActivity<P extends IBasePresenter> extends BaseAnimationActivity<P> implements View.OnClickListener {
    protected DrawerLayout     drawerRootLayout;
    protected NavigationView   navView;
    protected Toolbar          mToolbar;
    private   TextView         tvCenterTitle;
    protected LinearLayout     topLayout;
    protected ConstraintLayout clContentLayout;
    /**
     * 是否沉浸状态栏
     **/
    private   boolean          isSetStatusBar     = true;
    /**
     * 是否允许全屏
     **/
    private   boolean          mAllowFullScreen   = true;
    /**
     * 是否禁止旋转屏幕
     **/
    private   boolean          isAllowScreenRoate = false;

    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass()
                                     .getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        Bundle bundle = getIntent().getExtras();
        initParameter(bundle);

        initMainLayout();
        initToolbar();

        clContentLayout = (ConstraintLayout) findViewById(R.id.base_root_cl);
        drawerRootLayout = (DrawerLayout) findViewById(R.id.base_root_dl);
        lockDrawer(true);
        navView = (NavigationView) findViewById(R.id.base_root_nav_view);

        super.onCreate(savedInstanceState);

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
            topLayout = (LinearLayout) findViewById(R.id.base_root_top_layout_ll);
            getLayoutInflater().inflate(bindTopLayout(), topLayout);
        }
        if (0 != bindBottomLayout()) {
            getLayoutInflater().inflate(bindBottomLayout(), (ViewGroup) findViewById(R.id.base_root_bottom_layout_ll));
        }
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
        //高于Android5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView()
                  .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                                 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        //高于Android 4.4
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
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
     * [toolbar点击回调]
     */
    protected void OnNavigationOnClick() {
        finishActivity();
    }

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
    public void setToolBarTitle(String title) {
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
    public void setToolbarSubTitle(int subtitleId) {
        mToolbar.setSubtitle(subtitleId);
    }

    /**
     * 设置toolbar的标题
     *
     * @param titleId
     */
    public void setToolBarTitle(int titleId) {
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
        clContentLayout.setBackgroundColor(color);
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

    /**
     * 禁止/打开手势滑动
     */
    protected void lockDrawer(boolean lock) {
        if (lock) {
            drawerRootLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        else {
            drawerRootLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }
}
