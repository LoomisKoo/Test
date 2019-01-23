package com.example.administrator.test.mvp.model;

import com.example.administrator.test.http.Api;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.http.HttpUtil;
import com.example.administrator.test.mvp.contract.NaviContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.model
 * @ClassName: NaviModel
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/14 5:43 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/14 5:43 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class NaviModel implements NaviContract.Model {
    @Override
    public void getNaviData(HttpCallback callback) {
        Observable<ResponseBody> observable = Api.getPlayAndroidService().getNaviData();
        Api.query(observable, callback);
    }
}
