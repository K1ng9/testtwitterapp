package com.soft.unikey.vkluchak.testtwitterapp.app.screens.login;

import android.content.Context;

import com.soft.unikey.vkluchak.testtwitterapp.app.screens.main.MainActivity;
import com.soft.unikey.vkluchak.testtwitterapp.app.screens.base.Presenter;
import com.soft.unikey.vkluchak.testtwitterapp.data.DataManager;
import com.soft.unikey.vkluchak.testtwitterapp.injection.ApplicationContext;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by user on 23.09.17.
 */

public class LoginPresenter implements Presenter<LoginMvpView> {

    private final DataManager mDataManager;
    private LoginMvpView mMvpView;
    private Subscription mSubscription;


    @Inject
    public LoginPresenter(DataManager dataManager){
        mDataManager = dataManager;
    }

    @Override
    public void attachView(LoginMvpView mvpView) {
        this.mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        if (mSubscription != null) mSubscription.unsubscribe();
        mMvpView = null;
    }

    void startMainActivity(Context context){
        MainActivity.startNavigationActivity(context);
    }

    public void createTwitterSession() {
        mDataManager.createTwitterSession();
    }
}
