package com.example.administrator.test.http.interceptor;

import android.content.Context;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.http.interceptor
 * @ClassName: AddCacheInterceptor
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/12 9:30 AM
 * @UpdateUser:
 * @UpdateDate: 2019/1/12 9:30 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AddCacheInterceptor implements Interceptor {

    public AddCacheInterceptor() {
        super();
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
        cacheBuilder.maxAge(0, TimeUnit.SECONDS);
        cacheBuilder.maxStale(365, TimeUnit.DAYS);
        CacheControl cacheControl = cacheBuilder.build();
        Request      request      = chain.request();
        if (!NetworkUtils.isConnected()) {
            request = request.newBuilder()
                             .cacheControl(cacheControl)
                             .build();
        }
        Response originalResponse = chain.proceed(request);
        if (NetworkUtils.isConnected()) {
            // read from cache
            int maxAge = 0;
            return originalResponse.newBuilder()
                                   .removeHeader("Pragma")
                                   .header("Cache-Control", "public ,max-age=" + maxAge)
                                   .build();
        }
        else {
            // tolerate 4-weeks stale
            int maxStale = 60 * 60 * 24 * 28;
            return originalResponse.newBuilder()
                                   .removeHeader("Pragma")
                                   .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                   .build();
        }
    }
}