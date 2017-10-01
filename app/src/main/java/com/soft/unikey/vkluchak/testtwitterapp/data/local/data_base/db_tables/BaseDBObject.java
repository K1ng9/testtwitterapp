package com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.db_tables;

import android.support.annotation.NonNull;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public abstract class BaseDBObject {
    protected static final String TEXT_TYPE = " TEXT";
    protected static final String INT_TYPE = " INTEGER";
    protected static final String COMMA_SEP = ",";

    @NonNull
    public abstract String getTableName();

    @NonNull
    public abstract String getId();
}
