package com.soft.unikey.vkluchak.testtwitterapp.data.api;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;


import rx.Observable;

import retrofit.http.EncodedQuery;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by user on 23.09.17.
 */

public interface TwitterApi {

  //  @GET("/statuses/home_timeline.json")
   // Observable<>

   // https://api.twitter.com/1.1/statuses/home_timeline.json
    @GET("/1.1/statuses/home_timeline.json")
    Observable<List<Tweet>> getHomeTimeLine(@Query("user_id") long id);
}
