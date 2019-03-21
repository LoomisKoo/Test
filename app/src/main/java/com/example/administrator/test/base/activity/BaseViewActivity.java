package com.example.administrator.test.base.activity;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
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
import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.base_root_title_tv)
    TextView baseRootTitleTv;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.base_root_top_layout_ll)
    LinearLayout baseRootTopLayoutLl;
    @BindView(R.id.content_layout_ll)
    protected LinearLayout contentLayoutLl;
    @BindView(R.id.base_root_bottom_layout_ll)
    LinearLayout     baseRootBottomLayoutLl;
    @BindView(R.id.base_root_cl)
    ConstraintLayout baseRootCl;
    @BindView(R.id.base_root_nav_view)
    protected NavigationView baseRootNavView;
    @BindView(R.id.base_root_dl)
    protected DrawerLayout   baseRootDl;

    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar     = true;
    /**
     * 是否允许全屏
     **/
    private boolean mAllowFullScreen   = true;
    /**
     * 是否禁止旋转屏幕
     **/
    private boolean isAllowScreenRoate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        Bundle bundle = getIntent().getExtras();
        initParameter(bundle);

        initMainLayout();

        initToolbar();

        lockDrawer(true);

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

    @Override
    protected void onDestroy() {
        ButterKnife.bind(this)
                   .unbind();
        super.onDestroy();
    }

    /**
     * 初始化主布局
     */
    private void initMainLayout() {
        setContentView(getRootLayoutId());
        //ButterKnife会先找该activity的子类的view进行绑定，但是子类的view还没inflate，此处会崩溃。因此需要try-catch
        try {
            //这里绑定的是 父view（contentLayoutLl、baseRootTopLayoutLl、baseRootBottomLayoutLl等）
            ButterKnife.bind(this);
        }
        catch (Exception e) {

        }

        if (0 != bindContentLayout()) {
            getLayoutInflater().inflate(bindContentLayout(), contentLayoutLl);
        }
        if (0 != bindTopLayout()) {
            getLayoutInflater().inflate(bindTopLayout(), baseRootTopLayoutLl);
        }
        if (0 != bindBottomLayout()) {
            getLayoutInflater().inflate(bindBottomLayout(), baseRootBottomLayoutLl);
        }

        //ButterKnife会先找该activity的子类的view进行绑定，但是子类的view还没inflate，此处会崩溃。因此需要try-catch
        try {
            //这里绑定的是 子view
            ButterKnife.bind(this);
        }
        catch (Exception e) {

            int a = 0;
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

    }

    /**
     * 初始化toolbar
     */
    private void initToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(getHomeButtonEnabled());
        getSupportActionBar().setDisplayHomeAsUpEnabled(getDisplayHomeAsUpEnabled());
        toolbar.setOnMenuItemClickListener(menuItem -> {
            onMenuClickListener(menuItem.getItemId());
            return true;
        });
        toolbar.setBackgroundResource(R.color.tool_bar_base_background);

        toolbar.setNavigationOnClickListener(new OnMultiClickListener() {
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
        BarUtils.setStatusBarAlpha(this, 0);
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
        if (0 != bindMenu()) {
            getMenuInflater().inflate(bindMenu(), menu);
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
        toolbar.setSubtitle(subtitle);
    }

    /**
     * 设置toolbar的标题
     *
     * @param title
     */
    public void setToolBarTitle(String title) {
        toolbar.setTitle(title);
    }

    /**
     * 设置toolBar左边图标
     *
     * @param resId
     */
    public void setNavigationIcon(@DrawableRes int resId) {
        toolbar.setNavigationIcon(resId);
    }

    /**
     * 设置toolbar的子标题
     *
     * @param subtitleId
     */
    public void setToolbarSubTitle(int subtitleId) {
        toolbar.setSubtitle(subtitleId);
    }

    /**
     * 设置toolbar的标题
     *
     * @param titleId
     */
    public void setToolBarTitle(int titleId) {
        toolbar.setTitle(titleId);
    }

    /**
     * 设置toolbar的logo
     *
     * @param logo
     */
    public void setToolbarLogo(Drawable logo) {
        toolbar.setLogo(logo);
    }

    /**
     * 是否显示中心标题
     *
     * @param isShowCenterTitle
     */
    public void showCenterTitle(boolean isShowCenterTitle) {
        if (isShowCenterTitle) {
            baseRootTitleTv.setVisibility(View.VISIBLE);
        }
        else {
            baseRootTitleTv.setVisibility(View.GONE);
        }
    }

    /**
     * 设置中心标题size
     *
     * @param titleSize
     */
    public void setCenterTitleSize(int titleSize) {
        baseRootTitleTv.setTextSize(titleSize);
    }

    /**
     * 设置中心标题
     *
     * @param title
     */
    public void setCenterTitle(String title) {
        baseRootTitleTv.setText(title);
    }

    /**
     * 设置中心标题
     *
     * @param titleId
     */
    public void setCenterTitle(int titleId) {
        baseRootTitleTv.setText(titleId);
    }

    /**
     * 设置title color
     *
     * @param color
     */
    public void setBarTitleColor(int color) {
        toolbar.setTitleTextColor(color);
    }

    /**
     * 设置返回按钮样式
     *
     * @param icon
     */
    public void setBarNaviIcon(int icon) {
        toolbar.setNavigationIcon(icon);
    }

    /**
     * 设置根背景颜色
     *
     * @param color
     */
    public void setRootLayoutBackGround(int color) {
        baseRootCl.setBackgroundColor(color);
    }

    public void hideToolBar() {
        toolbar.setVisibility(View.GONE);
    }

    /**
     * 设置返回按钮样式
     *
     * @param icon
     */
    public void setBarNaviIcon(Drawable icon) {
        toolbar.setNavigationIcon(icon);
    }

    public int getToolBarBottom() {
        return toolbar.getBottom();
    }

    public void setToolBarBackgroundColor(int color) {
        toolbar.setBackgroundColor(color);
    }

    public void setToolBarBackgroundResource(@ColorRes int resource) {
        toolbar.setBackgroundResource(resource);
    }

    public void setToolBarVisible(boolean isVisible) {
        if (isVisible) {
        }
    }

    /**
     * 获取根布局
     */
    private int getRootLayoutId() {
        return R.layout.layout_base_root;
    }

    /**
     * 打开侧边菜单
     */
    protected void openDrawer() {
        // 开启菜单
        baseRootDl.openDrawer(GravityCompat.START);
    }

    /**
     * 关闭侧边菜单
     */
    protected void closeDrawer() {
        baseRootDl.closeDrawers();
    }

    /**
     * 禁止/打开手势滑动
     */
    protected void lockDrawer(boolean lock) {
        if (lock) {
            baseRootDl.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        else {
            baseRootDl.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    protected View getMenuView(int id) {
        final MenuItem item = toolbar.getMenu()
                                     .findItem(id);
        if (null == item) {
            return null;
        }
        return item.getActionView();
    }
}
