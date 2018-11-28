package com.example.administrator.test.http;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author koo
 */
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

    /**
     * 玩安卓，文章列表、知识体系下的文章
     *
     * @param page 页码，从0开始
     * @param cid  体系id
     */
    @GET("article/list/{page}/json")
    Observable<ResponseBody> getHomeList(@Path("page") int page, @Query("cid") Integer cid);
}
