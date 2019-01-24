package com.example.administrator.test.util;

import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.app.Constants;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.util
 * @ClassName: UserUtil
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/12 9:36 AM
 * @UpdateUser:
 * @UpdateDate: 2019/1/12 9:36 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class UserUtil {

    /**
     * 初始化登录状态
     */
//    public static void getLoginStatus() {
//        Injection.get().getSingleBean(new UserDataCallback() {
//            @Override
//            public void onDataNotAvailable() {
//                SPUtils.getPlayAndroidInstance().put(Constants.IS_LOGIN, false);
//            }
//
//            @Override
//            public void getData(User bean) {
//                SPUtils.getPlayAndroidInstance().put(Constants.IS_LOGIN, true);
//            }
//        });
//    }
    public static void handleLoginSuccess() {
        SPUtils.getInstance()
               .put(Constants.IS_LOGIN, true);
    }

    public static void handleLoginFailure() {
        SPUtils.getInstance()
               .put(Constants.IS_LOGIN, false);
        SPUtils.getInstance()
               .remove("cookie");
    }

    /**
     * 是否登录，没有进入登录页面
     */
    public static boolean isLogin(Context context) {
        boolean isLogin = SPUtils.getInstance()
                                 .getBoolean(Constants.IS_LOGIN, false);
        if (!isLogin) {
            ToastUtils.showShort("请先登录~");
//            ARouter.getPlayAndroidInstance().build(ArouteHelper.ROUTE_ACTIVITY_LOGIN).withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION).withInt("x", AnimatorHelper.getDownX()).withInt("y", AnimatorHelper.getDownY()).navigation();
            return false;
        }
        else {
            return true;
        }
    }
}
