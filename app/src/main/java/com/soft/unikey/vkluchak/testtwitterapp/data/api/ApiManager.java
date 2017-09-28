package com.soft.unikey.vkluchak.testtwitterapp.data.api;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.services.ListService;
import com.twitter.sdk.android.core.services.SearchService;
import com.twitter.sdk.android.core.services.StatusesService;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import retrofit.http.Query;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Vlad Kliuchak on 28.09.17.
 */
@Singleton
public class ApiManager {
    private final StatusesService statusesService;
    private final SearchService searchService;
    private final ListService listService;

    @Inject
    public ApiManager(Api api){
        searchService = Twitter.getApiClient(api.getSession()).getSearchService();
        listService = Twitter.getApiClient(api.getSession()).getListService();
        statusesService = Twitter.getApiClient(api.getSession()).getStatusesService();

    }


    //TODO change it into Observables
    public void getCurrentUserTwits(long userIdLong, Callback callBack) {
        statusesService.userTimeline(userIdLong, null, null, null,null,null, null, null, null, callBack);
    }

}
