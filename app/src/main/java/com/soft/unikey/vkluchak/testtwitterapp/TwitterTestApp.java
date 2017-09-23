package com.soft.unikey.vkluchak.testtwitterapp;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.soft.unikey.vkluchak.testtwitterapp.injection.component.ApplicationComponent;
import com.soft.unikey.vkluchak.testtwitterapp.injection.component.DaggerApplicationComponent;
import com.soft.unikey.vkluchak.testtwitterapp.injection.module.ApplicationModule;
import com.twitter.sdk.android.core.Twitter;

import timber.log.Timber;

/**
 * Created by user on 23.09.17.
 */

public class TwitterTestApp extends Application {


    private ApplicationComponent mApplicationComponent;

    public void onCreate() {
        super.onCreate();

        Twitter.initialize(this);

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
      //  Fabric.with(this, new Crashlytics());

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
