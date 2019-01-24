package com.example.administrator.test.http.interceptor;

import android.util.Log;

import com.example.administrator.test.http.JsonLogFormat;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

import static okhttp3.internal.Util.UTF_8;

/**
 * @author koo
 */
public class BasicParamsInterceptor implements Interceptor {

    public static final String TAG  = "httpLog";
    public static final String POST = "POST";

    private Map<String, String> queryParamsMap  = new HashMap<>();
    private Map<String, String> paramsMap       = new HashMap<>();
    private Map<String, String> headerParamsMap = new HashMap<>();
    private List<String>        headerLinesList = new ArrayList<>();

    private BasicParamsInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        //这个chain里面包含了request和response，要什么都可以从这里拿
        Request request = chain.request();
        //请求发起的时间
        long t1 = System.nanoTime();

        String method = request.method();

        if (POST.equals(method)) {
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                Log.i(TAG, String.format("发送请求 %s on %s %n%s %n RequestParams:{%s}",
                                         request.url(), chain.connection(), request.headers(), sb.toString()));
            }
        }
        else {
            Log.i(TAG, String.format("发送请求 %s on %s%n%s",
                                     request.url(), chain.connection(), request.headers()));
        }

        Response response = chain.proceed(request);
        //收到响应的时间
        long         t2           = System.nanoTime();
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        JsonLogFormat.setIsLog(true);
        JsonLogFormat.setTAG(TAG);
        JsonLogFormat.json(responseBody.string());
        Log.i(TAG, "请求时间：" + (t2 - t1) / 1e6d + "ms");

        return response;
    }

    private void printParams(RequestBody body) {
        if (null == body) {
            return;
        }
        Buffer buffer = new Buffer();
        try {
            body.writeTo(buffer);
            Charset   charset     = Charset.forName("UTF-8");
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF_8);
            }
            String params = buffer.readString(charset);
            Log.e(TAG, "请求参数： | " + params);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // func to inject params into url
    private void injectParamsIntoUrl(Request request, Request.Builder
            requestBuilder, Map<String, String> paramsMap) {
        HttpUrl.Builder httpUrlBuilder = request.url()
                                                .newBuilder();
        if (paramsMap.size() > 0) {
            Iterator iterator = paramsMap.entrySet()
                                         .iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                httpUrlBuilder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }

        requestBuilder.url(httpUrlBuilder.build());
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy   = request;
            final Buffer      buffer = new Buffer();
            if (copy != null) {
                copy.writeTo(buffer);
            }
            else {
                return "";
            }
            return buffer.readUtf8();
        }
        catch (final IOException e) {
            return "did not work";
        }
    }

    public static class Builder {

        BasicParamsInterceptor interceptor;

        public Builder() {
            interceptor = new BasicParamsInterceptor();
        }

        public Builder addParam(String key, String value) {
            interceptor.paramsMap.put(key, value);
            return this;
        }

        public Builder addParamsMap(Map<String, String> paramsMap) {
            interceptor.paramsMap.putAll(paramsMap);
            return this;
        }

        public Builder addHeaderParam(String key, String value) {
            interceptor.headerParamsMap.put(key, value);
            return this;
        }

        public Builder addHeaderParamsMap(Map<String, String> headerParamsMap) {
            interceptor.headerParamsMap.putAll(headerParamsMap);
            return this;
        }

        public Builder addHeaderLine(String headerLine) {
            int index = headerLine.indexOf(":");
            if (index == -1) {
                throw new IllegalArgumentException("Unexpected header: " + headerLine);
            }
            interceptor.headerLinesList.add(headerLine);
            return this;
        }

        public Builder addHeaderLinesList(List<String> headerLinesList) {
            for (String headerLine : headerLinesList) {
                int index = headerLine.indexOf(":");
                if (index == -1) {
                    throw new IllegalArgumentException("Unexpected header: " + headerLine);
                }
                interceptor.headerLinesList.add(headerLine);
            }
            return this;
        }

        public Builder addQueryParam(String key, String value) {
            interceptor.queryParamsMap.put(key, value);
            return this;
        }

        public Builder addQueryParamsMap(Map<String, String> queryParamsMap) {
            interceptor.queryParamsMap.putAll(queryParamsMap);
            return this;
        }

        public BasicParamsInterceptor build() {
            return interceptor;
        }
    }
}