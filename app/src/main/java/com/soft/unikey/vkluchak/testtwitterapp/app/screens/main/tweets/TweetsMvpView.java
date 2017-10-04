package com.soft.unikey.vkluchak.testtwitterapp.app.screens.main.tweets;

import com.soft.unikey.vkluchak.testtwitterapp.app.screens.base.MvpView;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.TweetUiModel;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Created by Vlad Kliuchak on 28.09.17.
 */

public interface TweetsMvpView extends MvpView {

    void currentUserTweetsList(List<TweetUiModel> data);
    void onError(TwitterException e);

    void onError(Throwable e);

    void syncSuccessful(Boolean isSycSuccessful);

}
