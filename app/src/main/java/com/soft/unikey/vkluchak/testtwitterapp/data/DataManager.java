package com.soft.unikey.vkluchak.testtwitterapp.data;

import com.soft.unikey.vkluchak.testtwitterapp.data.api.ApiManager;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.PreferencesHelper;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;
import rx.Subscription;

/**
 * Created by user on 23.09.17.
 */
@Singleton
public class DataManager {
    private final PreferencesHelper preferencesHelper;
    private final ApiManager apiManager;
    @Inject
    public DataManager(ApiManager apiManager, PreferencesHelper preferencesHelper){
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    // TODO try change callbacks to Observables
    public void getCurrentUserTwits(Callback<List<Tweet>> callback) {
        apiManager.getCurrentUserTwits(callback);
    }

    public void safeUserId(long currentUserId) {
        preferencesHelper.setUserId(currentUserId);
    }

    public void createTwitterSession() {
        apiManager.createSession();
    }
}
