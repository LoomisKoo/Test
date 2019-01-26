package com.example.administrator.test.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.entity.RecommendDailyEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.RecommendDailyContract;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.presenter
 * @ClassName: RecommendDailyPresenter
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/23 9:14 AM
 * @UpdateUser:
 * @UpdateDate: 2019/1/23 9:14 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendDailyPresenter implements RecommendDailyContract.Presenter {
    private RecommendDailyContract.Model model;
    private RecommendDailyContract.View  view;

    public RecommendDailyPresenter(RecommendDailyContract.Model model, RecommendDailyContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getDailyRecommend() {
        model.getDailyRecommend(new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                RecommendDailyEntity entity = null;
                try {
                    entity = JSON.parseObject(result.string(), RecommendDailyEntity.class);
                }
                catch (IOException e) {
                    view.onError("加载失败！");
                    e.printStackTrace();
                }
                view.onSuccess(entity);
            }

            @Override
            public void onError(String msg) {
                view.onError(msg);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getBannerImg() {

    }
}
