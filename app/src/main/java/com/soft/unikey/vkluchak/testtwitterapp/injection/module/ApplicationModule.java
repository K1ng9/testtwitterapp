package com.soft.unikey.vkluchak.testtwitterapp.injection.module;

import android.app.Application;
import android.content.Context;

import com.soft.unikey.vkluchak.testtwitterapp.data.api.Api;
import com.soft.unikey.vkluchak.testtwitterapp.data.api.TwitterApi;
import com.soft.unikey.vkluchak.testtwitterapp.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 23.09.17.
 */
@Module
public class ApplicationModule {

    protected final Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    TwitterApi provideAmpApi() {
        return Api.Factory.makeTwitterBaseApi();
    }


}
