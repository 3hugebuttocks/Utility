package com.example.utility.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by caoyouqiang on 18-4-28.
 */

public class RetrofitHelper {
    private volatile static Retrofit mRetrofit;
    //private static final String BASE_URL = "https://2civireg.ivoka.com.cn";

    public static <T>T createApi(Class<T> clazz, String baseUrl){
        return getRetrofit(baseUrl).create(clazz);
    }

    private static Retrofit getRetrofit(String baseUrl){
        if(null == mRetrofit){
            synchronized (Retrofit.class){
                if(null == mRetrofit){ // 双重检验锁,仅第一次调用时实例化
                    mRetrofit = new Retrofit.Builder()
                            .client(OKHttpFactory.INSTANCE.getOKHttpClient())
                            .baseUrl(baseUrl)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return mRetrofit;
    }
}
