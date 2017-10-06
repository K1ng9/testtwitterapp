package com.soft.unikey.vkluchak.testtwitterapp.app.screens.base;

import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.soft.unikey.vkluchak.testtwitterapp.R;
import com.soft.unikey.vkluchak.testtwitterapp.TwitterTestApp;
import com.soft.unikey.vkluchak.testtwitterapp.injection.component.ActivityComponent;
import com.soft.unikey.vkluchak.testtwitterapp.injection.component.DaggerActivityComponent;
import com.soft.unikey.vkluchak.testtwitterapp.injection.module.ActivityModule;

/**
 * Created by user on 23.09.17.
 */

public class BaseActivity extends Activity {
    private Toolbar toolbar;
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

    public void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
    }
    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbarTitle(String newToolbarTitle){
        if(getToolbar() != null && newToolbarTitle != null) {
            toolbar.setTitle(newToolbarTitle);
        }
    }
}
