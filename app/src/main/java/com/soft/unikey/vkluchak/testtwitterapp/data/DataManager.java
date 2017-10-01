package com.soft.unikey.vkluchak.testtwitterapp.data;

import com.soft.unikey.vkluchak.testtwitterapp.app.events.RXPublishSubBus;
import com.soft.unikey.vkluchak.testtwitterapp.data.api.ApiManager;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.PreferencesHelper;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.DataBaseUsageManager;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.TweetUiModel;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
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

    @Inject
    public DataManager(ApiManager apiManager, PreferencesHelper preferencesHelper, RXPublishSubBus rxChatBus, DataBaseUsageManager dataBaseUsageManager) {
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
        this.rxChatBus = rxChatBus;
        this.dataBaseUsageManager = dataBaseUsageManager;
    }

    // TODO try change callbacks to Observables
    public Observable<List<TweetUiModel>> getCurrentUserTwits() {
        getCurrentUserTweetsInternetCall();
        return Observable.concat(
                rxChatBus.getPsOnTweetsMessagesObservable(),
                dataBaseUsageManager.getAmpTodayFiles())
                .filter(tweetUiModelList -> tweetUiModelList != null)
                .first();
    }
    private void getCurrentUserTweetsInternetCall(){
        apiManager.getCurrentUserTwits(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> list) {
                rxChatBus.getPsOnTweetsMessages().onNext(
                        Observable.just(list.data).
                                flatMap((List<Tweet> tweetList) ->
                                        Observable.from(tweetList)
                                                .map(( elem) -> new TweetUiModel(
                                                        elem.idStr,
                                                        elem.text,
                                                        elem.user,
                                                        elem.createdAt,
                                                        elem.retweetCount)
                                                )
                                                .toList()
                                ));
            }

            @Override
            public void failure(TwitterException e) {
                rxChatBus.getPsOnTweetsMessages().onError(e);
            }
        });
    }
    public void safeUserId(long currentUserId) {
        preferencesHelper.setUserId(currentUserId);
    }

    public void createTwitterSession() {
        apiManager.createSession();
    }
}
