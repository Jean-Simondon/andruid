package com.andruidteam.andruid.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "characters")
public class Character {

    @PrimaryKey
    public int id;
    @ColumnInfo(name = "firstName")
    public String firstName;
    @ColumnInfo(name = "lastName")
    public String lastName;
    @ColumnInfo(name = "race")
    public String race;
    @ColumnInfo(name = "classe")
    public String classe;
    @ColumnInfo(name = "level")
    public int level;

}