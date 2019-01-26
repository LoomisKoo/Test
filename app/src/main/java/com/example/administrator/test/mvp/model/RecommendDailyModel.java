package com.example.administrator.test.mvp.model;

import com.example.administrator.test.http.Api;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.RecommendDailyContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.model
 * @ClassName: RecommendDailyModel
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/23 9:14 AM
 * @UpdateUser:
 * @UpdateDate: 2019/1/23 9:14 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendDailyModel implements RecommendDailyContract.Model {
    @Override
    public void getBannerImg(HttpCallback httpCallback) {

    }

    @Override
    public void getDailyRecommend(HttpCallback httpCallback) {
        Observable<ResponseBody> observable = Api.getRecommendService()
                                                 .getTodayRecommend();
        Api.query(observable, httpCallback);
    }
}
