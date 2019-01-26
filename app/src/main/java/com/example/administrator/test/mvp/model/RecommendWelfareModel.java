package com.example.administrator.test.mvp.model;

import com.example.administrator.test.http.Api;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.RecommendWelfareContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.model
 * @ClassName: RecommendWelfareModel
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/26 2:05 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/26 2:05 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendWelfareModel implements RecommendWelfareContract.Model {
    @Override
    public void getWelfare(String id,int pre_page,int page,HttpCallback httpCallback) {
        Observable<ResponseBody> observable = Api.getRecommendService()
                                                 .getGankIoData(id,pre_page,page);
        Api.query(observable, httpCallback);
    }
}
