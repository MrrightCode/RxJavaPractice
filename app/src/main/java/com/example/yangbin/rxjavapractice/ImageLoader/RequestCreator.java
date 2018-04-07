package com.example.yangbin.rxjavapractice.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Create by :yb on 2018/4/6
 * Description:生成图片的三种来源的Observable的管理类
 */
public class RequestCreator {

    public MemoryCacheObservable mMemoryCacheObservable;
    public DiskCacheObservable mDiskCacheObservable;
    public NetworkCacheObservable mNetworkCacheObservable;


    public RequestCreator(){
        mMemoryCacheObservable = new MemoryCacheObservable();
        mDiskCacheObservable = new DiskCacheObservable();
        mNetworkCacheObservable = new NetworkCacheObservable();
    }


    public Observable<ImageModel> getImageFromMemory(String url){
        return mMemoryCacheObservable.getImage(url);
    }


    public Observable<ImageModel> getImageFromDisk(String url){
        return mDiskCacheObservable.getImage(url)
                .filter(new Predicate<ImageModel>() {
                    @Override
                    public boolean test(ImageModel imageModel) throws Exception {
                        return imageModel != null;
                    }
                }).doOnNext(new Consumer<ImageModel>() {
                    @Override
                    public void accept(ImageModel imageModel) throws Exception {
                        mMemoryCacheObservable.putDataToCache(imageModel);
                    }
                });
    }

    public Observable<ImageModel> getImageFromNetWork(String url){
        //这里是从网络获取的数据 意味着需要对数据进行一次缓存操作
        return mNetworkCacheObservable.getImage(url)
                .filter(new Predicate<ImageModel>() {
                    //筛选操作 判断从网络获取的数据是不是空的
                    @Override
                    public boolean test(ImageModel imageModel) throws Exception {
                       return imageModel != null;
                    }
                })
                .doOnNext(new Consumer<ImageModel>() {
                    //伴随操作 在onNext 执行之前进行操作
                    @Override
                    public void accept(ImageModel imageModel) throws Exception {
                        mDiskCacheObservable.putDataToCache(imageModel);
                        mMemoryCacheObservable.putDataToCache(imageModel);
                    }
                });
    }
}
