package com.andruidteam.andruid.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.andruidteam.andruid.model.Character;
import com.andruidteam.andruid.util.StringListMapConverter;

import java.util.HashMap;
import java.util.Map;


@Entity(tableName = "characters")
public class CharacterEntity implements Character {
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

    @TypeConverters(StringListMapConverter.class)
    @ColumnInfo(name = "spells")
    public Map<String, String> spells;

    @TypeConverters(StringListMapConverter.class)
    @ColumnInfo(name = "skills")
    public Map<String, String> skills;

    public Map<String, String> getSpells() {
        return spells;
    }

    public void addSpell(String spellKey, String spellName) {
        spells.put(spellKey, spellName);
    }

    public void removeSpell(String spellKey) {
        spells.remove(spellKey);
    }

    public Map<String, String> getSkills() {
        return skills;
    }

    public void addSkill(String skillKey, String skillName) {
        skills.put(skillKey, skillName);
    }

    public void removeSkill(String skillKey) {
        skills.remove(skillKey);
    }

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

    public void setId(int id) {
        this.id = id;
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

    public CharacterEntity() {
    }

    @Ignore
    public CharacterEntity(int id, String firstName, String lastName, String race, String classe, int level) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.race = race;
        this.classe = classe;
        this.level = level;
        this.spells = new HashMap<>();
        this.skills = new HashMap<>();
    }

    public CharacterEntity(CharacterEntity character) {
        this.id = character.getId();
        this.firstName = character.getFirstName();
        this.lastName = character.getLastName();
        this.race = character.getRace();
        this.classe = character.getClasse();
        this.level = character.getLevel();
        this.spells = character.getSpells();
        this.skills = character.getSkills();
    }

}