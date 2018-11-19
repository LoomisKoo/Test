package com.example.administrator.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.BaseViewHolder;
import com.example.administrator.test.base.ListActivity;
import com.example.administrator.test.base.QuickDelegateAdapter;
import com.example.administrator.test.entity.testEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * @author
 */

@Route(path = "/com/SecondActivity")
public class SecondActivity extends ListActivity {

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
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
    public void setListener() {

    }

    @Override
    public void onMenuClickListener(int menuId) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    protected void getData(int page, int pageSize) {
        List<testEntity> testEntityList = new ArrayList<>();
        testEntityList.add(new testEntity("test1"));
        testEntityList.add(new testEntity("test2"));
        testEntityList.add(new testEntity("test3"));
        adapter.replaceAll(testEntityList);
        stopRefresh();
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<testEntity>(this, R.layout.item_test) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, testEntity item, int viewType, int position) {
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
