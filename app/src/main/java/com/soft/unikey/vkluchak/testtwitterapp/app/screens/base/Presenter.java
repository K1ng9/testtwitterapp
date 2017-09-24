package com.soft.unikey.vkluchak.testtwitterapp.app.screens.base;

/**
 * Created by user on 23.09.17.
 */

public interface Presenter<V extends MvpView>{

    void attachView(V mvpView);

    void detachView();

}
