package com.example.trymtodalshaug.festivalapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trymtodalshaug.festivalapp.model.PackingListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trym Todalshaug on 15/10/2017.
 */

public class DataSource {

    private Context context;
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] LIST_ALL_COLUMNS = {
            PackingListItemContract.PackingListItemEntry.ID,
            PackingListItemContract.PackingListItemEntry.NAME,
            PackingListItemContract.PackingListItemEntry.IS_CHECKED
    };

    public DataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    // Opens the database
    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    // Closes the database
    public void close() {
        dbHelper.close();
    }

    // Insert an item into the database
    public void insertItem(PackingListItem item) {
        open();

        ContentValues values = new ContentValues();
        values.put(PackingListItemContract.PackingListItemEntry.NAME, item.getName());
        values.put(PackingListItemContract.PackingListItemEntry.IS_CHECKED, item.getIsChecked());

        db.insert(PackingListItemContract.PackingListItemEntry.TABLE_NAME, null, values);
        close();
    }

    // Update a packing list item
    public void updateItem(PackingListItem item) {
        open();

        ContentValues values = new ContentValues();

        values.put(PackingListItemContract.PackingListItemEntry.IS_CHECKED, item.getIsChecked());

        db.update(PackingListItemContract.PackingListItemEntry.TABLE_NAME, values,
                PackingListItemContract.PackingListItemEntry.ID + "= ?",
                new String[]{
                        String.valueOf(item.getId())
                });
        close();
    }

    // Delete an item from database
    public void deleteItem(PackingListItem item) {
        open();

        db.delete(PackingListItemContract.PackingListItemEntry.TABLE_NAME,
                PackingListItemContract.PackingListItemEntry.ID + " =?",
                new String[] {
                        String.valueOf(item.getId())
                });
        close();
    }

    // Retrieve all packing list items from the database
    public List<PackingListItem> getPackingListItems() {
        //Open connection to read only
        db = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT  " +
                PackingListItemContract.PackingListItemEntry.ID + ", " +
                PackingListItemContract.PackingListItemEntry.NAME + ", " +
                PackingListItemContract.PackingListItemEntry.IS_CHECKED + " " +
                "FROM " + PackingListItemContract.PackingListItemEntry.TABLE_NAME + ";";

        List<PackingListItem> packingListItems = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                PackingListItem pli = new PackingListItem();
                pli.setId(cursor.getInt(cursor.getColumnIndex(PackingListItemContract.PackingListItemEntry.ID)));
                pli.setName(cursor.getString(cursor.getColumnIndex(PackingListItemContract.PackingListItemEntry.NAME)));
                pli.setIsChecked(cursor.getInt(cursor.getColumnIndex(PackingListItemContract.PackingListItemEntry.IS_CHECKED)));
                packingListItems.add(pli);

            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return packingListItems;
    }
}
