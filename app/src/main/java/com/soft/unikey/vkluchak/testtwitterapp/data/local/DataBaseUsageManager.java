package com.soft.unikey.vkluchak.testtwitterapp.data.local;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.Query;
import com.soft.unikey.vkluchak.testtwitterapp.data.local.data_base.db_tables.TweetDBTable;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.TweetUiModel;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class DataBaseUsageManager {

    private final StorIOSQLite storIOSQLite;

    @Inject
    DataBaseUsageManager(StorIOSQLite storIOSQLite){
        this.storIOSQLite = storIOSQLite;
    }


    public Observable<List<TweetUiModel>> getAmpTodayFiles() {
        return storIOSQLite
                .get()
                .listOfObjects(TweetUiModel.class)
                .withQuery(Query.builder().table(TweetDBTable.Entry.TABLE_NAME).build())
                .prepare()
                .asRxObservable();
    }

}
