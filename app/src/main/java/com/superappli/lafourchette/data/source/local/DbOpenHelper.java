package com.superappli.lafourchette.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.superappli.lafourchette.injection.ApplicationContext;

import javax.inject.Inject;

/**
 * Created by raphael on 04/12/2016.
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "resto.db";
    private static final int DATABASE_VERSION = 1;

    // TODO create and organize table statements and queries
    public static final String TABLE_RESTO = "Resto";
    private static final String RESTO_CREATE_TABLE = "CREATE TABLE Resto (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "name TEXT NOT NULL)";

    @Inject
    DbOpenHelper(@ApplicationContext Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(RESTO_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
