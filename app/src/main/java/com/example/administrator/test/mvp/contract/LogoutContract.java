package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.http.HttpCallback;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.contract
 * @ClassName: LogoutContract
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/11 5:55 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/11 5:55 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface LogoutContract {
    interface Model {
        void logout(HttpCallback httpCallback);
    }

    interface View {
        void logoutSuccess();

        void logoutFail(String msg);

    }

    interface Presenter {
        void logout();
    }
}
