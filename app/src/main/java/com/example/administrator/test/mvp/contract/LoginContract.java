package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.base.IBasePresenter;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.contract
 * @ClassName: LoginContract
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/9 4:37 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/9 4:37 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface LoginContract {
    interface Model {
        void login(String userName, String passWord, HttpCallback httpCallback);

        void logout(HttpCallback httpCallback);

        void register(String userName, String password, String rePassword, HttpCallback httpCallback);
    }

    interface View {
        void loginSuccess();

        void loginFail(String msg);

        void logoutSuccess();

        void logoutFail(String msg);

        void registerSuccess();

        void registerFail(String msg);
    }

    interface Presenter extends IBasePresenter {
        void login(String userName, String passWord);

        void logout();

        void register(String userName, String password, String rePassword);
    }
}
