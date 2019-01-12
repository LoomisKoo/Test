package com.example.administrator.test.mvp.model;

import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.http.HttpUtil;
import com.example.administrator.test.mvp.contract.LogoutContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.model
 * @ClassName: LogoutModel
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/11 5:55 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/11 5:55 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LogoutModel implements LogoutContract.Model {
    @Override
    public void logout(HttpCallback httpCallback) {
        Observable<ResponseBody> observable = HttpUtil.getInstance().getService().logout();
        HttpUtil.query(observable, httpCallback);
    }
}
