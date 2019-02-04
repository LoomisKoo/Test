package com.example.administrator.test.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.entity.MoviewHitEntity;
import com.example.administrator.test.http.HttpCallback;
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
public class MovieUpcomingPresenter implements MovieUpcomingContract.Presenter {
    private MovieUpcomingContract.View  view;
    private MovieUpcomingContract.Model model;


    public MovieUpcomingPresenter(MovieUpcomingContract.View view, MovieUpcomingContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void loadData(int start, int count) {
        model.loadData(start, count, new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                MoviewHitEntity entity = null;
                try {
                    entity = JSON.parseObject(result.string(), MoviewHitEntity.class);
                }
                catch (IOException e) {
                    view.onLoadFailed("加载数据失败");
                    e.printStackTrace();
                }
                view.onLoadSuccess(entity);
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
