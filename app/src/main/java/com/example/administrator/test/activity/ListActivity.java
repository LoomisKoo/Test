package com.example.administrator.test.activity;

import android.graphics.Color;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.util.ArouterHelper;
import com.example.administrator.test.viewholder.Test2ViewHolder;
import com.example.administrator.test.viewholder.TestViewHolder;
import com.example.administrator.test.base.activity.BaseListActivity;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.entity.TestEntity;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.koo.loomis.swiperecyclerview.SwipeMenuItem;

import java.util.ArrayList;

/**
 * @author koo
 */

@Route(path = ArouterHelper.ROUTE_ACTIVITY_LIST_ACTIVITY)
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

    @Override
    protected void initRecycleView() {
        initSwipeRV();
        recyclerView.setSwipeItemClickListener((itemView, position) -> recyclerView.smoothOpenRightMenu(position));
        super.initRecycleView();
    }

    private void initSwipeRV() {
        recyclerView.setSwipeMenuCreator((leftMenu, rightMenu, position) -> {
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(ListActivity.this).setBackground(R.drawable.gray_radius)
                                                                           .setText("删除")
                                                                           // 图标。
                                                                           .setImage(R.mipmap.ic_bottom_bar_discover)
                                                                           .setTextColor(Color.WHITE)
                                                                           .setWidth(400)
                                                                           .setHeight(height);
            rightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。
        });
    }
}
