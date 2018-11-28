package com.example.administrator.test.util;

import android.view.View;

/**
 * 防止多次点击
 */
public abstract class OnMultiClickListener implements View.OnClickListener {
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int  MIN_CLICK_DELAY_TIME = 1000;
    private static       long lastClickTime;
    private              int  delayTime;

    public OnMultiClickListener(int delayTime) {
        this.delayTime = delayTime;
    }

    public OnMultiClickListener() {
        delayTime = MIN_CLICK_DELAY_TIME;
    }

    /**
     * 防止多次点击
     * @param v
     */
    public abstract void onMultiClick(View v);

    @Override
    public void onClick(View v) {
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= delayTime) {
            // 超过点击间隔后再将lastClickTime重置为当前点击时间
            lastClickTime = curClickTime;
            onMultiClick(v);
        }
    }
}