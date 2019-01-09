package com.example.administrator.test.animation;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.animation
 * @ClassName: AnimatorHelper
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/9 1:57 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/9 1:57 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AnimatorHelper {

    /**
     * 点击时的屏幕坐标
     */
    private static int                  downX = 0;
    private static int                  downY = 0;
    private static View.OnTouchListener touchListener;

    /**
     * 设置View的Touch事件   记录触摸坐标，用于转场动画中心坐标
     *
     * @param v
     */
    @SuppressLint("ClickableViewAccessibility")
    public static void setViewTouchListener(View v) {
        if (null == touchListener) {
            touchListener = (view, event) -> {
                //储存点击时的坐标
                if (event.getActionMasked() != MotionEvent.ACTION_DOWN) {
                    return false;
                }
                downX = (int) event.getRawX();
                downY = (int) event.getRawY();
                // let the touch event pass on to whoever needs it
                return false;
            };
        }
        v.setOnTouchListener(touchListener);
    }


    public static int getDownX() {
        return downX;
    }

    public static void setDownX(int downX) {
        AnimatorHelper.downX = downX;
    }

    public static int getDownY() {
        return downY;
    }

    public static void setDownY(int downY) {
        AnimatorHelper.downY = downY;
    }

    /**
     * 重置XY
     */
    public static void resetXY() {
        downX = 0;
        downY = 0;
    }
}
