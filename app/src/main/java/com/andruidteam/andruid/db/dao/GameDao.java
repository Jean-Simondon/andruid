package com.andruidteam.andruid.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.andruidteam.andruid.db.entity.CharacterEntity;
import com.andruidteam.andruid.db.entity.GameEntity;

import java.util.List;

@Dao
public interface GameDao {

    @Query("SELECT * FROM games")
    List<GameEntity> loadAllGames();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<GameEntity> games);

    @Query("SELECT * FROM games WHERE id = :gameIds")
    GameEntity loadGame(int gameIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GameEntity game);

}
