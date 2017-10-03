package com.soft.unikey.vkluchak.testtwitterapp.data.api;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by new on 03.10.17.
 */

public class ApiClient extends TwitterApiClient {

    public ApiClient(TwitterSession session) {
        super(session);
    }

    /**
     * Provide CustomService with defined endpoints
     */
    public TwitterApi getTwitterService() {
        return getService(TwitterApi.class);
    }
}
