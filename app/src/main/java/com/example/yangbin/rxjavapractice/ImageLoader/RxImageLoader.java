package com.example.yangbin.rxjavapractice.ImageLoader;

import android.content.Context;
import android.widget.ImageView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Create by :yb
 * Time :2018/4/4
 * Description:自定义图片加载缓存类
 */

public class RxImageLoader {

    static  RxImageLoader singleton = null;
    public String mUrl;
    private RequestCreator mRequestCreator;

    /**
    *@Create by  yb on 2018/4/4
    *@params:私有化构造方法
    *
    */
    private RxImageLoader(){
        mRequestCreator = new RequestCreator();
    }

    public static RxImageLoader with(Context context){
        if(singleton == null){
            synchronized (RxImageLoader.class){
                if(singleton == null){
                    singleton = new Builder(context).build();
                }
            }
        }
        return  singleton;
    }


    /**
    *@Create by  yb on 2018/4/4
    *@params: url 获取网络图片的URL
    *
    */
    public RxImageLoader load(String url){
        mUrl = url;
        return singleton;
    }


    /**
    *@Create by  yb on 2018/4/4
    *@params:将获取的图片设置给对应的控件
    *
    */
    public void  into(final ImageView imageView){
        Observable.concat(mRequestCreator.getImageFromMemory(mUrl),
                mRequestCreator.getImageFromDisk(mUrl),
                mRequestCreator.getImageFromNetWork(mUrl))
                .subscribe(new Observer<ImageModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImageModel imageModel) {

                        imageView.setImageBitmap(imageModel.getBitmap());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }



    public static  class Builder{
        private Context mContext;

        public Builder(Context context){
            mContext = context;
        }

        public RxImageLoader build(){
            return new RxImageLoader();
        }
    }
}
