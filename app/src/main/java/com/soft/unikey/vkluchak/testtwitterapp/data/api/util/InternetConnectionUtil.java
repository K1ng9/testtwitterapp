package com.soft.unikey.vkluchak.testtwitterapp.data.api.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.soft.unikey.vkluchak.testtwitterapp.R;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */


public class InternetConnectionUtil {
    private Context mContext;

    private PublishSubject<String> psIsInternetOn;

    @Inject
    public InternetConnectionUtil(Context context) {
        this.mContext = context;
        psIsInternetOn = PublishSubject.create();
    }

    public Observable<Boolean> isInternetOn() {
        NetworkInfo activeNetworkInfo = getNetworkInfo();
        return Observable.just(activeNetworkInfo != null && activeNetworkInfo.isConnected())
                .flatMap(connectionStatus -> {
                    if (!connectionStatus) {
                        psIsInternetOn.onNext(mContext.getResources().getString(R.string.no_connection_error));
                    }
                    return Observable.just(connectionStatus)
                            .filter(connectionStatusIS -> connectionStatusIS);
                });
    }


    private NetworkInfo getNetworkInfo() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

    public Observable<String> getPsIsInternetOn() {
        if (psIsInternetOn == null) {
            psIsInternetOn = PublishSubject.create();
        }
        return psIsInternetOn.asObservable();
    }

}