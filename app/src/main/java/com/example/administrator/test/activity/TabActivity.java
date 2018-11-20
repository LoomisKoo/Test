package com.example.administrator.test.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseTabActivity;
import com.example.administrator.test.base.baseinterface.ITabPagerView;
import com.example.administrator.test.entity.TestEntity;
import com.example.administrator.test.listview.TestListView;

import java.util.ArrayList;

/**
 * @author koo
 */
@Route(path = "/com/TabActivity")
public class TabActivity extends BaseTabActivity {
    @Override
    public int bindLayout() {
        return R.layout.layout_base_tab_list;
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
}
