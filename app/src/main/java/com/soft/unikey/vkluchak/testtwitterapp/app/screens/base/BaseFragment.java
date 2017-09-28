package com.soft.unikey.vkluchak.testtwitterapp.app.screens.base;

import android.app.Fragment;
import android.os.Bundle;

import com.soft.unikey.vkluchak.testtwitterapp.TwitterTestApp;
import com.soft.unikey.vkluchak.testtwitterapp.injection.component.FragmentComponent;
import com.soft.unikey.vkluchak.testtwitterapp.injection.module.FragmentModule;

/**
 * Created by user on 23.09.17.
 */

public class BaseFragment extends Fragment {
    private FragmentComponent mFragmentComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public FragmentComponent fragmentComponent() {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(new FragmentModule(this))
                    .applicationComponent(TwitterTestApp.getApp(getActivity()).getComponent())
                    .build();
        }
        return mFragmentComponent;
    }

    @Override
    public void onPause() {
        super.onPause();

        /* // for cancel toasts when/if activity has no focus
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).hideToast();
        }
        */
    }
}
