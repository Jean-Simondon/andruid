package com.andruidteam.andruid.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.andruidteam.andruid.model.Game;

@Entity(tableName = "games")
public class GameEntity implements Game {

    @PrimaryKey
    public int id;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "description")
    public String description;


    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Ignore
    public GameEntity(int id, String description, String title) {
        this.id = id;
        this.description = description;
        this.title = title;
    }

    public GameEntity(GameEntity game) {
        this.id = game.getId();
        this.description = game.getDescription();
        this.title = game.getTitle();
    }


}
