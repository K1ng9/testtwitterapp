package com.soft.unikey.vkluchak.testtwitterapp.app.screens.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.soft.unikey.vkluchak.testtwitterapp.R;
import com.soft.unikey.vkluchak.testtwitterapp.app.screens.base.BaseActivity;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import io.fabric.sdk.android.Fabric;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.models.User;

/**
 * Created by user on 23.09.17.
 */

public class LoginActivity extends BaseActivity implements LoginMvpView{

    @BindView(R.id.twitter_login_button)
    TwitterLoginButton loginButton;

    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.screen_name)
    TextView screen_name;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.user_location)
    TextView user_location;
    @BindView(R.id.user_timezone)
    TextView user_timezone;
    @BindView(R.id.user_description)
    TextView user_description;
    @BindView(R.id.profile_pic)
    ImageView user_picture;

    TwitterSession session;

    String screenname,username,twitterImage,location,timeZone,description;

    // Add your Consumer Key and Consumer Secret Key generated while creating app on Twitter.
    //See "Keys and access Tokens" in your app on twitter to find these keys.
    private static final String TWITTER_KEY = "CncvECzq4uBkrSzSmDAh8DphB";
    private static final String TWITTER_SECRET = "LXkpMgyyiBIFITlz169WwDUXfvWwYwxJ1DjJqIxn0tQC5Tzz1x";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activityComponent().inject(this);
        loginPresenter.attachView(this);
        ButterKnife.bind(this);

//For authentication, pass consumer key and consumer secret key
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig), new Crashlytics());

        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                session = Twitter.getSessionManager().getActiveSession();

                Twitter.getApiClient(session).getAccountService()
                        .verifyCredentials(true, false, new Callback<User>() {

                            @Override
                            public void success(Result<User> userResult) {

                                User user = userResult.data;

                                twitterImage = user.profileImageUrl;
                                screenname = user.screenName;
                                username = user.name;
                                location = user.location;
                                timeZone = user.timeZone;
                                description = user.description;

                                Picasso.with(getApplicationContext()).load(twitterImage.toString())
                                        .into(user_picture);

                                screen_name.setText("Username : " + screenname);
                                user_name.setText("Name : "+username);
                                user_location.setText("Location : "+location);
                                user_timezone.setText("Timezone : "+timeZone);
                                user_description.setText("Description : "+description);

                            }

                            @Override
                            public void failure(TwitterException e) {
                                Log.d("TwitterKit", "Login with Twitter failure", e);
                            }

                        });

                loginButton.setVisibility(View.GONE);


            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }


        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }
}
