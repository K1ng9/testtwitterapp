package com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.db_tables;

import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class TweetDBTable extends BaseDBObject {
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TweetDBTable.Entry.TABLE_NAME;

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TweetDBTable.Entry.TABLE_NAME + " (" +
                    TweetDBTable.Entry._ID + " INTEGER PRIMARY KEY," +
                    TweetDBTable.Entry.COLUMN_TWEET_ID + TEXT_TYPE + COMMA_SEP +
                    TweetDBTable.Entry.COLUMN_TWEET_TEXT + TEXT_TYPE + COMMA_SEP +
                    TweetDBTable.Entry.COLUMN_USER_ID + TEXT_TYPE + COMMA_SEP +
                    TweetDBTable.Entry.COLUMN_CREATED_AT + INT_TYPE + COMMA_SEP +
                    TweetDBTable.Entry.COLUMN_RETWEETED_COUNT + INT_TYPE + COMMA_SEP +
                    TweetDBTable.Entry.COLUMN_IS_TWEET_SYNC + INT_TYPE +
                    " )";

    @NonNull
    @Override
    public String getTableName() {
        return TweetDBTable.Entry.TABLE_NAME;
    }

    @NonNull
    @Override
    public String getId() {
        return Entry.COLUMN_TWEET_ID;
    }

    /* Inner class that defines the table contents */
    public static abstract class Entry implements BaseColumns {
        public static final String TABLE_NAME = "Tweet";
        public static final String COLUMN_TWEET_ID = "id";
        public static final String COLUMN_TWEET_TEXT = "text";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_CREATED_AT = "created_at";
        public static final String COLUMN_RETWEETED_COUNT = "retweeted_count";
        public static final String COLUMN_IS_TWEET_SYNC = "is_tweet_sync";
    }
}
