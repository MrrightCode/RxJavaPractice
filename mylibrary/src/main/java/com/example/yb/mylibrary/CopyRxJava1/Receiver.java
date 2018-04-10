package com.example.yb.mylibrary.CopyRxJava1;

/**
 * Create by :yb on 2018/4/9
 * Description: 相当于SubScriber
 */
public abstract class  Receiver<T> implements Callee<T>,Calling{

    private volatile boolean unCalled;

    @Override
    public void unCall() {
        unCalled = true;
    }

    @Override
    public boolean isUnCalled() {
        return unCalled;
    }
}
