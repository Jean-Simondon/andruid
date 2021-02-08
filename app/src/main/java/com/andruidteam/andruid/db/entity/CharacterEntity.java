package com.andruidteam.andruid.db.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.andruidteam.andruid.model.Character;

@Entity(tableName = "characters")
public class CharacterEntity implements Character {

    @PrimaryKey
    private int id;
    private String firstName;
    private String lastName;
    private String race;
    private String classe;
    private int level;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getRace() {
        return this.race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Override
    public String getClasse() {
        return this.classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Ignore
    public CharacterEntity(int id, String firstName, String lastname, String race, String classe, int level) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastname;
        this.race = race;
        this.classe = classe;
        this.level = level;
    }

    public CharacterEntity(Character pc) {
        this.id = pc.getId();
        this.firstName = pc.getFirstName();
        this.lastName = pc.getLastName();
        this.level = pc.getLevel();
        this.classe = pc.getClasse();
        this.race = pc.getRace();
    }
}


/**
 * This annotation marks a class as a database row
 * Devra être référencé dans  Database#entities array
 */