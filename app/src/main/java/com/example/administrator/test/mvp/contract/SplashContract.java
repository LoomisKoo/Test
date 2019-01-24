package com.example.administrator.test.mvp.contract;

import androidx.annotation.DrawableRes;

import com.example.administrator.test.mvp.base.IBasePresenter;

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
         *
         * @return
         */
        int getLocalADResID();

        /**
         * 广告倒计时
         */
        void countDownAD();

        /**
         * 无用的时候释放资源等
         */
        void onDestroy();
    }

    interface View {
        /**
         * 显示本地广告
         *
         * @param imgRes
         */
        void showLocalAD(@DrawableRes int imgRes);

        /**
         * 广告倒计时
         *
         * @param countDown
         */
        void countDownAD(long countDown);

    }

    interface Presenter extends IBasePresenter {
        /**
         * 显示欢迎页
         */
        void showWelcome();

        /**
         * 广告倒计时
         */
        void countDownAD();

        /**
         * 无用的时候释放资源等
         */
        void onDestroy();
    }

    /**
     * 回调接口
     */
    interface CallBack {
        /**
         * 广告倒计时回调
         *
         * @param countDown
         */
        void countDown(long countDown);

        /**
         * 完成欢迎页显示
         */
        void finishShowWelcome();

    }
}
