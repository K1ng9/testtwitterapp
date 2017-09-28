package com.soft.unikey.vkluchak.testtwitterapp.app.screens.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soft.unikey.vkluchak.testtwitterapp.R;
import com.soft.unikey.vkluchak.testtwitterapp.app.screens.base.BaseFragment;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vlad Kliuchak on 28.09.17.
 */

public class LoginFragment extends BaseFragment implements LoginMvpView {

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

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, fragmentView);
        loginPresenter.attachView(this);

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                // session = Twitter.getSessionManager().getActiveSession();
                // Twitter.getApiClient(session).getSearchService().tweets();

                loginPresenter.startMainActivity(getActivity());

                Twitter.getApiClient(session).getAccountService()
                        .verifyCredentials(true, false, new Callback<User>() {

                            @Override
                            public void success(Result<User> userResult) {
                                if(userResult != null && userResult.data != null) {
                                    loginPresenter.safeUserId(userResult.data.getId());
                                }
                                /*
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
                                */

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }

    @Override
    public void onSignInError(Throwable e) {

    }

    @Override
    public void onSignInSuccessful() {

    }
}