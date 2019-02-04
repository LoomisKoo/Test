package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.entity.MoviewHitEntity;
import com.example.administrator.test.http.HttpCallback;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.contract
 * @ClassName: MovieHitContract
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/3 11:22 AM
 * @UpdateUser:
 * @UpdateDate: 2019/2/3 11:22 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface MovieHitContract {
    interface Model {
        void loadData(HttpCallback callback);
    }

    interface View {
        void onLoadSuccess(MoviewHitEntity entity);

        void onLoadFailed(String msg);
    }

    interface Presenter {
        void loadData();
    }
}
