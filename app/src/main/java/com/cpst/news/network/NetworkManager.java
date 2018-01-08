package com.cpst.news.network;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wy on 2017/11/25.
 *  OKHttp + Retrofit
 *
 */

public class NetworkManager {
    private static Retrofit client;
    private static String   url;
    private static OkHttpClient ok = new OkHttpClient();

    /**
     * 根据url,获取commonClient
     */
    public static Retrofit getCommonClient(String baseUrl){
        if(client == null || !baseUrl.equals(url)) {
            client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(getHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    .build();
            url = baseUrl;
        }
        return client;
    }

    /**
     * OkHttpClient 拦截器
     * 1.获取请求request  参数、url
     * 2.获取响应response 内容、信息
     */
    private static OkHttpClient getHttpClient(){
        Interceptor interceptor = chain -> {
            Request request = chain.request();
            Log.i("TAG","请求参数："+ request.toString());

            long t1 = System.nanoTime();
            Response response = chain.proceed(chain.request());
            long t2 = System.nanoTime();
            Logger.d(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers(), response.code()));

            Log.d("TAG", "响应码："+response.code()+" 请求url:"+response.request().url()+" " +"响应的headers:"+ response.headers());
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.i("TAG", "返回数据：" + content);
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        };


        /**
         * 添加拦截器，设置超时时间
         */
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor());
        return builder.build();
    }
}
