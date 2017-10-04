package com.soft.unikey.vkluchak.testtwitterapp.app.events;

/**
 * Created by new on 04.10.17.
 */

public class ConnectionChangeEvent {
    private final boolean isInternetConnectionExist;

    public ConnectionChangeEvent(boolean isInternetConnectionExist){
        this.isInternetConnectionExist = isInternetConnectionExist;
    }

    public boolean isInternetConnectionExist() {
        return isInternetConnectionExist;
    }
}
