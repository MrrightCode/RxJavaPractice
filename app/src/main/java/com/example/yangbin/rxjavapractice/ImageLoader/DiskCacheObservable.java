package com.example.yangbin.rxjavapractice.ImageLoader;

/**
 * Create by :yb on 2018/4/6
 * Description:从内存中获取图片
 * 这里需要使用到DiaskLruCache 开源项目 有时间补充
 */
public class DiskCacheObservable  extends CacheObservable{
    @Override
    public ImageModel getDataFromCache(String url) {
        return null;
    }

    /**
     *@Create by  yb on 2018/4/7
     *@params:[imageModel]
     *TODO 这里需要实现在内存中进行缓存数据
     */
    @Override
    public void putDataToCache(ImageModel imageModel) {


    }
}
