package com.example.administrator.test.base.baseinterface;

import android.content.Context;
import android.view.View;

/**
 * @author koo
 */
public interface ITabPagerView {
    /**
     * 获取contentView
     * @return
     */
    View getContentView();

    /**
     * onCreateView回调
     * @param context
     */
    void onCreateView(Context context);
}
