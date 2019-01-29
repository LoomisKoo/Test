package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.entity.RecommendCustomEntity;
import com.example.administrator.test.entity.view.RecommendCustomViewEntity;
import com.example.administrator.test.http.HttpCallback;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.contract
 * @ClassName: RecommendCustomContract
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/29 2:00 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/29 2:00 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface RecommendCustomContract {
    interface Model {
        void getCustomData(String id, int pre_page, int page, HttpCallback callback);
    }

    interface View {
        void onSuccess(RecommendCustomViewEntity entity);

        void onError(String msg);
    }

    interface Presenter {
        void getCustomData(String id, int pre_page, int page);
    }
}
