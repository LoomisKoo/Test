package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.entity.RecommendWelfareEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.base.IBasePresenter;

import java.util.List;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.contract
 * @ClassName: RecommendWelfareContract
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/26 2:05 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/26 2:05 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface RecommendWelfareContract {
    interface Model {
        /**
         * 获取福利数据
         *
         * @param id
         * @param page
         * @param pre_page
         * @param httpCallback
         */
        void getWelfare(String id, int page, int pre_page, HttpCallback httpCallback);
    }

    interface View {
        /**
         * 数据请求成功回调
         *
         * @param entity
         */
        void onSuccess(List<RecommendWelfareEntity.WelfareBean> entity);

        void onError(String msg);
    }

    interface Presenter extends IBasePresenter {
        void getWelfare(String id, int page, int pre_page);
    }
}
