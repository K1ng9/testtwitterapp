package com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.resolvers;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.operations.get.DefaultGetResolver;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.db_tables.TweetDBTable;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.db_tables.UserDBTable;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.UserUiModel;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class UserUIStorIOSQLiteGetResolver extends DefaultGetResolver<UserUiModel> {
    @NonNull
    @Override
    public UserUiModel mapFromCursor(@NonNull Cursor cursor) {
        return new UserUiModel(
                cursor.getString(cursor.getColumnIndexOrThrow(UserDBTable.Entry.COLUMN_USER_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserDBTable.Entry.COLUMN_USER_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserDBTable.Entry.COLUMN_USER_EMAIL)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserDBTable.Entry.COLUMN_USER_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserDBTable.Entry.COLUMN_PROFILE_IMAGE_URL))
        );
    }
}
