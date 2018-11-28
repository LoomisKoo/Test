package com.example.administrator.test.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.TestEntity;

public class Test2ViewHolder extends BaseViewHolder {
    public Test2ViewHolder(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
    }

    public Test2ViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    public void setData(Object data) {
        setText(R.id.textView, ((TestEntity) data).getName());
    }
}
