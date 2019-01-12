package com.example.administrator.test.http.interceptor;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.http.interceptor
 * @ClassName: HttpHeadInterceptor
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/12 10:06 AM
 * @UpdateUser:
 * @UpdateDate: 2019/1/12 10:06 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HttpHeadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request         request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.addHeader("Accept", "application/json;versions=1");
        if (NetworkUtils.isConnected()) {
            int maxAge = 60;
            builder.addHeader("Cache-Control", "public, max-age=" + maxAge);
        }
        else {
            int maxStale = 60 * 60 * 24 * 28;
            builder.addHeader("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
        }
        return chain.proceed(builder.build());
    }
}