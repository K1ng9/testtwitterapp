package com.soft.unikey.vkluchak.testtwitterapp.app.screens.main.tweets;

import com.soft.unikey.vkluchak.testtwitterapp.app.screens.base.Presenter;
import com.soft.unikey.vkluchak.testtwitterapp.data.DataManager;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.TweetUiModel;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by Vlad Kliuchak on 28.09.17.
 */

public class TweetsPresenter implements Presenter<TweetsMvpView> {

    private final DataManager mDataManager;
    private TweetsMvpView mMvpView;
    private Subscription mSubscription;

    @Inject
    public TweetsPresenter(DataManager dataManager){
        this.mDataManager = dataManager;
    }


    @Override
    public void attachView(TweetsMvpView mvpView) {
        this.mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        if (mSubscription != null) mSubscription.unsubscribe();
        mMvpView = null;
    }

    void getCurrentUserTwits(){
        if (list != null && list.data != null) {
            if (mMvpView != null) mMvpView.currentUserTweetsList(list.data);
        }

        mSubscription = mDataManager.getCurrentUserTwits();
    }
}
