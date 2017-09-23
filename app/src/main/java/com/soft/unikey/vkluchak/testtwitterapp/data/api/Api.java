package com.soft.unikey.vkluchak.testtwitterapp.data.api;

import android.view.LayoutInflater;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.soft.unikey.vkluchak.testtwitterapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 23.09.17.
 */

public class Api {
    private final static String API_BASE_URL = "https://api.twitter.com";

    private TwitterApi apiBase;

    public Api(){
        apiBase = Factory.makeTwitterBaseApi();
    }


    public TwitterApi getApiBase() {
        return apiBase;
    }

    public static class Factory {
        public static TwitterApi makeTwitterBaseApi(){
            return  configureRetrofitBuilder(
                    configureHttpClient(), API_BASE_URL)
                    .create(TwitterApi.class);
        }



        private static Retrofit configureRetrofitBuilder(OkHttpClient okHttpClient, String currentUrl) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(currentUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

            return builder.build();
        }

        private static OkHttpClient configureHttpClient() {
            return getBaseHttpClientBuilder().build();
        }
        private static OkHttpClient.Builder getBaseHttpClientBuilder() {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS);
            if (BuildConfig.DEBUG) {
                httpClient.networkInterceptors().add(new StethoInterceptor());
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
                httpClient.addNetworkInterceptor(interceptor);
            }

            return httpClient;
        }
    }

}
