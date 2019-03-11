package com.example.administrator.test.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.entity.MovieTop250Entity;
import com.example.administrator.test.entity.MoviewHitEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.MovieTop250Contract;
import com.example.administrator.test.mvp.contract.MovieUpcomingContract;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.presenter
 * @ClassName: MovieUpcomingPresenter
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/4 1:20 PM
 * @UpdateUser:
 * @UpdateDate: 2019/2/4 1:20 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MovieTop250Presenter implements MovieTop250Contract.Presenter {
    private MovieTop250Contract.View  view;
    private MovieTop250Contract.Model model;


    public MovieTop250Presenter(MovieTop250Contract.View view, MovieTop250Contract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void loadData(int start, int count) {
        model.loadData(start, count, new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                MovieTop250Entity entity = null;
                try {
                    entity = JSON.parseObject(result.string(), MovieTop250Entity.class);
                    view.onLoadSuccess(entity);
                }
                catch (IOException e) {
                    view.onLoadFailed("加载数据失败");
                    e.printStackTrace();
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
