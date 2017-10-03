package com.soft.unikey.vkluchak.testtwitterapp.app.events;


import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.TweetUiModel;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class RXPublishSubBus {

    private final PublishSubject<List<TweetUiModel>> psOnTweetsMessages;
    private final PublishSubject<Object> psOnInternetConfigChange;

    @Inject
    public RXPublishSubBus(){
        psOnTweetsMessages = PublishSubject.create();
        psOnInternetConfigChange = PublishSubject.create();
    }



    public PublishSubject<List<TweetUiModel>> getPsOnTweetsMessages() {
        return psOnTweetsMessages;
    }

    public PublishSubject<Object> getPsOnInternetConfigChange() {
        return psOnInternetConfigChange;
    }


    public Observable<List<TweetUiModel>> getPsOnTweetsMessagesObservable() {
        return psOnTweetsMessages.asObservable();
    }

    public Observable<Object> getPsOnInternetConfigChangesObservable() {
        return psOnInternetConfigChange.asObservable();
    }
}
