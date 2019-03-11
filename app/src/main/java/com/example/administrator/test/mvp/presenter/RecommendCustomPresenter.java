package com.example.administrator.test.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.entity.RecommendCustomEntity;
import com.example.administrator.test.entity.view.BaseViewEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.RecommendCustomContract;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.presenter
 * @ClassName: RecommendCustomPresenter
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/29 2:00 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/29 2:00 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendCustomPresenter implements RecommendCustomContract.Presenter {
    private RecommendCustomContract.Model model;
    private RecommendCustomContract.View  view;

    public RecommendCustomPresenter(RecommendCustomContract.Model model, RecommendCustomContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getCustomData(String id, int pre_page, int page) {
        model.getCustomData(id, pre_page, page, new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                RecommendCustomEntity entity = null;
                try {
                    entity = JSON.parseObject(result.string(), RecommendCustomEntity.class);
                    if (entity.isError()) {
                        view.onError("加载失败");
                    }
                    else {
                        List<RecommendCustomEntity.CustomInfoEntity> dataList     = entity.getResults();
                        int                                          dataListSize = dataList.size();
                        //封装数据
                        for (int i = 0; i < dataListSize; i++) {
                            RecommendCustomEntity.CustomInfoEntity data             = dataList.get(i);
                            BaseViewEntity                         customViewEntity = new BaseViewEntity(data, BaseViewEntity.RECOMMEND_CUSTOM_VIEW_TYPE_ARTICLE_LIST);
                            view.onSuccess(customViewEntity);
                        }
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                    view.onError("加载失败");
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
