package com.soft.unikey.vkluchak.testtwitterapp.injection.component;

import com.soft.unikey.vkluchak.testtwitterapp.app.screens.login.LoginFragment;
import com.soft.unikey.vkluchak.testtwitterapp.injection.PerActivity;
import com.soft.unikey.vkluchak.testtwitterapp.injection.module.FragmentModule;

import dagger.Component;

/**
 * Created by Vlad Kliuchak on 28.09.17.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(LoginFragment loginFragment);
}
