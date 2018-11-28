package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.administrator.test.R;
import com.example.administrator.test.base.fragment.BaseFragment;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;

/**
 * @author koo
 */
public class PlayFragment extends BaseFragment {
    private ArrayList<String>   mTitleList = new ArrayList<>(3);
    private ArrayList<Fragment> fragments  = new ArrayList<>(3);
    private ViewPager           viewPager;
    private BottomBar           mBottomBar;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_play;
    }

    @Override
    protected void initView(View view) {
        mBottomBar = view.findViewById(R.id.bottomBar);
        viewPager = view.findViewById(R.id.view_pager);
        initFragmentList();
        initViewPager(view);
        initBottomBar(view);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {


    }

    @Override
    protected Object getPresenter() {
        return null;
    }

    /**
     * 初始化fragment列表
     */
    private void initFragmentList() {
        mTitleList.clear();
        mTitleList.add("玩安卓");
        mTitleList.add("知识体系");
        mTitleList.add("导航数据");

        fragments.add(new PlayAndroidFragment());
        fragments.add(new Fragment1());
        fragments.add(new Fragment1());
    }

    private void initViewPager(View view) {
        viewPager = view.findViewById(R.id.view_pager);
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
    private void initBottomBar(View view) {
        mBottomBar = view.findViewById(R.id.bottomBar);
        //已小红点形式显示新消息数量
        mBottomBar.getTabWithId(R.id.tab_discover).setBadgeCount(5);

        mBottomBar.setOnTabSelectListener(tabId -> {

            switch (tabId) {
                case R.id.tab_discover:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.tab_friends:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.tab_music:
                    viewPager.setCurrentItem(2);
                    break;
                default:
                    break;
            }
        });

        mBottomBar.setOnTabReselectListener(tabId -> {
            if (tabId == R.id.tab_discover) {
                // 已经选择了这个标签，又点击一次。即重选。
                mBottomBar.getTabWithId(R.id.tab_discover).removeBadge();
            }
        });
        mBottomBar.setTabSelectionInterceptor((oldTabId, newTabId) -> {
            // 点击无效
            if (newTabId == R.id.tab_music) {
                // ......
                // 返回 true 。代表：这里我处理了，你不用管了。
                return false;
            }

            return false;
        });
    }

}
