package com.example.administrator.test.mvp.model;

import com.example.administrator.test.http.Api;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.RecommendCustomContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.model
 * @ClassName: RecommendCustomModel
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/29 2:00 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/29 2:00 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendCustomModel implements RecommendCustomContract.Model {
    @Override
    public void getCustomData(String id, int pre_page, int page, HttpCallback callback) {
        Observable<ResponseBody> observable = Api.getRecommendService()
                                                 .getGankIoData(id,pre_page,page);
        Api.query(observable, callback);
    }
}
