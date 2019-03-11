package com.example.administrator.test.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.entity.KnowledgeSystemEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.KnowledgeSystemContract;

import java.io.IOException;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.presenter
 * @ClassName: KnowledgeSystemPresenter
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/13 4:35 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/13 4:35 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KnowledgeSystemPresenter implements KnowledgeSystemContract.Presenter {
    private KnowledgeSystemContract.Model model;
    private KnowledgeSystemContract.View  view;

    public KnowledgeSystemPresenter(KnowledgeSystemContract.Model model, KnowledgeSystemContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void loadData() {
        model.loadData(new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                KnowledgeSystemEntity entity = null;
                try {
                    entity = JSON.parseObject(result.string(), KnowledgeSystemEntity.class);
                    if (entity.getErrorCode() >= 0) {
                        view.loadDataSuccess(entity);
                    }
                    else {
                        view.loadDataFail(entity.getErrorMsg());
                    }
                }
                catch (IOException e) {
                    view.loadDataFail("数据获取失败");
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String msg) {
                view.loadDataFail(msg);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
