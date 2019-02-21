package com.example.administrator.test.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.activity.BaseViewActivity;
import com.example.administrator.test.base.fragment.BaseFragment;
import com.example.administrator.test.fragment.BasicKnowledgeFragment;
import com.example.administrator.test.fragment.MovieFragment;
import com.example.administrator.test.fragment.RecommendFragment;
import com.example.administrator.test.manager.ActivityManager;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.util.ACache;
import com.example.administrator.test.util.ArouteHelper;
import com.example.administrator.test.util.UserUtil;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;
import java.util.List;


/**
 * @author
 */
@Route(path = ArouteHelper.ROUTE_ACTIVITY_MAIN)
public class MainActivity extends BaseViewActivity {
    /**
     * viewpager缓存数量
     */
    private static final int VIEW_PAGER_OFFSCREEN_PAGE_LIMIT = 3;

    /**
     * Tab下标
     */
    private static final int TAB_TYPE_DISCOVER = 0;
    private static final int TAB_TYPE_FRIENDS  = 1;
    private static final int TAB_TYPE_MUSIC    = 2;

    /**
     * 记录用户首次点击返回键的时间
     */
    private              long firstBackPressTime       = 0;
    /**
     * 防误触返回间隔时间
     */
    private static final int  BACK_PRESS_INTERVAL_TIME = 2000;

