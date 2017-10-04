package com.soft.unikey.vkluchak.testtwitterapp.app.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.soft.unikey.vkluchak.testtwitterapp.R;
import com.soft.unikey.vkluchak.testtwitterapp.app.events.ConnectionChangeEvent;
import com.soft.unikey.vkluchak.testtwitterapp.app.events.RXPublishSubBus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by new on 03.10.17.
 */
public class NetworkReceiver extends BroadcastReceiver {
    public static final String ANY = "Any";

    public RXPublishSubBus rxChatBus;

    public static NetworkReceiver newInstance() {
        return new NetworkReceiver();
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conn = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();

        // If the setting is ANY network and there is a network connection
        // (which by process of elimination would be mobile), sets refreshDisplay to true.
        if (networkInfo != null) {
          //  rxChatBus.getPsOnInternetConfigChange().onNext(true);
            EventBus.getDefault().post(new ConnectionChangeEvent(true));

            //  networkInfo.getType();
            //  refreshDisplay = true;
            // Otherwise, the app can't download content--either because there is no network
            // connection (mobile or Wi-Fi), or because the pref setting is WIFI, and there
            // is no Wi-Fi connection.
            // Sets refreshDisplay to false.
        } else {
            EventBus.getDefault().post(new ConnectionChangeEvent(false));

            //rxChatBus.getPsOnInternetConfigChange().onNext(false);
            // refreshDisplay = false;
            Toast.makeText(context, R.string.lost_connection, Toast.LENGTH_SHORT).show();
        }
    }
}
