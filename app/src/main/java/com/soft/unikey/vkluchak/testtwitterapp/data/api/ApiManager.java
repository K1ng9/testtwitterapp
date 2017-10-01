package com.soft.unikey.vkluchak.testtwitterapp.data.api;

import com.soft.unikey.vkluchak.testtwitterapp.data.api.util.InternetConnectionUtil;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.ListService;
import com.twitter.sdk.android.core.services.SearchService;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Vlad Kliuchak on 28.09.17.
 */
@Singleton
public class ApiManager {
    private final Api api;
    private StatusesService statusesService;
    private SearchService searchService;
    private ListService listService;
    private final InternetConnectionUtil internetConnection;

    @Inject
    public ApiManager(Api api, InternetConnectionUtil internetConnection){
        this.api = api;
        this.internetConnection = internetConnection;
    }

    public void createSession() {
        api.createSession();
        createServices();
    }

    //TODO change it into Observables
    public void getCurrentUserTwits(Callback<List<Tweet>> callBack) {
        if(statusesService == null){
            createServices();
        }

        internetConnection.isInternetOn()
                .switchMap(connectionStatus -> {
                    statusesService.userTimeline(api.getSession().getUserId(), null, null, null, null, null, null, null, null, callBack);

                    //TODO  delete it
                    return Observable.just(true);
                });
    }

    private void createServices() {
        searchService = Twitter.getApiClient(api.getSession()).getSearchService();
        listService = Twitter.getApiClient(api.getSession()).getListService();
        statusesService = Twitter.getApiClient(api.getSession()).getStatusesService();
    }
}
