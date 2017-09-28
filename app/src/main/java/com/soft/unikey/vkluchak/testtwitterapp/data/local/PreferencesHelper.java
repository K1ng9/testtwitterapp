package com.soft.unikey.vkluchak.testtwitterapp.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.soft.unikey.vkluchak.testtwitterapp.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesHelper {

    public static final String USER_ID = "com.soft.unikey.vkluchak.testtwitterapp.USER_ID";

    private final Context mContext;
    private final SharedPreferences prefs;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        this.mContext = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private void updatePref(String key, String value) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString(key, value);
        edit.apply();
    }
    private void updatePref(String key, long value) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putLong(key, value);
        edit.apply();
    }
    private void updatePref(String key, int value) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putInt(key, value);
        edit.apply();
    }
    private void updatePref(String key, boolean value) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }
    public void setUserId(long id) {
        updatePref(USER_ID, id);
    }
    public long getUserId() {
        return prefs.getLong(USER_ID, 0);
    }
}