package com.example.administrator.test.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
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
    protected void getData(int page, int pageSize) {
        stopRefresh();
        ArrayList<TestEntity> testEntities = new ArrayList<>();
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        testEntities.add(new TestEntity("test1"));
        adapter.replaceAll(testEntities);
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<TestEntity>(this, R.layout.item_test) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, TestEntity item, int viewType, int position) {
                holder.setText(R.id.textView, item.getName());
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new LinearLayoutHelper();
            }
        };
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
