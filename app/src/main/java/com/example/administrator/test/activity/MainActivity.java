package com.example.administrator.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseActivity;
import com.example.administrator.test.fragment.Fragment1;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;

import java.util.ArrayList;
import java.util.List;


/**
 * @author
 */
public class MainActivity extends BaseActivity {
    private BottomBar mBottomBar;
    private BottomBarTab nearby;
    private List<Fragment> fragments;
    private ViewPager viewPager;

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
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public int bindMenu() {
        return R.menu.base_toolbar_menu;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        initViewPager();
        initTopBar();
        initBottomBar();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onMenuClickListener(int menuId) {
        switch (menuId) {
            case R.id.action_search:
                ARouter.getInstance().build("/com/TabActivity").navigation();
                break;
            case R.id.action_notification:
                showToast("Notification");
                break;

            default:
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    protected boolean getDisplayHomeAsUpEnabled() {
        return false;
    }

    private void initTopBar() {
        setTitle("大标题");
        setSubtitle("subtitle");
        setTitle("title");
    }

    /**
     * 初始化BottomBar
     */
    private void initBottomBar() {
        mBottomBar = findViewById(R.id.bottomBar);
        mBottomBar.setOnTabSelectListener(tabId -> {
            if (tabId == R.id.tab_favorites) {
                // 选择指定 id 的标签
                nearby = mBottomBar.getTabWithId(R.id.tab_nearby);
                nearby.setBadgeCount(5);
            }

            switch (tabId) {
                case R.id.tab_recents:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.tab_favorites:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.tab_nearby:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.tab_friends:
                    viewPager.setCurrentItem(3);
                    break;
                case R.id.tab_restaurants:
                    viewPager.setCurrentItem(4);
                    break;
                default:
                    break;
            }
        });

        mBottomBar.setOnTabReselectListener(tabId -> {
            if (tabId == R.id.tab_favorites) {
                // 已经选择了这个标签，又点击一次。即重选。
                nearby.removeBadge();
            }
        });
        mBottomBar.setTabSelectionInterceptor((oldTabId, newTabId) -> {
            // 点击无效
            if (newTabId == R.id.tab_restaurants) {
                // ......
                // 返回 true 。代表：这里我处理了，你不用管了。
                return false;
            }

            return false;
        });
    }


    private void initViewPager() {
        fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment1());
        fragments.add(new Fragment1());
        fragments.add(new Fragment1());
        fragments.add(new Fragment1());
        viewPager = findViewById(R.id.view_pager);
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
}
