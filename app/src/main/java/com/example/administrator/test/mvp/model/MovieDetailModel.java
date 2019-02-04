package com.example.administrator.test.mvp.model;

import com.example.administrator.test.http.Api;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.MovieDetailContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.model
 * @ClassName: MovieDetailModel
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/4 2:38 PM
 * @UpdateUser:
 * @UpdateDate: 2019/2/4 2:38 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MovieDetailModel implements MovieDetailContract.Model {
    @Override
    public void getMovieDetail(String movieID, HttpCallback callback) {
        Observable<ResponseBody> observable = Api.getDoubanService()
                                                 .getMovieDetail(movieID);
        Api.query(observable, callback);
    }
}
