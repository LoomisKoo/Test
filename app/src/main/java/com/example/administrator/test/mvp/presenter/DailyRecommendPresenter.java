package com.example.administrator.test.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.entity.DailyRecommendEntity;
import com.example.administrator.test.entity.view.DailyRecommendViewEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.DailyRecommendContract;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.presenter
 * @ClassName: DailyRecommendPresenter
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/23 9:14 AM
 * @UpdateUser:
 * @UpdateDate: 2019/1/23 9:14 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DailyRecommendPresenter implements DailyRecommendContract.Presenter {
    private DailyRecommendContract.Model model;
    private DailyRecommendContract.View  view;

    public DailyRecommendPresenter(DailyRecommendContract.Model model, DailyRecommendContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getDailyRecommend() {
        model.getDailyRecommend(new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                DailyRecommendEntity entity = null;
                try {
                    entity = JSON.parseObject(result.string(), DailyRecommendEntity.class);
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
