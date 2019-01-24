package com.example.administrator.test.http;

import android.annotation.SuppressLint;
import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author koo
 */
public interface Api {
    static void init(Context context) {

    }

    static Api getPlayAndroidService() {
        return BuilderFactory.getInstance()
                             .create(HttpConfigUtil.BASE_URL_PLAY_ANDROID);
    }

    static Api getRecommendService() {
        return BuilderFactory.getInstance()
                             .create(HttpConfigUtil.BASE_URL_GANK);
    }

    static <T> void query(Observable<T> observable, HttpCallback<T> callBack) {
        observable.subscribeOn(Schedulers.newThread())
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Observer<T>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(T t) {
                          callBack.onSuccess(t);
                      }

                      @SuppressLint("CheckResult")
                      @Override
                      public void onError(Throwable e) {
                          observable.unsubscribeOn(AndroidSchedulers.mainThread());
                          callBack.onError(e.toString());
                      }

                      @SuppressLint("CheckResult")
                      @Override
                      public void onComplete() {
                          observable.unsubscribeOn(AndroidSchedulers.mainThread());
                          callBack.onComplete();
                      }
                  });

    }
    //---------------------------------- playAndroid ------------------------------------

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
    Observable<ResponseBody> getBannerImg();

    /**
     * 玩安卓，文章列表、知识体系下的文章
     *
     * @param page 页码，从0开始
     * @param cid  体系id
     */
    @GET("article/list/{page}/json")
    Observable<ResponseBody> getArticleList(@Path("page") int page, @Query("cid") Integer cid);

    /**
     * 收藏本站文章，errorCode返回0为成功
     *
     * @param id 文章id
     */
    @POST("lg/collect/{id}/json")
    Observable<ResponseBody> collectArticle(@Path("id") int id);

    /**
     * 取消收藏(首页文章列表)
     *
     * @param id 文章id
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<ResponseBody> unCollectArticleOrigin(@Path("id") int id);

    /**
     * 取消收藏，我的收藏页面（该页面包含自己录入的内容）
     *
     * @param id       文章id
     * @param originId 列表页下发，无则为-1
     *                 (代表的是你收藏之前的那篇文章本身的id；
     *                 但是收藏支持主动添加，这种情况下，没有originId则为-1)
     */
    @FormUrlEncoded
    @POST("lg/uncollect/{id}/json")
    Observable<ResponseBody> unCollectArticle(@Path("id") int id, @Field("originId") int originId);

    /**
     * 玩安卓登录
     *
     * @param username 用户名
     * @param password 密码
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<ResponseBody> login(@Field("username") String username, @Field("password") String password);

    /**
     * 玩安卓退出登录
     */
    @GET("user/logout/json")
    Observable<ResponseBody> logout();

    /**
     * 玩安卓注册
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<ResponseBody> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);


    /**
     * 体系数据
     */
    @GET("tree/json")
    Observable<ResponseBody> getKownledgeSystem();

    /**
     * 导航数据
     */
    @GET("navi/json")
    Observable<ResponseBody> getNaviData();


    //---------------------------------- gank ------------------------------------

    /**
     * 每日推荐
     */
    @GET("today")
    Observable<ResponseBody> getTodayRecommend();
}
