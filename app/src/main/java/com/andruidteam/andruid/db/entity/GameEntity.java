package com.andruidteam.andruid.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.andruidteam.andruid.model.Game;

@Entity(tableName = "games")
public class GameEntity implements Game {

    @PrimaryKey
    private int id;
    private String name;
    private String description;

    @Override
    public int getId() {
        return 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GameEntity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public GameEntity(Game game) {
        this.id = game.getId();
        this.name = game.getName();
        this.description = game.getDescription();
    }

}
