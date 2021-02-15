package com.andruidteam.andruid.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Ignore;
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

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRace() {
        return race;
    }

    public String getClasse() {
        return classe;
    }

    public int getLevel() {
        return level;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Character() {
    }

    @Ignore
    public Character(int id, String firstName, String lastName, String race, String classe, int level) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.race = race;
        this.classe = classe;
        this.level = level;
    }

    public Character(Character character) {
        this.id = character.getId();
        this.firstName = character.getFirstName();
        this.lastName = character.getLastName();
        this.race = character.getRace();
        this.classe = character.getClasse();
        this.level = character.getLevel();
    }
}