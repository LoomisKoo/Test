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
import com.example.administrator.test.fragment.PlayFragment;
import com.example.administrator.test.mvp.base.IBaseModel;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.mvp.contract.TestContract;
import com.example.administrator.test.mvp.presenter.GetBookPresenter;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;
import java.util.List;


/**
 * @author
 */
public class MainActivity extends BaseActivity implements TestContract.View {
    private BottomBar mBottomBar;
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
        initViewPager();
        initTopBar();
        initBottomBar();
        setEnableGesture(false);
    }

    @Override
    protected IBasePresenter createPresenter() {
//        model = new TestModel();
        GetBookPresenter getBookPresenter = new GetBookPresenter();
        return getBookPresenter;
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
                ARouter.getInstance().build("/com/ListActivity").navigation();
                showToast("Notification");
                break;

            default:
                break;
        }
    }

    @Override
    public void onEvent(Context mContext) {

    }

    @Override
    protected boolean getDisplayHomeAsUpEnabled() {
        return false;
    }

    private void initTopBar() {
        setTitle("大标题");
        setSubtitle("subtitle");
        setTitle("title");
        setSteepStatusBar(true);
    }

    /**
     * 初始化BottomBar
     */
    private void initBottomBar() {
        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
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

    private void initViewPager() {
        fragments = new ArrayList<>();
        fragments.add(new PlayFragment());
        fragments.add(new Fragment1());
        fragments.add(new Fragment1());
        viewPager = (ViewPager) findViewById(R.id.view_pager);
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

    @Override
    public void addResult(double result) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onErrorCode(IBaseModel model) {

    }

}
