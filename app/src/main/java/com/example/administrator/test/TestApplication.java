package com.example.administrator.test;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.example.administrator.test.http.Api;
import com.example.administrator.test.http.HttpUtil;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * @author koo
 */
public class TestApplication extends Application {
    public static TestApplication instance;

    public static TestApplication getApplication() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 打印日志
        ARouter.openLog();
        // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.openDebug();
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);

        Utils.init(this);
        initLogger();
    }

    private void initLogger() {
//        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
//                .showThreadInfo(false)  // 是否显示线程信息，默认为ture
//                .methodCount(0)         // 显示的方法行数，默认为2
//                .methodOffset(7)        // 隐藏内部方法调用到偏移量，默认为5
//                .logStrategy(customLog) // 更改要打印的日志策略。
//                .tag("My custom tag")   // 每个日志的全局标记。默认PRETTY_LOGGER
//                .build();
//        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
