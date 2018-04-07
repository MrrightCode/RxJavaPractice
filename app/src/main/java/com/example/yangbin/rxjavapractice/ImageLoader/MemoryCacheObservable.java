package com.example.yangbin.rxjavapractice.ImageLoader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Create by :yb on 2018/4/ 6
 * Description:从缓存中获取图片
 */
public class MemoryCacheObservable extends CacheObservable {

    //获取APP分配的内存 /1024  得到KB
    int macMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
    int maxSize = macMemory/8;

    /**
     * 这段代码需要去搞懂意图
     * TODO
     */
    LruCache<String,Bitmap> mLruCache = new LruCache<String, Bitmap>(maxSize){
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes()*value.getHeight()/1024;
        }
    };
    @Override
    public ImageModel getDataFromCache(String url) {
        Bitmap bitmap = mLruCache.get(url);
        if(bitmap != null){
            return new ImageModel(url,bitmap);
        }

        return null;
    }

    /**
    *@Create by  yb on 2018/4/7
    *@params:
    *这里需要实现在内存中缓存图片
    */
    @Override
    public void putDataToCache(ImageModel imageModel) {


    }
}
