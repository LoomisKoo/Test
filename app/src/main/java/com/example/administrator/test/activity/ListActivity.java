package com.example.administrator.test.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.viewHolder.Test2ViewHolder;
import com.example.administrator.test.viewHolder.TestViewHolder;
import com.example.administrator.test.base.activity.BaseListActivity;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.entity.TestEntity;
import com.example.administrator.test.mvp.base.IBasePresenter;

import java.util.ArrayList;

/**
 * @author koo
 */

@Route(path = "/com/ListActivity")
public class ListActivity extends BaseListActivity<TestEntity> {
    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
    }

    @Override
    protected void getData(int page, int pageSize) {
        stopRefresh();
        ArrayList<TestEntity> testEntities = new ArrayList<>();
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1", 1));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1", 1));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1", 1));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1", 1));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1", 1));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1", 1));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1", 1));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1", 1));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1", 1));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1", 1));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1", 1));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1", 1));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1", 1));
        testEntities.add(new TestEntity("test1"));
        adapter.replaceAll(testEntities);
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<TestEntity>(this, 0) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, TestEntity item, int viewType, int position) {
                holder.setData(item);
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                switch (viewType) {
                    case 0:
                        return new TestViewHolder(ListActivity.this, parent, R.layout.item_test);
                    case 1:
                        return new Test2ViewHolder(ListActivity.this, parent, R.layout.item_test2);
                    default:
                        break;
                }
                return new TestViewHolder(ListActivity.this, parent, R.layout.item_test);
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new LinearLayoutHelper();
            }

            @Override
            public int getItemViewType(int position) {
                return getItem(position).getType();
            }
        };
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
    protected boolean isToolBarAnimation() {
        return true;
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    public void OnNavigationOnClick() {
        finish();
    }
}
