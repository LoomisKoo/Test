package com.example.administrator.test.base.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * @param <T>
 * @author koo
 */
public abstract class QuickDelegateAdapter<T> extends BaseQuickDelegateAdapter<T, BaseViewHolder> {
    protected final int layoutResId;

    public QuickDelegateAdapter(Context context, int layoutResId) {
        super(context);
        this.layoutResId = layoutResId;
    }

    public QuickDelegateAdapter(Context context, int layoutResId, List<T> data) {
        super(context, data);
        this.layoutResId = layoutResId;
    }

    @Override
    protected RecyclerView.ViewHolder onGetViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.get(context, parent, layoutResId);
    }
}
