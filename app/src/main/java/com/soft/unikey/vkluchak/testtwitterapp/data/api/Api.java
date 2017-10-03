package com.soft.unikey.vkluchak.testtwitterapp.data.api;


import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.soft.unikey.vkluchak.testtwitterapp.BuildConfig;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 23.09.17.
 */
@Singleton
public class Api{
    private final static String API_BASE_URL = "https://api.twitter.com";
    private final static String API_VERSION = "/1.1/";

    private TwitterApi apiBase;
    private TwitterSession session;
    private ApiClient twitterApiClient;

    @Inject
    public Api() {
        //apiBase = Factory.makeTwitterBaseApi();
        //session = Factory.makeTwitterSession();
    }


    public TwitterApi getApiBase() {
        return apiBase;
    }

    public TwitterSession getSession() {
        return session;
    }

    public void createApiClient() {
        session = Factory.makeTwitterSession();
        twitterApiClient = new ApiClient(session);
        apiBase = twitterApiClient.getTwitterService();
    }

    public static class Factory {
        public static TwitterApi makeTwitterBaseApi() {

            //return  configureTwitterBuilder();
            return configureRetrofitBuilder(
                    configureHttpClient(), API_BASE_URL + API_VERSION)
                    .create(TwitterApi.class);

        }
/*
        private static TwitterApi configureTwitterBuilder() {
            final TwitterSession activeSession = TwitterCore.getInstance()
                    .getSessionManager().getActiveSession();

            // pass custom OkHttpClient into TwitterApiClient and add to TwitterCore
            final TwitterApiClient customApiClient;
            if (activeSession != null) {
                customApiClient = new TwitterApiClient(activeSession, configureHttpClient());
                TwitterCore.getInstance().addApiClient(activeSession, customApiClient);
            } else {
                customApiClient = new TwitterApiClient(configureHttpClient());
                TwitterCore.getInstance().addGuestApiClient(customApiClient);
            }
            return null;
        }
        */

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

        public static TwitterSession makeTwitterSession() {
            return Twitter.getSessionManager().getActiveSession();
        }
    }

}
