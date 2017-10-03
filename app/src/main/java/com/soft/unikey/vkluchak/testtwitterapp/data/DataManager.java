package com.soft.unikey.vkluchak.testtwitterapp.data;

import com.soft.unikey.vkluchak.testtwitterapp.app.events.RXPublishSubBus;
import com.soft.unikey.vkluchak.testtwitterapp.app.receivers.NetworkReceiver;
import com.soft.unikey.vkluchak.testtwitterapp.data.api.ApiManager;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.PreferencesHelper;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.DataBaseUsageManager;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.TweetUiModel;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by user on 23.09.17.
 */
@Singleton
public class DataManager {
    private final PreferencesHelper preferencesHelper;
    private final ApiManager apiManager;
    private final RXPublishSubBus rxChatBus;
    private final DataBaseUsageManager dataBaseUsageManager;
    private final NetworkReceiver networkReceiver;

    @Inject
    public DataManager(ApiManager apiManager, PreferencesHelper preferencesHelper, RXPublishSubBus rxChatBus, DataBaseUsageManager dataBaseUsageManager,
                       NetworkReceiver networkReceiver) {
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
        this.rxChatBus = rxChatBus;
        this.dataBaseUsageManager = dataBaseUsageManager;
        this.networkReceiver = networkReceiver;
    }
    public Observable<List<TweetUiModel>> getCurrentUserTwits() {
        return Observable.concat(
                getCurrentUserTweetsInternetCall(),
                dataBaseUsageManager.getTweets())
                .filter(tweetUiModelList -> tweetUiModelList != null)
                .first();
    }

    private Observable<List<TweetUiModel>> getCurrentUserTweetsInternetCall(){
        return apiManager.getCurrentUserTwits()
                .flatMap((List<Tweet> tweetList) -> Observable.from(tweetList)
                        .map(elem -> new TweetUiModel(
                                elem.idStr, elem.text, elem.user, elem.createdAt, elem.retweetCount)).toList());



    }
    public void safeUserId(long currentUserId) {
        preferencesHelper.setUserId(currentUserId);
    }

    public void createTwitterSession() {
        apiManager.createSession();
    }
}
