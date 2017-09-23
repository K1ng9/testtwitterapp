package com.soft.unikey.vkluchak.testtwitterapp.injection.component;

import android.app.Application;
import android.content.Context;

import com.soft.unikey.vkluchak.testtwitterapp.data.DataManager;
import com.soft.unikey.vkluchak.testtwitterapp.data.api.Api;
import com.soft.unikey.vkluchak.testtwitterapp.injection.ApplicationContext;
import com.soft.unikey.vkluchak.testtwitterapp.injection.module.ApplicationModule;
import com.soft.unikey.vkluchak.testtwitterapp.injection.module.DbModule;

import javax.inject.Singleton;

import dagger.Component;
/**
 * Created by user on 23.09.17.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DbModule.class})
public interface ApplicationComponent {

    @ApplicationContext Context context();
    Application application();
    Api api();
    DataManager dataManager();
}
