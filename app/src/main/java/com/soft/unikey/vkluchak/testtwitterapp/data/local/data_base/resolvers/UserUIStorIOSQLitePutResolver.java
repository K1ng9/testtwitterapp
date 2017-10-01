package com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.resolvers;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio.sqlite.queries.InsertQuery;
import com.pushtorefresh.storio.sqlite.queries.UpdateQuery;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.db_tables.TweetDBTable;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.db_tables.UserDBTable;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.UserUiModel;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class UserUIStorIOSQLitePutResolver extends DefaultPutResolver<UserUiModel> {
    @NonNull
    @Override
    protected InsertQuery mapToInsertQuery(@NonNull UserUiModel object) {
        return InsertQuery.builder()
                .table(UserDBTable.Entry.TABLE_NAME)
                .build();
    }

    @NonNull
    @Override
    protected UpdateQuery mapToUpdateQuery(@NonNull UserUiModel object) {
        return UpdateQuery.builder()
                .table(UserDBTable.Entry.TABLE_NAME)
                .where(UserDBTable.Entry.COLUMN_USER_ID + " = ?")
                .whereArgs(object.getId())
                .build();
    }

    @NonNull
    @Override
    protected ContentValues mapToContentValues(@NonNull UserUiModel object) {
        ContentValues cv = new ContentValues();
        cv.put(UserDBTable.Entry.COLUMN_USER_ID, object.getId());
        cv.put(UserDBTable.Entry.COLUMN_USER_NAME, object.getName());
        cv.put(UserDBTable.Entry.COLUMN_USER_DESCRIPTION, object.getUserDescription());
        cv.put(UserDBTable.Entry.COLUMN_USER_EMAIL, object.getUserEmail());
        cv.put(UserDBTable.Entry.COLUMN_PROFILE_IMAGE_URL, object.getProfileImageUrl());
        return cv;
    }
}
