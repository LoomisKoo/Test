package com.example.administrator.test.mvp.model;

import com.example.administrator.test.http.Api;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.http.HttpUtil;
import com.example.administrator.test.mvp.contract.KnowledgeSystemContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.model
 * @ClassName: KnowledgeSystemModel
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/13 4:35 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/13 4:35 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KnowledgeSystemModel implements KnowledgeSystemContract.Model {
    @Override
    public void loadData(HttpCallback callback) {
        Observable<ResponseBody> observable = Api.getPlayAndroidService()
                                                 .getKownledgeSystem();
        Api.query(observable, callback);
    }
}
