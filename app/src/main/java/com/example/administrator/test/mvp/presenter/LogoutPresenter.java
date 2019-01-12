package com.example.administrator.test.mvp.presenter;

import android.content.Context;

import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.mvp.contract.LogoutContract;
import com.example.administrator.test.util.ACache;
import com.example.administrator.test.util.UserUtil;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.presenter
 * @ClassName: LogoutPresenter
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/11 5:55 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/11 5:55 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LogoutPresenter implements LogoutContract.Presenter, IBasePresenter {
    private LogoutContract.Model model;
    private LogoutContract.View  view;
    private Context              context;

    public LogoutPresenter(Context context, LogoutContract.Model model, LogoutContract.View view) {
        this.model = model;
        this.view = view;
        this.context = context;
    }

    @Override
    public void logout() {
        model.logout(new HttpCallback() {
            @Override
            public void onSuccess(Object result) {
                ACache mCache = ACache.get(context);
                mCache.remove("user");
                UserUtil.handleLoginFailure();
                view.logoutSuccess();
            }

            @Override
            public void onError(String msg) {
                view.logoutFail(msg);

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
