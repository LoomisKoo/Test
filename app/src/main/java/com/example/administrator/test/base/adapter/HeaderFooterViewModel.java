package com.example.administrator.test.base.adapter;

import android.support.annotation.LayoutRes;

import com.example.administrator.test.base.adapter.BaseViewHolder;

public abstract class HeaderFooterViewModel {
    //数据对象
    public Object object;
    //布局资源id
    public @LayoutRes
    int layoutId;

    public HeaderFooterViewModel(int layoutId, Object object) {
        this.layoutId = layoutId;
        this.object = object;
    }

    public abstract void setData(BaseViewHolder viewHolder, Object object);
}
