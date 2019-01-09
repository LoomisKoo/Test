package com.example.administrator.test.mvp.model;

import com.blankj.utilcode.util.StringUtils;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.http.HttpUtil;
import com.example.administrator.test.mvp.contract.LoginContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.model
 * @ClassName: LoginModel
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/9 4:37 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/9 4:37 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginModel implements LoginContract.Model {
    @Override
    public void login(String userName, String password, HttpCallback httpCallback) {
        Observable<ResponseBody> observable = HttpUtil.getInstance().getService().login(userName, password);
        HttpUtil.query(observable, httpCallback);
    }

    @Override
    public void register(String userName, String password, String rePassword, HttpCallback httpCallback) {
        Observable<ResponseBody> observable = HttpUtil.getInstance().getService().register(userName, password, rePassword);
        HttpUtil.query(observable, httpCallback);
    }
}
