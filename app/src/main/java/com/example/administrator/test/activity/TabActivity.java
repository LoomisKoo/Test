package com.example.administrator.test.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseTabActivity;
import com.example.administrator.test.base.baseinterface.ITabPagerView;
import com.example.administrator.test.entity.TestEntity;
import com.example.administrator.test.listview.TestListView;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.util.ArouteHelper;

import java.util.ArrayList;

/**
 * @author koo
 */
@Route(path = ArouteHelper.ROUTE_ACTIVITY_TAB_ACTIVITY)
public class TabActivity extends BaseTabActivity {
    @Override
    public int bindContentLayout() {
        return R.layout.layout_base_tab;
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
    protected ArrayList<String> getTabTitles() {
        ArrayList<String> title = new ArrayList<>();
        title.add("title1");
        title.add("title2");
        title.add("title3");
        return title;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initToolbar();
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected ArrayList<ITabPagerView> getViewList() {
        ArrayList<ITabPagerView> viewList = new ArrayList<>();

        ArrayList<TestEntity> testEntities = new ArrayList<>();
        testEntities.add(new TestEntity("koo"));
        viewList.add(new TestListView(this, 0));


        testEntities = new ArrayList<>();
        testEntities.add(new TestEntity("koo1"));
        testEntities.add(new TestEntity("koo1"));
        viewList.add(new TestListView(this, 1));

        testEntities = new ArrayList<>();
        testEntities.add(new TestEntity("koo2"));
        testEntities.add(new TestEntity("koo2"));
        testEntities.add(new TestEntity("koo2"));
        viewList.add(new TestListView(this, 2));

        return viewList;
    }

    /**
     * 初始化toolbar
     */
    private void initToolbar() {
        setSubtitle("subTitle");
        setTooBarTitle("title");
        setCenterTitle("中心大标题");
        setBarNaviIcon(getResources().getDrawable(R.mipmap.ic_back_black));
        showCenterTitle(true);
    }
}
