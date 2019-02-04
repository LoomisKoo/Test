package com.example.administrator.test.http;

import com.example.administrator.test.http.interceptor.BasicParamsInterceptor;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.http
 * @ClassName: BuilderFactory
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/23 9:49 AM
 * @UpdateUser:
 * @UpdateDate: 2019/1/23 9:49 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BuilderFactory {
    private static BuilderFactory instance;
    private        Api            playAndroidApi;
    private        Api            gankApi;
    private        Api            doubanApi;

    public static BuilderFactory getInstance() {
        if (instance == null) {
            synchronized (BuilderFactory.class) {
                if (instance == null) {
                    instance = new BuilderFactory();
                }
            }
        }
        return instance;
    }

    public Api create(String baseUrl) {
        switch (baseUrl) {
            case HttpConfigUtil.BASE_URL_PLAY_ANDROID:
                return create(playAndroidApi, baseUrl);
            case HttpConfigUtil.BASE_URL_GANK:
                return create(gankApi, baseUrl);
            case HttpConfigUtil.BASE_URL_DOUBAN:
                return create(doubanApi, baseUrl);
            default:
                break;
        }
        return null;
    }

    /**
     * 根据类型生成对应的api
     *
     * @param api
     * @param baseUrl
     * @return
     */
    private Api create(Api api, String baseUrl) {
        if (api == null) {
            synchronized (Api.class) {
                if (api == null) {
                    api = new HttpUtilBuilder<>(Api.class, new BasicParamsInterceptor.Builder().build(), baseUrl).getService();
                }
            }
        }
        return api;
    }
}
