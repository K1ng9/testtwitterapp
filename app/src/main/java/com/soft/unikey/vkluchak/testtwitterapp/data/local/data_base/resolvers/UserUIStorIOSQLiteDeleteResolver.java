package com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.resolvers;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.operations.delete.DefaultDeleteResolver;
import com.pushtorefresh.storio.sqlite.queries.DeleteQuery;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.db_tables.UserDBTable;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.UserUiModel;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class UserUIStorIOSQLiteDeleteResolver extends DefaultDeleteResolver<UserUiModel> {
    @NonNull
    @Override
    protected DeleteQuery mapToDeleteQuery(@NonNull UserUiModel object) {
        return DeleteQuery.builder()
                .table(UserDBTable.Entry.TABLE_NAME)
                .where(UserDBTable.Entry.COLUMN_USER_ID + " = ?")
                .whereArgs(object.getId())
                .build();

    }
}
