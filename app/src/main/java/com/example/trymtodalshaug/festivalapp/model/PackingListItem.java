package com.example.trymtodalshaug.festivalapp.model;

import android.widget.CheckBox;

import java.io.Serializable;

import static android.R.attr.id;

/**
 * Created by Trym Todalshaug on 15/10/2017.
 */

public class PackingListItem implements Serializable {

    private long id;
    private CheckBox checkBox;
    private String name;

    public PackingListItem(long id, CheckBox checkBox, String name) {
        this.id = id;
        this.checkBox = checkBox;
        this.name = name;
    }

    public long getid() {
        return id;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