    private BottomBar          mBottomBar;
    private List<BaseFragment> fragments;
    private ViewPager          viewPager;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        return R.layout.activity_main;
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
        return R.menu.base_toolbar_menu;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        initViewPager();
        initTopBar();
        initBottomBar();
        initDrawerLayout();
        setEnableGesture(false);
        //打开侧边栏手势滑动呼出
        lockDrawer(false);
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
        switch (menuId) {
            case R.id.action_search:
                ARouter.getInstance()
                       .build(ArouteHelper.ROUTE_ACTIVITY_TAB_ACTIVITY)
                       .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                       .navigation();
                break;
            case R.id.action_notification:
                ARouter.getInstance()
                       .build(ArouteHelper.ROUTE_ACTIVITY_LIST_ACTIVITY)
                       .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                       .navigation();
                break;

            default:
                break;
        }
    }

    @Override
    public void initData(Context mContext) {

    }

    @Override
    protected boolean getDisplayHomeAsUpEnabled() {
        return false;
    }

    @Override
    protected void OnNavigationOnClick() {
        drawerRootLayout.openDrawer(GravityCompat.START);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ACache mCache = ACache.get(this);
        mCache.remove("user");
    }

    private void initTopBar() {
        setNavigationIcon(R.mipmap.toolbar_menu);
        setSteepStatusBar(true);
    }

    /**
     * inflateHeaderView 进来的布局要宽一些
     */
    private void initDrawerLayout() {
        View view = LayoutInflater.from(this)
                                  .inflate(R.layout.layout_main_drawer_view, navView);
        //夜间模式
//        bind.dayNightSwitch.setChecked(SPUtils.getNightMode());
        //TODO 设置头像和等级
//        ImageLoadUtil.displayCircle(bind.ivAvatar, ConstantsImageUrl.IC_AVATAR);
        TextView homePageTv, downLoadTv, feedBackTv, aboutTv, loginTv, collectionTv, exitTv;
        homePageTv = view.findViewById(R.id.project_home_page_tv);
        downLoadTv = view.findViewById(R.id.down_load_tv);
        feedBackTv = view.findViewById(R.id.feed_back_tv);
        aboutTv = view.findViewById(R.id.about_tv);
        loginTv = view.findViewById(R.id.login_tv);
        collectionTv = view.findViewById(R.id.collection_tv);

        exitTv = view.findViewById(R.id.exit_tv);
        //项目主页
        homePageTv.setOnClickListener(v -> ARouter.getInstance()
                                                  .build(ArouteHelper.ROUTE_ACTIVITY_PROJECT_HOME)
                                                  .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                                  .withInt("x", AnimatorHelper.getDownX())
                                                  .withInt("y", AnimatorHelper.getDownY())
                                                  .navigation(this));
        //扫码下载
        downLoadTv.setOnClickListener(v -> {
            showToast("扫码下载");
        });
        //问题反馈
        feedBackTv.setOnClickListener(v -> {
            showToast("问题反馈");
        });
        //关于
        aboutTv.setOnClickListener(v -> {
            showToast("关于");
        });
        //登录
        loginTv.setOnClickListener(v -> ARouter.getInstance()
                                               .build(ArouteHelper.ROUTE_ACTIVITY_LOGIN_PLAY_ANDROID)
                                               .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                               .withInt("x", AnimatorHelper.getDownX())
                                               .withInt("y", AnimatorHelper.getDownY())
                                               .navigation(this));
        //我的收藏
        collectionTv.setOnClickListener(v -> {
            showToast("我的收藏");
        });
        //退出应用
        exitTv.setOnClickListener(v -> {
            finishActivity();
        });

        setMenuEvent(homePageTv);
        setMenuEvent(downLoadTv);
        setMenuEvent(feedBackTv);
        setMenuEvent(aboutTv);
        setMenuEvent(loginTv);
        setMenuEvent(collectionTv);
        setMenuEvent(exitTv);

    }

    /**
     * 设置按钮的事件
     *
     * @param view
     */
    private void setMenuEvent(TextView view) {
        //监听View点击时的触摸坐标（相对于屏幕）
        AnimatorHelper.setViewTouchListener(view);
        setDrawerLayoutIconSize(view);
    }

    /**
     * 设置侧边菜单栏的菜单图标大小
     *
     * @param textView
     */
    private void setDrawerLayoutIconSize(TextView textView) {
        Drawable drawable = textView.getCompoundDrawablesRelative()[0];
        //第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        int drawableSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
        drawable.setBounds(0, 0, drawableSize, drawableSize);
        //只放左边
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        fragments = new ArrayList<>();
        fragments.add(new BasicKnowledgeFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new MovieFragment());
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(VIEW_PAGER_OFFSCREEN_PAGE_LIMIT);

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mBottomBar.selectTabAtPosition(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 初始化BottomBar
     */
    private void initBottomBar() {
        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        //以小红点形式显示新消息数量
//        mBottomBar.getTabWithId(R.id.tab_discover).setBadgeCount(5);

        mBottomBar.setOnTabSelectListener(tabId -> {
            switch (tabId) {
                case R.id.tab_basic_knowledge:
                    viewPager.setCurrentItem(TAB_TYPE_DISCOVER);
                    break;
                case R.id.tab_recommended:
                    viewPager.setCurrentItem(TAB_TYPE_FRIENDS);
                    break;
                case R.id.tab_film:
                    viewPager.setCurrentItem(TAB_TYPE_MUSIC);
                    break;
                default:
                    break;
            }
        });

        mBottomBar.setOnTabReselectListener(tabId -> {
            if (tabId == R.id.tab_recommended) {
                // 已经选择了这个标签，又点击一次。即重选。
                mBottomBar.getTabWithId(R.id.tab_recommended)
                          .removeBadge();
            }
        });

        mBottomBar.setTabSelectionInterceptor((oldTabId, newTabId) -> {
            // 点击无效
            if (newTabId == R.id.tab_film) {
                // ......
                // 返回 true 。代表：这里我处理了，你不用管了。
                return false;
            }

            return false;
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存BottomBar的状态
        mBottomBar.onSaveInstanceState();
    }

    @Override
    public void onBackPressed() {
        boolean isChildViewHandled = false;
        //通知所有子view返回时间
        int fragmentSize = fragments.size();
        for (int i = 0; i < fragmentSize; i++) {
            //可见的fragment就通知
            if (fragments.get(i)
                         .isVisibleToUser()) {
                isChildViewHandled = fragments.get(i)
                                              .onBackPressed();
                break;
            }
        }
        if (!isChildViewHandled) {
            if (System.currentTimeMillis() - firstBackPressTime > BACK_PRESS_INTERVAL_TIME) {
                ToastUtils.showShort("再按一次退出程序");
                firstBackPressTime = System.currentTimeMillis();
            }
            else {
                ACache mCache = ACache.get(this);
                mCache.remove("user");
                UserUtil.handleLoginFailure();
                super.onBackPressed();
            }
        }
    }

    @Override
    protected void onActivityAnimationFinish() {
        ActivityManager.getAppManager()
                       .finishAllExcept("MainActivity");
    }
}
