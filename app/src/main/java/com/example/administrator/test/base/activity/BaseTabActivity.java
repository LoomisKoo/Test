package com.example.administrator.test.base.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.test.R;
import com.example.administrator.test.base.baseinterface.ITabPagerView;

import java.util.ArrayList;

/**
 * @author koo
 */
public abstract class BaseTabActivity extends BaseActivity {
    protected TabLayout                tabLayout;
    protected ArrayList<String>        tabTitles;
    protected ViewPager                viewPager;
    protected BasePagerAdapter         adapter;
    protected ArrayList<ITabPagerView> viewList;

    @Override
    public void widgetClick(View v) {

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
        return 0;
    }

    @Override
    public int bindMenu() {
        return 0;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        tabLayout = (TabLayout) findViewById(R.id.act_nav_tab_pager_tl_tab);
        tabTitles = getTabTitles();
        viewList = getViewList();
        if (tabTitles == null || viewList == null) {
            return;
        }

        for (int i = 0; i < tabTitles.size(); i++) {
            tabLayout.addTab(tabLayout.newTab()
                                      .setText(tabTitles.get(i)));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager = (ViewPager) findViewById(R.id.act_nav_tab_pager_vp);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position)
                         .select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(adapter = new BasePagerAdapter());
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onMenuClickListener(int menuId) {

    }

    @Override
    public void initData(Context mContext) {

    }

    class BasePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position)
                                      .getContentView());
            viewList.get(position)
                    .onCreateView(BaseTabActivity.this);
            return viewList.get(position)
                           .getContentView();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    /**
     * 获取tab的title
     *
     * @return
     */
    protected abstract ArrayList<String> getTabTitles();

    /**
     * 获取每个tab的列表
     *
     * @return
     */
    protected abstract ArrayList<ITabPagerView> getViewList();
}
