package com.soft.unikey.vkluchak.testtwitterapp.app.screens.main.tweets;

import com.pushtorefresh.storio.sqlite.operations.put.PutResult;
import com.soft.unikey.vkluchak.testtwitterapp.app.screens.base.Presenter;
import com.soft.unikey.vkluchak.testtwitterapp.data.DataManager;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.TweetUiModel;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

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

    void sendTweet(String tweetText){
        mSubscription = mDataManager.sendTweet(tweetText)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PutResult>() {
                    @Override
                    public void onCompleted() {
                        Timber.e("sendTweet onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("sendTweet onError: " + e);
                        if(mMvpView != null) mMvpView.onError(e);
                    }

                    @Override
                    public void onNext(PutResult putResult) {
                        Timber.i("sendTweet onNext: " + putResult);
                        if (mMvpView != null) mMvpView.sendTweetSuccessful();
                    }
                });
    }

    void getCurrentUserTwits(){
        mSubscription = mDataManager.getCurrentUserTwits()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<TweetUiModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("getCurrentUserTwits onError: " + e);
                        if(mMvpView != null) mMvpView.onError(e);
                    }

                    @Override
                    public void onNext(List<TweetUiModel> tweetUiModels) {
                        if(tweetUiModels != null)Timber.i("getCurrentUserTwits onNext: " + tweetUiModels.toString());
                        if (mMvpView != null) mMvpView.currentUserTweetsList(tweetUiModels);
                    }
                });
    }

    public void startSync() {
        mSubscription = mDataManager.startSync().toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PutResult>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("isSycSuccessful onError: " + e);
                       // if(mMvpView != null) mMvpView.onError(e);
                    }

                    @Override
                    public void onNext(List<PutResult> putResults) {
                        Timber.i("isSycSuccessful onNext: " + putResults);
                        if (mMvpView != null) mMvpView.syncSuccessful(putResults);
                    }
                });
    }
}
