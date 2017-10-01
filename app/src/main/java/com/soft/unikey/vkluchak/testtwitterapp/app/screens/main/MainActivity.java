package com.soft.unikey.vkluchak.testtwitterapp.app.screens.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.soft.unikey.vkluchak.testtwitterapp.R;
import com.soft.unikey.vkluchak.testtwitterapp.app.screens.base.BaseActivity;
import com.soft.unikey.vkluchak.testtwitterapp.app.screens.login.LoginFragment;
import com.soft.unikey.vkluchak.testtwitterapp.app.screens.main.tweets.TweetsFragment;
import com.soft.unikey.vkluchak.testtwitterapp.app.utils.ActivityUtils;

public class MainActivity extends BaseActivity {


    public static void startNavigationActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TweetsFragment tweetsFragment =
                (TweetsFragment) getFragmentManager().findFragmentById(R.id.contentFrame);
        if (tweetsFragment == null) {
            tweetsFragment = TweetsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getFragmentManager(), tweetsFragment,
                    R.id.contentFrame);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }
}
