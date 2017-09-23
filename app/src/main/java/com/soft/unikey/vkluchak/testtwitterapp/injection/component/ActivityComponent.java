package com.soft.unikey.vkluchak.testtwitterapp.injection.component;

import com.soft.unikey.vkluchak.testtwitterapp.app.screens.login.LoginActivity;
import com.soft.unikey.vkluchak.testtwitterapp.injection.PerActivity;
import com.soft.unikey.vkluchak.testtwitterapp.injection.module.ActivityModule;

import dagger.Component;

/**
 * Created by user on 23.09.17.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);
}
