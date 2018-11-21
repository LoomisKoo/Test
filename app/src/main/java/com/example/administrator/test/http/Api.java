package com.example.administrator.test.http;

import com.example.administrator.test.entity.BookEntity;
import com.example.administrator.test.entity.TesttttBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {
    @GET("book/search")
    Call<BookEntity> getSearchBook(@Query("q") String name,
                                   @Query("tag") String tag,
                                   @Query("start") int start,
                                   @Query("count") int count);

    @GET
    Observable<TesttttBean> getGetData(@Url String url);
}
