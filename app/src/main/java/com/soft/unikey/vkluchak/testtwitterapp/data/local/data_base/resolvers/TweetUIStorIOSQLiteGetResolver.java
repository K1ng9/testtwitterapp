package com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.resolvers;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.get.DefaultGetResolver;
import com.pushtorefresh.storio.sqlite.queries.Query;
import com.pushtorefresh.storio.sqlite.queries.RawQuery;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.db_tables.TweetDBTable;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.db_tables.UserDBTable;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.TweetUiModel;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.UserUiModel;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class TweetUIStorIOSQLiteGetResolver extends DefaultGetResolver<TweetUiModel> {


    // lib hack change in StorIO v2.0.0.
    @NonNull
    private final ThreadLocal<StorIOSQLite> storIOSQLiteFromPerformGet = new ThreadLocal<StorIOSQLite>();


    @NonNull
    @Override
    public TweetUiModel mapFromCursor(@NonNull Cursor cursor) {
        final StorIOSQLite storIOSQLite = storIOSQLiteFromPerformGet.get();
        String userId = cursor.getString(cursor.getColumnIndexOrThrow(TweetDBTable.Entry.COLUMN_USER_ID));

        UserUiModel user = storIOSQLite
                .get()
                .object(UserUiModel.class)
                .withQuery(Query.builder()
                        .table(UserDBTable.Entry.TABLE_NAME)
                        .where(UserDBTable.Entry.COLUMN_USER_ID + " = ?")
                        .whereArgs(userId)
                        .build())
                .prepare()
                .executeAsBlocking();

        return new TweetUiModel(
                cursor.getString(cursor.getColumnIndexOrThrow(TweetDBTable.Entry.COLUMN_TWEET_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(TweetDBTable.Entry.COLUMN_TWEET_TEXT)),
                user,
                cursor.getLong(cursor.getColumnIndexOrThrow(TweetDBTable.Entry.COLUMN_CREATED_AT)),
                cursor.getInt(cursor.getColumnIndexOrThrow(TweetDBTable.Entry.COLUMN_RETWEETED_COUNT)),
                cursor.getInt(cursor.getColumnIndexOrThrow(TweetDBTable.Entry.COLUMN_IS_TWEET_SYNC)));

    }

    @NonNull
    @Override
    public Cursor performGet(@NonNull StorIOSQLite storIOSQLite, @NonNull RawQuery rawQuery) {
        storIOSQLiteFromPerformGet.set(storIOSQLite);
        return storIOSQLite.lowLevel().rawQuery(rawQuery);
    }

    @NonNull
    @Override
    public Cursor performGet(@NonNull StorIOSQLite storIOSQLite, @NonNull Query query) {
        storIOSQLiteFromPerformGet.set(storIOSQLite);
        return storIOSQLite.lowLevel().query(query);
    }
}
