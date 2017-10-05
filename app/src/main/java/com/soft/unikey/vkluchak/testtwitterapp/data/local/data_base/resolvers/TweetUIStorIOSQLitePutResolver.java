package com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.resolvers;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio.sqlite.queries.InsertQuery;
import com.pushtorefresh.storio.sqlite.queries.UpdateQuery;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.db_tables.TweetDBTable;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.db_tables.UserDBTable;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.TweetUiModel;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class TweetUIStorIOSQLitePutResolver extends DefaultPutResolver<TweetUiModel> {

    @NonNull
    @Override
    protected InsertQuery mapToInsertQuery(@NonNull TweetUiModel object) {
        return InsertQuery.builder()
                .table(TweetDBTable.Entry.TABLE_NAME)
                .build();
    }


    @NonNull
    @Override
    protected UpdateQuery mapToUpdateQuery(@NonNull TweetUiModel object) {
        return UpdateQuery.builder()
                .table(TweetDBTable.Entry.TABLE_NAME)
                .where(TweetDBTable.Entry.COLUMN_TWEET_TEXT + " = ?")
                .whereArgs(object.getText())
                .build();
    }

    @NonNull
    @Override
    protected ContentValues mapToContentValues(@NonNull TweetUiModel object) {
        ContentValues cv = new ContentValues();
        cv.put(TweetDBTable.Entry.COLUMN_TWEET_ID, object.getId());
        cv.put(TweetDBTable.Entry.COLUMN_CREATED_AT, object.getCreatedAt());
        cv.put(TweetDBTable.Entry.COLUMN_RETWEETED_COUNT, object.getRetweetCount());
        cv.put(TweetDBTable.Entry.COLUMN_TWEET_TEXT, object.getText());
        if(object.getUser() != null) {
            cv.put(TweetDBTable.Entry.COLUMN_USER_ID, object.getUser().getId());
        }
        if(object.isTweetSync()) {
            cv.put(TweetDBTable.Entry.COLUMN_IS_TWEET_SYNC, 1);
        }else {
            cv.put(TweetDBTable.Entry.COLUMN_IS_TWEET_SYNC, 0);
        }
        return cv;
    }
}
