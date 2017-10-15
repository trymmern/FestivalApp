package com.example.trymtodalshaug.festivalapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Trym Todalshaug on 15/10/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "festivalapp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "CREATE TABLE " + PackingListItemContract.PackingListItemEntry.TABLE_NAME + "("
                    + PackingListItemContract.PackingListItemEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + PackingListItemContract.PackingListItemEntry.NAME + " TEXT, "
                    + PackingListItemContract.PackingListItemEntry.IS_CHECKED + " INTEGER);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PackingListItemContract
                .PackingListItemEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
