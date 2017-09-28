package com.soft.unikey.vkluchak.testtwitterapp;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.soft.unikey.vkluchak.testtwitterapp.injection.component.ApplicationComponent;
import com.soft.unikey.vkluchak.testtwitterapp.injection.component.DaggerApplicationComponent;
import com.soft.unikey.vkluchak.testtwitterapp.injection.module.ApplicationModule;
import io.fabric.sdk.android.Fabric;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import timber.log.Timber;

/**
 * Created by user on 23.09.17.
 */

public class TwitterTestApp extends Application {


    private ApplicationComponent mApplicationComponent;

    private static final String TWITTER_KEY = "CncvECzq4uBkrSzSmDAh8DphB";
    private static final String TWITTER_SECRET = "LXkpMgyyiBIFITlz169WwDUXfvWwYwxJ1DjJqIxn0tQC5Tzz1x";

    public void onCreate() {
        super.onCreate();


        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig), new Crashlytics());

        if (BuildConfig.DEBUG){
            doDebugWork();
        }else {
            doReleaseWork();
        }


        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void doReleaseWork() {
        Timber.plant(new Timber.DebugTree());
        // use Crashlytics for crash reporting
       // Fabric.with(this, new Crashlytics());

    }

    private void doDebugWork() {

        Stetho.initializeWithDefaults(this);

        // for live device db viewer
//        SqlScoutServer.create(this, getPackageName());
    }

    public static TwitterTestApp getApp(Context context){
        return (TwitterTestApp) context.getApplicationContext();
    }


    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
}
