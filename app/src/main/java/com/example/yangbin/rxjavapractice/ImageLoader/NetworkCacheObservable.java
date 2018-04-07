package com.example.yangbin.rxjavapractice.ImageLoader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Create by :yb on 2018/4/6
 * Description:从网络中获取图片
 */
public class NetworkCacheObservable extends CacheObservable {
    @Override
    public ImageModel getDataFromCache(String url) {
        Bitmap bitmap = downLoadImage(url);
        if(bitmap != null){
            return  new ImageModel(url,bitmap);
        }
        return  null;
    }

    @Override
    public void putDataToCache(ImageModel imageModel) {

    }

    private Bitmap downLoadImage(String url){
        Bitmap bitmap = null;
        InputStream inputStream = null;

        try {
            final URLConnection connection = new URL(url).openConnection();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return  bitmap;
    }

}
