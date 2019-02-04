package com.example.administrator.test.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.entity.MoviewHitEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.MovieHitContract;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.presenter
 * @ClassName: MovieHitPresenter
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/3 11:22 AM
 * @UpdateUser:
 * @UpdateDate: 2019/2/3 11:22 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MovieHitPresenter implements MovieHitContract.Presenter {
    private MovieHitContract.View  view;
    private MovieHitContract.Model model;


    public MovieHitPresenter(MovieHitContract.View view, MovieHitContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void loadData() {
        model.loadData(new HttpCallback<ResponseBody>() {
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
