package com.example.androidnote.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_database")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "subtitle")
    public String subtitle;

    @ColumnInfo(name = "createDate")
    public String createDate;

    @ColumnInfo(name = "notes")
    public String notes;

    @ColumnInfo(name = "priority")
    public String priority;

}
