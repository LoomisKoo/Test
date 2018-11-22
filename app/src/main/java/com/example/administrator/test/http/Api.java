package com.example.administrator.test.http;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {
    @GET
    Observable<ResponseBody> getGetData(@Url String url);
}
