package com.example.administrator.test.http.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.http
 * @ClassName: AddCookiesInterceptor
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/12 9:19 AM
 * @UpdateUser:
 * @UpdateDate: 2019/1/12 9:19 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AddCookiesInterceptor implements Interceptor {
    private Context context;

    public AddCookiesInterceptor(Context context) {
        super();
        this.context = context;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder           = chain.request().newBuilder();
        SharedPreferences     sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        String                cookie            = sharedPreferences.getString("cookie", "");
        builder.addHeader("Cookie", cookie);
        return chain.proceed(builder.build());
    }
}
