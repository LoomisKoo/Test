package com.example.administrator.test.listview;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.BaseListView;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.baseinterface.ITabPagerView;
import com.example.administrator.test.entity.TestEntity;

import java.util.ArrayList;

/**
 * @author koo
 */
public class TestListView extends BaseListView<TestEntity> implements ITabPagerView {
    ArrayList<TestEntity> testEntityArrayList;
    private int listType;

    public TestListView(Context context, int listType) {
        super(context);
        this.listType = listType;
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<TestEntity>(getContext(), R.layout.item_test) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, TestEntity item, int viewType, int position) {
                holder.setText(R.id.textView, item.getName());
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new LinearLayoutHelper();
            }

            @Override
            protected RecyclerView.ViewHolder onGetViewHolder(ViewGroup parent, int viewType) {
                return super.onGetViewHolder(parent, viewType);
            }
        };
    }

    @Override
    protected void getData(int page, int pageSize) {
        stopRefresh();
        switch (listType) {
            case 0:
                testEntityArrayList.add(new TestEntity("k1"));
                break;
            case 1:
                testEntityArrayList.add(new TestEntity("k2"));
                testEntityArrayList.add(new TestEntity("k2"));
                break;
            case 2:
                testEntityArrayList.add(new TestEntity("k3"));
                testEntityArrayList.add(new TestEntity("k3"));
                testEntityArrayList.add(new TestEntity("k3"));
                break;
            default:
                break;
        }
        adapter.replaceAll(testEntityArrayList);
    }

    @Override
    public View getContentView() {
        return this;
    }

    @Override
    public void onCreateView(Context context) {
        setRefreshEnable(true);
        setLoadMoreEnable(true);
        testEntityArrayList = new ArrayList<>();
    }
}
