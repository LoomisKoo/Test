package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.entity.KnowledgeSystemEntity;
import com.example.administrator.test.http.HttpCallback;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.contract
 * @ClassName: KnowledgeSystemContract
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/13 4:35 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/13 4:35 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface KnowledgeSystemContract {
    interface Model {
        void loadData(HttpCallback callback);
    }

    interface View {
        void loadDataSuccess(KnowledgeSystemEntity entity);

        void loadDataFail(String msg);
    }

    interface Presenter {
        void loadData();
    }
}
