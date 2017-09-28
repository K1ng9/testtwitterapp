package com.soft.unikey.vkluchak.testtwitterapp.injection.module;

import android.app.Fragment;
import android.content.Context;

import com.soft.unikey.vkluchak.testtwitterapp.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 23.09.17.
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    public Fragment provideFragment() {
        return mFragment;
    }

    @Provides
    @ActivityContext
    public Context provideContext() {
        return mFragment.getActivity();
    }
}
