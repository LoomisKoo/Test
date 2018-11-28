package com.example.administrator.test.util;

/**
 * 防止多次点击
 */
public class ClickUtil {
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {

        long time  = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
