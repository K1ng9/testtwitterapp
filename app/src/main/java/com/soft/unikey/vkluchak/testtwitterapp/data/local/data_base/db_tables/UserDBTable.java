package com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.db_tables;

import android.provider.BaseColumns;
import android.support.annotation.NonNull;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class UserDBTable extends BaseDBObject {
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserDBTable.Entry.TABLE_NAME;

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserDBTable.Entry.TABLE_NAME + " (" +
                    UserDBTable.Entry._ID + " INTEGER PRIMARY KEY," +
                    UserDBTable.Entry.COLUMN_USER_ID + TEXT_TYPE + COMMA_SEP +
                    UserDBTable.Entry.COLUMN_USER_NAME + TEXT_TYPE + COMMA_SEP +
                    UserDBTable.Entry.COLUMN_USER_EMAIL + TEXT_TYPE + COMMA_SEP +
                    UserDBTable.Entry.COLUMN_USER_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    UserDBTable.Entry.COLUMN_PROFILE_IMAGE_URL + TEXT_TYPE +
                    " )";

    @NonNull
    @Override
    public String getTableName() {
        return UserDBTable.Entry.TABLE_NAME;
    }

    @NonNull
    @Override
    public String getId() {
        return UserDBTable.Entry.COLUMN_USER_ID;
    }

    /* Inner class that defines the table contents */
    public static abstract class Entry implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String COLUMN_USER_ID = "id";
        public static final String COLUMN_USER_NAME = "user_name";
        public static final String COLUMN_USER_EMAIL = "user_email";
        public static final String COLUMN_USER_DESCRIPTION = "description";
        public static final String COLUMN_PROFILE_IMAGE_URL = "profile_image_url";
    }
}
