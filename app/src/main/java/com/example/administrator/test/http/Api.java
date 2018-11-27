package com.example.administrator.test.http;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {
    /**
     * @param url
     * @return
     */
    @GET
    Observable<ResponseBody> getData(@Url String url);

    /**
     * 玩安卓轮播图
     *
     * @return
     */
    @GET("banner/json")
    Observable<ResponseBody> getBanerImg();
}
