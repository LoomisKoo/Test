package com.example.administrator.test.http;

public interface HttpCallback<T> {
    public void onSuccess(T result);
    public void onError(String msg);
    public void onComplete();
}
