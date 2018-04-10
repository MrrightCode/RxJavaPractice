package com.example.yb.mylibrary.CopyRxJava1;

/**
 * Create by :yb on 2018/4/9
 * Description:这是一个仿写RXJava的项目  Caller 相当于 Observable
 */
public class Caller<T> {

    final OnCall<T> onCall;

    public Caller(OnCall<T> onCall){
        this.onCall = onCall;
    }

    public static <T> Caller<T> Create(OnCall<T> onCall){
        return  new Caller<T>(onCall);
    }

    public Calling call(Receiver<T> receiver){
        //订阅很关键的一步 在这一步里面吧SubScriber 传递给了Observable的Create方法的参数OnCall
        this.onCall.call(receiver);
        return receiver;
    }

    public interface OnCall<T> extends  Action1<Receiver<T>>{

    }
}
