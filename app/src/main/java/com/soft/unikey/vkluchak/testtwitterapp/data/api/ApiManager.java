package com.soft.unikey.vkluchak.testtwitterapp.data.api;

import com.soft.unikey.vkluchak.testtwitterapp.data.api.util.InternetConnectionUtil;
import com.squareup.picasso.Downloader;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.ListService;
import com.twitter.sdk.android.core.services.SearchService;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Vlad Kliuchak on 28.09.17.
 */
@Singleton
public class ApiManager {
    private final Api api;
    private StatusesService statusesService;
    private TwitterApi twitter;
    private ListService listService;
    private final InternetConnectionUtil internetConnection;

    @Inject
    public ApiManager(Api api, InternetConnectionUtil internetConnection){
        this.api = api;
        this.internetConnection = internetConnection;
    }

    public void createSession() {
        api.createApiClient();
    }

    //TODO change it into Observables
    public Observable<List<Tweet>> getCurrentUserTwits() {
       return internetConnection.isInternetOn()
                .switchMap(connectionStatus ->
                        api.getApiBase().getHomeTimeLine(api.getSession().getUserId()));
    }

    public Observable<Tweet> sendTweet(String tweet){
        return internetConnection.isInternetOn()
                .switchMap(connectionStatus ->
                        api.getApiBase().sendNewTweet(tweet)).delay(3, TimeUnit.SECONDS);

    }

}
