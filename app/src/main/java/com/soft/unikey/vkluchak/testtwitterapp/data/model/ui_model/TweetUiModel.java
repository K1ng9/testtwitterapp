package com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model;

import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.models.User;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class TweetUiModel {

    @SerializedName("id")
    public String id;
    @SerializedName("text")
    public String text;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("retweet_count")
    public int retweetCount;
    @SerializedName("user")
    public UserUiModel user;

    public TweetUiModel(String id, String text, User user, String createdAt, int retweetCount) {
        this.id = id;
        this.text = text;
        this.user = new UserUiModel(user.idStr , user.name, user.email, user.description, user.profileImageUrl );
        this.createdAt = createdAt;
        this.retweetCount = retweetCount;
    }
    public TweetUiModel(String id, String text, UserUiModel user, String createdAt, int retweetCount) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.createdAt = createdAt;
        this.retweetCount = retweetCount;
    }
    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public UserUiModel getUser() {
        return user;
    }

}
