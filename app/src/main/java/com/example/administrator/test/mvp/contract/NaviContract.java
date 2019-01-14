package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.entity.NaviEntity;
import com.example.administrator.test.http.HttpCallback;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.contract
 * @ClassName: NaviContract
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/14 5:43 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/14 5:43 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface NaviContract {
    interface Model {
        void getNaviData(HttpCallback callback);
    }

    interface View {
        void getDataSuccess(NaviEntity entity);

        void getDataFail(String msg);
    }

    interface Presenter {
        void getNaviData();
    }
}
