package com.soft.unikey.vkluchak.testtwitterapp.app.screens.base;

import android.app.Activity;

import com.soft.unikey.vkluchak.testtwitterapp.TwitterTestApp;
import com.soft.unikey.vkluchak.testtwitterapp.injection.component.ActivityComponent;
import com.soft.unikey.vkluchak.testtwitterapp.injection.component.DaggerActivityComponent;
import com.soft.unikey.vkluchak.testtwitterapp.injection.module.ActivityModule;

/**
 * Created by user on 23.09.17.
 */

public class BaseActivity extends Activity {

    private ActivityComponent mActivityComponent;

    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(TwitterTestApp.getApp(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }
}
