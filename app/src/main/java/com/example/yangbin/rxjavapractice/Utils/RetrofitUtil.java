package com.example.yangbin.rxjavapractice.Utils;

import com.example.yangbin.rxjavapractice.Entity.TransformWord;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by yangbin on 2018/3/15.
 */

public class RetrofitUtil {

    private static ApiService apiService;
    private static String mBaseUrl = "http://fy.iciba.com/";

    public static ApiService getApiService(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9, TimeUnit.SECONDS);
        builder.addInterceptor(interceptor);

        if(apiService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }



    public interface ApiService{
        //ajax.php?a=fy&f=auto&t=auto&w=测试
        @GET("ajax.php")
        Observable<TransformWord> getTrasform(@QueryMap Map<String ,String> map);
    }

}
