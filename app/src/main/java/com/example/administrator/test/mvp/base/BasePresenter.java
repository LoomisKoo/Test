package com.example.administrator.test.mvp.base;

import com.example.administrator.test.http.AppConfigUtil;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.http.HttpUtil;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @author koo
 */
public abstract class BasePresenter {
    protected void getData(Map<String, String> map, HttpCallback<ResponseBody> callback) {
        Observable<ResponseBody> observable = HttpUtil.getInstance().getService().getGetData(AppConfigUtil.url);
        HttpUtil.query(observable, callback);
    }
}
