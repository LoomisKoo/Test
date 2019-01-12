package com.example.administrator.test.http;

import android.content.Context;

import com.example.administrator.test.http.interceptor.AddCacheInterceptor;
import com.example.administrator.test.http.interceptor.AddCookiesInterceptor;
import com.example.administrator.test.http.interceptor.HttpHeadInterceptor;
import com.example.administrator.test.http.interceptor.ReceivedCookiesInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @param <T>
 * @author koo
 */
public class HttpUtilBuilder<T> {

    private final int          DEFAULT_TIMEOUT = 50;
    private       T            api;
    private       Class<T>     clazz;
    private       Retrofit     retrofit;
    private       String       baseUrl;
    private       Interceptor  interceptor;
    private       OkHttpClient client;
    private       Context      context;
    private       boolean      isDebug;

    public HttpUtilBuilder(boolean isDebug, Class<T> clazz, String baseUrl, Interceptor interceptor) {
        this.clazz = clazz;
        this.isDebug = isDebug;
        this.baseUrl = baseUrl;
        this.interceptor = interceptor;

    }

    public void setContext(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        //cache url
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        // 50 MiB
        int   cacheSize = 50 * 1024 * 1024;
        Cache cache     = new Cache(httpCacheDirectory, cacheSize);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.addInterceptor(new HttpHeadInterceptor());
        builder.addInterceptor(interceptor);
        // 持久化cookie
        builder.addInterceptor(new ReceivedCookiesInterceptor(context));
        builder.addInterceptor(new AddCookiesInterceptor(context));
        // 添加缓存，无网访问时会拿缓存,只会缓存get请求
        builder.addInterceptor(new AddCacheInterceptor());

        builder.addInterceptor(getInterceptor());
        builder.cache(cache);

        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
               .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
               .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
               .retryOnConnectionFailure(true);
        client = builder.build();
        buildRetrofit();
    }

    private HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (isDebug) {
            // 测试
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        else {
            // 打包
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return interceptor;
    }

    private void buildRetrofit() {
        if (null == retrofit) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConfigUtil.PLAY_ANDROID_BASE_URL)
                    // 支持RxJava平台
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        api = retrofit.create(clazz);

    }

    public void resetBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        buildRetrofit();
    }

    public T getService() {
        return api;
    }


}
