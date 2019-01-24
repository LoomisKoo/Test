package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.test.R;
import com.example.administrator.test.base.fragment.BaseFragment;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.fragment
 * @ClassName: RecommendFragment
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/22 3:38 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/22 3:38 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendFragment extends BaseFragment {
    /**
     * viewpager数量
     */
    private static final int VIEW_PAGER_COUNT = 4;

    /**
     * viewpager下标
     */
    private static final int VIEW_PAGER_PAGE_1 = 0;
    private static final int VIEW_PAGER_PAGE_2 = 1;
    private static final int VIEW_PAGER_PAGE_3 = 2;
    private static final int VIEW_PAGER_PAGE_4 = 3;

    private ArrayList<Fragment> fragments = new ArrayList<>(VIEW_PAGER_COUNT);
    private ViewPager           viewPager;
    private BottomBar           mBottomBar;


    @Override
    protected int setContentLayout() {
        return R.layout.recommend_fragment_root;
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
    protected void initData() {
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
        fragments.add(new DailyRecommendationFragment());
        fragments.add(new DailyRecommendationFragment());
        fragments.add(new DailyRecommendationFragment());
        fragments.add(new DailyRecommendationFragment());
    }

    private void initViewPager(View view) {
        viewPager = view.findViewById(R.id.view_pager);
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
                System.out.println("position：" + position);
                if (mBottomBar.getTabCount() - 1 >= position) {
                    mBottomBar.selectTabAtPosition(position, true);
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
        mBottomBar = view.findViewById(R.id.bottomBar);
        //已小红点形式显示新消息数量
//        mBottomBar.getTabWithId(R.id.tab_discover).setBadgeCount(5);

        mBottomBar.setOnTabSelectListener(tabId -> {
            switch (tabId) {
                case R.id.daily_recommendation:
                    viewPager.setCurrentItem(VIEW_PAGER_PAGE_1);
                    break;
                case R.id.welfare:
                    viewPager.setCurrentItem(VIEW_PAGER_PAGE_2);
                    break;
                case R.id.custom:
                    viewPager.setCurrentItem(VIEW_PAGER_PAGE_3);
                    break;
                case R.id.android:
                    viewPager.setCurrentItem(VIEW_PAGER_PAGE_4);
                    break;
                default:
                    break;
            }
        });

        mBottomBar.setOnTabReselectListener(tabId -> {
            if (tabId == R.id.play_android) {
                // 已经选择了这个标签，又点击一次。即重选。
                mBottomBar.getTabWithId(R.id.play_android)
                          .removeBadge();
            }
        });
        mBottomBar.setTabSelectionInterceptor((oldTabId, newTabId) -> {
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