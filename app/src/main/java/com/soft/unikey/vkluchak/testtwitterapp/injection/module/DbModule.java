package com.soft.unikey.vkluchak.testtwitterapp.injection.module;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.DataBaseOpenHelper;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.resolvers.TweetUIStorIOSQLiteDeleteResolver;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.resolvers.TweetUIStorIOSQLiteGetResolver;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.resolvers.TweetUIStorIOSQLitePutResolver;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.resolvers.UserUIStorIOSQLiteDeleteResolver;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.resolvers.UserUIStorIOSQLiteGetResolver;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.resolvers.UserUIStorIOSQLitePutResolver;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.TweetUiModel;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.UserUiModel;
import com.soft.unikey.vkluchak.testtwitterapp.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 23.09.17.
 */

@Module
public class DbModule {

    @Provides
    @NonNull
    @Singleton
    public StorIOSQLite provideStorIOSQLite(@NonNull SQLiteOpenHelper sqLiteOpenHelper) {
        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(sqLiteOpenHelper)
                .addTypeMapping(TweetUiModel.class, SQLiteTypeMapping.<TweetUiModel>builder()
                        .putResolver(new TweetUIStorIOSQLitePutResolver())
                        .getResolver(new TweetUIStorIOSQLiteGetResolver())
                        .deleteResolver(new TweetUIStorIOSQLiteDeleteResolver())
                        .build())
                .addTypeMapping(UserUiModel.class, SQLiteTypeMapping.<UserUiModel>builder()
                        .putResolver(new UserUIStorIOSQLitePutResolver())
                        .getResolver(new UserUIStorIOSQLiteGetResolver())
                        .deleteResolver(new UserUIStorIOSQLiteDeleteResolver())
                        .build())
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    public SQLiteOpenHelper provideSQLiteOpenHelper(@NonNull @ApplicationContext Context context) {
        return new DataBaseOpenHelper(context);
    }
}
