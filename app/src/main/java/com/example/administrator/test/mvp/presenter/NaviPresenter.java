package com.example.administrator.test.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.entity.NaviEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.NaviContract;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.presenter
 * @ClassName: NaviPresenter
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/14 5:43 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/14 5:43 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class NaviPresenter implements NaviContract.Presenter {
    private NaviContract.View  view;
    private NaviContract.Model model;


    public NaviPresenter(NaviContract.View view, NaviContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getNaviData() {
        model.getNaviData(new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                try {
                    NaviEntity entity = JSON.parseObject(result.string(), NaviEntity.class);
                    if (0 > entity.getErrorCode()) {
                        view.getDataFail(entity.getErrorMsg());
                    }
                    else {
                        view.getDataSuccess(entity);
                    }
                }
                catch (IOException e) {
                    view.getDataFail("获取失败");
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String msg) {
                view.getDataFail(msg);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
