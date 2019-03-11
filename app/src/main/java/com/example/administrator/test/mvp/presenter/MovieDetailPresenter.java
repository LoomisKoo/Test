package com.example.administrator.test.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.entity.MovieDetailEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.MovieDetailContract;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.presenter
 * @ClassName: MovieDetailPresenter
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/4 2:38 PM
 * @UpdateUser:
 * @UpdateDate: 2019/2/4 2:38 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MovieDetailPresenter implements MovieDetailContract.Presenter {
    private MovieDetailContract.View  view;
    private MovieDetailContract.Model model;


    public MovieDetailPresenter(MovieDetailContract.View view, MovieDetailContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getMovieDetail(String movieID) {
        model.getMovieDetail(movieID, new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                try {
                    MovieDetailEntity entity = JSON.parseObject(result.string(), MovieDetailEntity.class);
                    view.onLoadSuccess(entity);

                }
                catch (IOException e) {
                    e.printStackTrace();
                    view.onLoadFailed("数据加载失败");
                }

            }

            @Override
            public void onError(String msg) {
                view.onLoadFailed(msg);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
