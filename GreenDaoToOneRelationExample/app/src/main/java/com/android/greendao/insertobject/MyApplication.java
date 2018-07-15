package com.android.greendao.insertobject;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

/**
 * Created by shahulhameed on 14/07/2018.
 */

public class MyApplication extends Application {

    private DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "greeddao_toone_example");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
