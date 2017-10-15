package com.example.trymtodalshaug.festivalapp.data;

import android.provider.BaseColumns;

/**
 * Created by Trym Todalshaug on 15/10/2017.
 */

public final class PackingListItemContract {

    private PackingListItemContract() {}

    public static class PackingListItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "packing_list_items";

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String IS_CHECKED = "is_checked";
    }
}
