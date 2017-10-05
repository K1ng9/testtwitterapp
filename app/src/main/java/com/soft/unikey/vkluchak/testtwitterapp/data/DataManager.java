package com.soft.unikey.vkluchak.testtwitterapp.data;

import com.pushtorefresh.storio.sqlite.operations.put.PutResult;
import com.soft.unikey.vkluchak.testtwitterapp.app.events.RXPublishSubBus;
import com.soft.unikey.vkluchak.testtwitterapp.data.api.ApiManager;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.PreferencesHelper;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.DataBaseUsageManager;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.TweetUiModel;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.UserUiModel;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public Observable<List<TweetUiModel>> getCurrentUserTwits() {
        return Observable.concat(
                getCurrentUserTweetsInternetCall(),
                dataBaseUsageManager.getTweets())
                .filter(tweetUiModelList -> tweetUiModelList != null)
                .first();
    }


    public Observable<PutResult> sendTweet(String tweetText) {
        createTweetHolder(tweetText);
        return apiManager.sendTweet(tweetText)
                .flatMap((Tweet createdTweet) ->
                        dataBaseUsageManager.addOrUpdateTweetObservable(
                                new TweetUiModel(createdTweet.idStr, createdTweet.text,
                                        createdTweet.user, createdTweet.createdAt, createdTweet.retweetCount)));
    }


    private void createTweetHolder(String tweetText) {
        dataBaseUsageManager.addOrUpdateTweetBlock(new TweetUiModel(UUID.randomUUID().toString(), tweetText,
                null, null, 0, 0));
    }


    private Observable<List<TweetUiModel>> getCurrentUserTweetsInternetCall() {
        return apiManager.getCurrentUserTwits()
                .flatMap((List<Tweet> tweetList) -> Observable.from(tweetList)
                        .map(elem -> new TweetUiModel(
                                elem.idStr, elem.text, elem.user, elem.createdAt, elem.retweetCount)).toList()
                        .flatMap((List<TweetUiModel> tweetListFromServer) -> {
                                    saveDataToDb(tweetListFromServer);
                                    return Observable.just(tweetListFromServer);
                                }
                        ));

    }

    private void saveDataToDb(List<TweetUiModel> tweetListFromServer) {
        dataBaseUsageManager.addOrUpdateTweets(tweetListFromServer);

        // TODO change To observable
        List<UserUiModel> usersListToAddList = new ArrayList<>();
        for (TweetUiModel item : tweetListFromServer) {
            usersListToAddList.add(item.getUser());
        }
        dataBaseUsageManager.addOrUpdateUsers(usersListToAddList);
    }

    public void createTwitterSession() {
        apiManager.createSession();
    }

    public boolean startSync() {
        return dataBaseUsageManager.getNotSyncTweets().flatMap((List< TweetUiModel> tweetList) -> Observable.from(tweetList)
                .map(elem ->
                        sendTweet(elem.getText()
                )));
    }
}
