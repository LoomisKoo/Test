package com.example.administrator.test.mvp.contract;

import java.util.List;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.contract
 * @ClassName: SplashContract
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2018/11/28 11:31 AM
 * @UpdateUser:
 * @UpdateDate: 2018/11/28 11:31 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface SplashContract {
    interface Model {
        /**
         * 显示欢迎页
         */
        void showWelcome();

        /**
         * 显示广告（本地图片）
         */
        void getLocalADPath();
    }

    interface View {
        /**
         * 显示本地广告
         * @param localADPath
         */
        void showLocalAD(List<String> localADPath);

        void showWelcome();
    }

    interface Presenter {
        /**
         * 显示欢迎页
         */
        void showWelcome();

        /**
         * 显示广告（本地图片）
         */
        void showLocalAD();
    }
}
