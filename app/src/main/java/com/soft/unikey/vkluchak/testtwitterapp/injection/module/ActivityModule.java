package com.soft.unikey.vkluchak.testtwitterapp.injection.module;

import android.app.Activity;
import android.content.Context;

import com.soft.unikey.vkluchak.testtwitterapp.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 23.09.17.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    public Context providesContext() {
        return mActivity;
    }
}
