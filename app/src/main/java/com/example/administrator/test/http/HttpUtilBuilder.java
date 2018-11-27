package com.example.administrator.test.http;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtilBuilder<T> {

    private final int DEFAULT_TIMEOUT = 50;
    private T api;
    private Class<T> clazz;
    private Retrofit retrofit;
    private String baseUrl;
    private Interceptor interceptor;
    private OkHttpClient client;

    public HttpUtilBuilder(Class<T> clazz, String baseUrl, Interceptor interceptor) {
        this.clazz = clazz;
        this.baseUrl = baseUrl;
        this.interceptor = interceptor;
        init();
    }


    private void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        if (true) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
//        builder.addNetworkInterceptor(mCacheControlInterceptor);
        // .cache(cache)

        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        client = builder.build();
        buildRetrofit();
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
