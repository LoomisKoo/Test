package com.example.administrator.test.http;

import android.content.Context;

import com.example.administrator.test.TestApplication;
import com.example.administrator.test.app.AppConfig;
import com.example.administrator.test.http.interceptor.AddCacheInterceptor;
import com.example.administrator.test.http.interceptor.AddCookiesInterceptor;
import com.example.administrator.test.http.interceptor.HttpHeadInterceptor;
import com.example.administrator.test.http.interceptor.ReceivedCookiesInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
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
    private       Interceptor  interceptor;
    private       OkHttpClient client;
    private       boolean      isDebug;
    private       String       baseURL;
    private       Gson         gson;

    public HttpUtilBuilder(Class<T> clazz, Interceptor interceptor, String baseURL) {
        this.clazz = clazz;
        this.isDebug = AppConfig.IS_DEBUG;
        this.interceptor = interceptor;
        this.baseURL = baseURL;
        init();
    }

    private void init() {
        Context context = TestApplication.getApplication();
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
                    .client(client)
                    .baseUrl(baseURL)
//                .addConverterFactory(new NullOnEmptyConverterFactory())
//                .addConverterFactory(GsonConverterFactory.create(getGson()))
                    // 支持RxJava平台
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        api = retrofit.create(clazz);
    }

    public void resetBaseUrl(String baseUrl) {
        buildRetrofit();
    }

    public T getService() {
        return api;
    }
}
