package com.soft.unikey.vkluchak.testtwitterapp.app.screens.main.tweets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.soft.unikey.vkluchak.testtwitterapp.R;
import com.soft.unikey.vkluchak.testtwitterapp.app.screens.base.BaseFragment;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class TweetsFragment extends BaseFragment implements TweetsMvpView{
    @Inject
    TweetsPresenter tweetsPresenter;

    @BindView(R.id.tvText)
    TextView tvText;

    public static TweetsFragment newInstance() {
        return new TweetsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, fragmentView);
        tweetsPresenter.attachView(this);
        return fragmentView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tweetsPresenter.getCurrentUserTwits();
    }


    @Override
    public void currentUserTweetsList(List<Tweet> data) {
        tvText.setText(data.toString());
    }

    @Override
    public void onError(TwitterException e) {
        Log.d("TwitterKit", "TweetsFragment failure", e);
        Toast.makeText(getActivity() , "TweetsFragment failure: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tweetsPresenter.detachView();
    }
}
