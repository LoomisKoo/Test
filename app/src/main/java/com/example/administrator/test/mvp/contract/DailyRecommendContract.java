package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.entity.DailyRecommendEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.base.IBasePresenter;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.contract
 * @ClassName: DailyRecommendContract
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/23 9:14 AM
 * @UpdateUser:
 * @UpdateDate: 2019/1/23 9:14 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface DailyRecommendContract {
    interface Model {
        /**
         * 获取banner图片数据
         *
         * @param httpCallback 回调
         */
        void getBannerImg(HttpCallback httpCallback);

        void getDailyRecommend(HttpCallback httpCallback);
    }

    interface View {
        /**
         * 数据请求成功回调
         *
         * @param entity
         */
        void onSuccess(DailyRecommendEntity entity);

        void onError(String msg);
    }

    interface Presenter extends IBasePresenter {
        void getDailyRecommend();

        void getBannerImg();
    }
}
