package com.example.administrator.test.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.entity.LoginEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.LoginContract;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.presenter
 * @ClassName: LoginPresenter
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/9 4:37 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/9 4:37 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.Model model;
    private LoginContract.View  view;

    public LoginPresenter(LoginContract.Model model, LoginContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void login(String userName, String passWord) {
        model.login(userName, passWord, new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                try {
                    String      strResult = result.string();
                    LoginEntity entity    = JSON.parseObject(strResult, LoginEntity.class);
                    if (-1 == entity.getErrorCode()) {
                        view.loginFail(entity.getErrorMsg());
                    }
                    else {
                        view.loginSuccess();
                    }
                }
                catch (IOException e) {
                    view.loginFail("登录失败");
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String msg) {
                view.loginFail(msg);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void register(String userName, String password, String rePassword) {
        model.register(userName, password, rePassword, new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {

                try {
                    String      strResult = result.string();
                    LoginEntity entity    = JSON.parseObject(strResult, LoginEntity.class);
                    if (-1 == entity.getErrorCode()) {
                        view.registerFail(entity.getErrorMsg());
                    }
                    else {
                        view.registerSuccess();
                    }
                }
                catch (IOException e) {
                    view.registerFail("注册失败");
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String msg) {
                view.registerFail(msg);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
