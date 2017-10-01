package com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class UserUiModel {
    // using public for mapping

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("email")
    public String userEmail;
    @SerializedName("description")
    public String userDescription;
    @SerializedName("profile_image_url")
    public String profileImageUrl;


    public UserUiModel(String id, String name, String userEmail, String userDescription, String profileImageUrl) {
        this.id = id;
        this.name = name;
        this.userEmail = userEmail;
        this.userDescription = userDescription;
        this.profileImageUrl = profileImageUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
