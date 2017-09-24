package com.soft.unikey.vkluchak.testtwitterapp.app.screens.login;

import com.soft.unikey.vkluchak.testtwitterapp.app.screens.base.MvpView;
import com.soft.unikey.vkluchak.testtwitterapp.app.screens.base.Presenter;
import com.soft.unikey.vkluchak.testtwitterapp.data.DataManager;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by user on 23.09.17.
 */

public class LoginPresenter implements Presenter<LoginMvpView> {

    private final DataManager mDataManager;
    private MvpView mMvpView;
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
}
