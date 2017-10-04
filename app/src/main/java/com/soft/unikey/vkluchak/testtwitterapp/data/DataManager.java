package com.soft.unikey.vkluchak.testtwitterapp.data;

import com.soft.unikey.vkluchak.testtwitterapp.app.events.RXPublishSubBus;
import com.soft.unikey.vkluchak.testtwitterapp.app.receivers.NetworkReceiver;
import com.soft.unikey.vkluchak.testtwitterapp.data.api.ApiManager;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.PreferencesHelper;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.DataBaseUsageManager;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.TweetUiModel;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.UserUiModel;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;

import static java.util.Collections.addAll;

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


    public Observable<Void> sendTweet(String tweetText) {
        return apiManager.sendTweet(tweetText);
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

    public Observable<Boolean> startSync() {
        return null;
    }
}
