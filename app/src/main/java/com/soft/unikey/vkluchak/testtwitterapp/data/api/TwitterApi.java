package com.soft.unikey.vkluchak.testtwitterapp.data.api;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;


import okhttp3.ResponseBody;
import retrofit.ResponseCallback;
import retrofit.http.POST;
import retrofit2.Response;
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

    @POST("/1.1/statuses/update.json")
    Observable<Tweet> sendNewTweet(@Query("status") String textTwitter);
}
