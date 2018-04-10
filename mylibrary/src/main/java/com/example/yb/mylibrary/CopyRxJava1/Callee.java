package com.example.yb.mylibrary.CopyRxJava1;

/**
 * Create by :yb on 2018/4/9
 * Description: Callee 相当于 Observer
 */
public interface Callee<T> {

    void onComplete();

    void onError(Throwable throwable);

    void onReceive(T t);
}
