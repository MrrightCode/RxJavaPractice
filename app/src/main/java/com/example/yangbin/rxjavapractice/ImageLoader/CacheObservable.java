package com.example.yangbin.rxjavapractice.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class CacheObservable {

    public  Observable<ImageModel> getImage(final String url){
        return Observable.create(new ObservableOnSubscribe<ImageModel>() {
            @Override
            public void subscribe(ObservableEmitter<ImageModel> emitter) throws Exception {
                ImageModel imageModel = getDataFromCache(url);
                //判断订阅没有解除则发送
                if(!emitter.isDisposed()){
                    emitter.onNext(imageModel);
                    emitter.onComplete();
                }

            }
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread());
    }

    public abstract ImageModel getDataFromCache(String url);

    public abstract void putDataToCache(ImageModel imageModel);
}
