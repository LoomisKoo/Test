package com.example.administrator.test.http;

/**
 * @param <T>
 * @author koo
 */
public interface HttpCallback<T> {
    /**
     * 成功
     *
     * @param result
     */
    void onSuccess(T result);

    /**
     * 失败
     *
     * @param msg
     */
    void onError(String msg);

    /**
     * 完成
     */
    void onComplete();
}
