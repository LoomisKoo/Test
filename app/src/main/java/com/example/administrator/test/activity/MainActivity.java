package com.example.administrator.test.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;


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
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    /**
     * 记录用户首次点击返回键的时间
     */
    private              long firstBackPressTime       = 0;
    /**
     * 防误触返回间隔时间
     */
    private static final int  BACK_PRESS_INTERVAL_TIME = 2000;

    private List<BaseFragment> fragments;

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
//                       .build(ArouteHelper.ROUTE_ACTIVITY_LIST_ACTIVITY)
                       .build(ArouteHelper.ROUTE_ACTIVITY_LOTTIE)
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
        baseRootDl.openDrawer(GravityCompat.START);
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
                                  .inflate(R.layout.layout_main_drawer_view, baseRootNavView);
        ViewHolder viewHolder = new ViewHolder(view);
        //夜间模式
//        bind.dayNightSwitch.setChecked(SPUtils.getNightMode());
        //项目主页
        viewHolder.projectHomePageTv.setOnClickListener(v -> ARouter.getInstance()
                                                                    .build(ArouteHelper.ROUTE_ACTIVITY_PROJECT_HOME)
                                                                    .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                                                    .withInt("x", AnimatorHelper.getDownX())
                                                                    .withInt("y", AnimatorHelper.getDownY())
                                                                    .navigation(this));
        //扫码下载
        viewHolder.downLoadTv.setOnClickListener(v -> {
            showToast("扫码下载");
        });
        //问题反馈
        viewHolder.feedBackTv.setOnClickListener(v -> {
            showToast("问题反馈");
        });
        //关于
        viewHolder.aboutTv.setOnClickListener(v -> {
            showToast("关于");
        });
        //登录
        viewHolder.loginTv.setOnClickListener(v -> ARouter.getInstance()
                                                          .build(ArouteHelper.ROUTE_ACTIVITY_LOGIN_PLAY_ANDROID)
                                                          .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                                          .withInt("x", AnimatorHelper.getDownX())
                                                          .withInt("y", AnimatorHelper.getDownY())
                                                          .navigation(this));
        //我的收藏
        viewHolder.collectionTv.setOnClickListener(v -> ARouter.getInstance()
                                                               .build(ArouteHelper.ROUTE_ACTIVITY_COLLECTION_ARTICLE_LIST)
                                                               .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                                               .withInt("x", AnimatorHelper.getDownX())
                                                               .withInt("y", AnimatorHelper.getDownY())
                                                               .navigation(this));
        //退出应用
        viewHolder.exitTv.setOnClickListener(v -> finishActivity());

        setMenuEvent(viewHolder.projectHomePageTv);
        setMenuEvent(viewHolder.downLoadTv);
        setMenuEvent(viewHolder.feedBackTv);
        setMenuEvent(viewHolder.aboutTv);
        setMenuEvent(viewHolder.loginTv);
        setMenuEvent(viewHolder.collectionTv);
        setMenuEvent(viewHolder.exitTv);
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
                bottomBar.selectTabAtPosition(position, true);
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
        //以小红点形式显示新消息数量
//        mBottomBar.getTabWithId(R.id.tab_discover).setBadgeCount(5);

        bottomBar.setOnTabSelectListener(tabId -> {
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

        bottomBar.setOnTabReselectListener(tabId -> {
            if (tabId == R.id.tab_recommended) {
                // 已经选择了这个标签，又点击一次。即重选。
                bottomBar.getTabWithId(R.id.tab_recommended)
                         .removeBadge();
            }
        });

        bottomBar.setTabSelectionInterceptor((oldTabId, newTabId) -> {
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
        bottomBar.onSaveInstanceState();
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
                ToastUtils.showShort(getString(R.string.home_exit_program_prompt));
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
                       .finishAllExcept(getClass().getSimpleName());
    }

    class ViewHolder {
        @BindView(R.id.top_view)
        ImageView        topView;
        @BindView(R.id.iv_avatar)
        ImageView        imageView3;
        @BindView(R.id.nick_name_tv)
        TextView         nickNameTv;
        @BindView(R.id.constraintLayout)
        ConstraintLayout constraintLayout;
        @BindView(R.id.project_home_page_tv)
        TextView         projectHomePageTv;
        @BindView(R.id.down_load_tv)
        TextView         downLoadTv;
        @BindView(R.id.feed_back_tv)
        TextView         feedBackTv;
        @BindView(R.id.about_tv)
        TextView         aboutTv;
        @BindView(R.id.login_tv)
        TextView         loginTv;
        @BindView(R.id.collection_tv)
        TextView         collectionTv;
        @BindView(R.id.exit_tv)
        TextView         exitTv;
        @BindView(R.id.linearLayout)
        LinearLayout     linearLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
