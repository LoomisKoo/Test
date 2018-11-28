package com.example.administrator.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.activity.BaseListActivity;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.entity.TestEntity;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.util.ArouterHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * @author
 */

@Route(path = ArouterHelper.ROUTE_ACTIVITY_SECOND_ACTIVITY)
public class SecondActivityBase extends BaseListActivity {

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
        super.initView(savedInstanceState);
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

    }

    @Override
    public void initData(Context mContext) {

    }

    @Override
    protected int getBottomLayId() {
        return super.getBottomLayId();
    }

    @Override
    protected void getData(int page, int pageSize) {
        List<TestEntity> testEntityList = new ArrayList<>();
        testEntityList.add(new TestEntity("test1"));
        testEntityList.add(new TestEntity("test2"));
        testEntityList.add(new TestEntity("test3"));
        adapter.replaceAll(testEntityList);
        stopRefresh();
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<TestEntity>(this, R.layout.item_test) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, TestEntity item, int viewType, int position) {
                holder.setText(R.id.textView, item.getName());
                System.out.println();
            }


            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new LinearLayoutHelper();
            }
        };
    }

}
