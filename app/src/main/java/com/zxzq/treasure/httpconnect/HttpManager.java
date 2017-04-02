package com.zxzq.treasure.httpconnect;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class HttpManager {
    private static HttpManager mHttpManager=null;
    private final Retrofit mRetrofit;
    private UserAPI mUserAPI;
    private HttpManager(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://admin.syfeicuiedu.com")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static HttpManager getHttpManager(){
        if(mHttpManager==null){
            synchronized (HttpManager.class){
                if(mHttpManager==null){
                    mHttpManager=new HttpManager();
                }
            }
        }
        return mHttpManager;
    }
    public UserAPI getUserAPI(){
        if(mUserAPI==null){
            mUserAPI=mRetrofit.create(UserAPI.class);
        }
        return mUserAPI;
    }



}
