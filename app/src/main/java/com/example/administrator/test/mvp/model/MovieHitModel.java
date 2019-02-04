package com.example.administrator.test.mvp.model;

import com.example.administrator.test.http.Api;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.MovieHitContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.model
 * @ClassName: MovieHitModel
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/3 11:22 AM
 * @UpdateUser:
 * @UpdateDate: 2019/2/3 11:22 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MovieHitModel implements MovieHitContract.Model {
    @Override
    public void loadData(HttpCallback callback) {
        Observable<ResponseBody> observable = Api.getDoubanService()
                                                 .getHitMovie();
        Api.query(observable, callback);
    }
}
