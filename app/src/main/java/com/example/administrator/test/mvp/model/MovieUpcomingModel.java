package com.example.administrator.test.mvp.model;

import com.example.administrator.test.http.Api;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.MovieUpcomingContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.model
 * @ClassName: MovieUpcomingModel
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/4 1:20 PM
 * @UpdateUser:
 * @UpdateDate: 2019/2/4 1:20 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MovieUpcomingModel implements MovieUpcomingContract.Model {
    @Override
    public void loadData(int start,int count,HttpCallback callback) {
        Observable<ResponseBody> observable = Api.getDoubanService()
                                                 .getUpcomming(start,count);
        Api.query(observable, callback);
    }
}
