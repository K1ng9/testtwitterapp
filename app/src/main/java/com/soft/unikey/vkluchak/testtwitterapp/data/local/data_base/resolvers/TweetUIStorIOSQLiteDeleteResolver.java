package com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.resolvers;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.operations.delete.DefaultDeleteResolver;
import com.pushtorefresh.storio.sqlite.queries.DeleteQuery;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.db_tables.TweetDBTable;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.TweetUiModel;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class TweetUIStorIOSQLiteDeleteResolver extends DefaultDeleteResolver<TweetUiModel> {
    @NonNull
    @Override
    protected DeleteQuery mapToDeleteQuery(@NonNull TweetUiModel object) {
        return DeleteQuery.builder()
                .table(TweetDBTable.Entry.TABLE_NAME)
                .where(TweetDBTable.Entry.COLUMN_TWEET_ID + " = ?")
                .whereArgs(object.getId())
                .build();

    }
}
