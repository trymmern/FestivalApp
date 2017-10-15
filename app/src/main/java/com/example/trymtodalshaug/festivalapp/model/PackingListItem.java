package com.example.trymtodalshaug.festivalapp.model;

import android.widget.CheckBox;

import java.io.Serializable;

import static android.R.attr.id;

/**
 * Created by Trym Todalshaug on 15/10/2017.
 */

public class PackingListItem implements Serializable {

    private int id;
    private String name;
    private int isChecked;

    public PackingListItem(String name, int isChecked) {
        this.name = name;
        this.isChecked = isChecked;
    }

    public PackingListItem() {
        // Empty constructor
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
