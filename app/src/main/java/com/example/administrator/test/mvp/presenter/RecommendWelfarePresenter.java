package com.example.administrator.test.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.entity.RecommendWelfareEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.RecommendWelfareContract;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.presenter
 * @ClassName: RecommendWelfarePresenter
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/26 2:05 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/26 2:05 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendWelfarePresenter implements RecommendWelfareContract.Presenter {
    private RecommendWelfareContract.Model model;
    private RecommendWelfareContract.View  view;

    public RecommendWelfarePresenter(RecommendWelfareContract.Model model, RecommendWelfareContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getWelfare(String id, int pre_page, int page) {
        model.getWelfare(id, pre_page, page, new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                RecommendWelfareEntity entity;
                try {
                    entity = JSON.parseObject(result.string(), RecommendWelfareEntity.class);
                    if (entity.isError()) {
                        view.onError("加载失败");
                    }
                    else {
                        int entitySize = entity.getResults()
                                               .size();
                        for (int i = 0; i < entitySize; i++) {
                            String url = entity.getResults()
                                               .get(i)
                                               .getUrl();
                            view.onSuccess(url);
                        }
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                    view.onError("加载异常");
                }
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
}
