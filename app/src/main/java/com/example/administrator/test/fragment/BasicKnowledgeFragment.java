package com.example.administrator.test.fragment;

import android.view.View;

import com.example.administrator.test.R;
import com.example.administrator.test.base.fragment.BaseFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @author koo
 */
public class BasicKnowledgeFragment extends BaseFragment {
    /**
     * viewpager数量
     */
    private static final int VIEW_PAGER_COUNT = 3;

    /**
     * viewpager下标
     */
    private static final int VIEW_PAGER_PAGE_1 = 0;
    private static final int VIEW_PAGER_PAGE_2 = 1;
    private static final int VIEW_PAGER_PAGE_3 = 2;
    @BindView(R.id.bottomBar)
    BottomBar    bottomBar;
    @BindView(R.id.view_pager)
    ViewPager    viewPager;

    private ArrayList<Fragment> fragments = new ArrayList<>(VIEW_PAGER_COUNT);

    @Override
    protected void initView(View view) {
        initFragmentList();
        initViewPager(view);
        initBottomBar(view);
        //禁止越界拖动
        basePagerListRefreshLayout.setEnableOverScrollDrag(false);
        //禁止下拉刷新
        basePagerListRefreshLayout.setEnableRefresh(false);
        //禁止上拉加载
        basePagerListRefreshLayout.setEnableLoadmore(false);
    }

    @Override
    public int bindContentLayout() {
        return R.layout.play_android_fragment_root;
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
    protected Object getPresenter() {
        return null;
    }

    /**
     * 初始化fragment列表
     */
    private void initFragmentList() {
        fragments.clear();

        fragments.add(new BasicKnowledgePlayAndroidFragment());
        fragments.add(new BasicKnowledgeTreeFragment());
        fragments.add(new BasicKnowledgeNaviFragment());
    }

    private void initViewPager(View view) {
        viewPager.setOffscreenPageLimit(VIEW_PAGER_COUNT);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
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
                if (bottomBar.getTabCount() - 1 >= position) {
                    bottomBar.selectTabAtPosition(position, true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    /**
     * 初始化BottomBar
     */
    private void initBottomBar(View view) {
        //已小红点形式显示新消息数量
//        mBottomBar.getTabWithId(R.id.tab_discover).setBadgeCount(5);

        bottomBar.setOnTabSelectListener(tabId -> {
            switch (tabId) {
                case R.id.play_android:
                    viewPager.setCurrentItem(VIEW_PAGER_PAGE_1);
                    break;
                case R.id.tree:
                    viewPager.setCurrentItem(VIEW_PAGER_PAGE_2);
                    break;
                case R.id.navigation_data:
                    viewPager.setCurrentItem(VIEW_PAGER_PAGE_3);
                    break;
                default:
                    break;
            }
        });

        bottomBar.setOnTabReselectListener(tabId -> {
            if (tabId == R.id.play_android) {
                // 已经选择了这个标签，又点击一次。即重选。
                bottomBar.getTabWithId(R.id.play_android)
                          .removeBadge();
            }
        });
        bottomBar.setTabSelectionInterceptor((oldTabId, newTabId) -> {
            // 点击无效
            if (newTabId == R.id.tree) {
                // ......
                // 返回 true 。代表：这里我处理了，你不用管了。
                return false;
            }

            return false;
        });
    }

}
