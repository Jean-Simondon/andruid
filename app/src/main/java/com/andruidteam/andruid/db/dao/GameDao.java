package com.andruidteam.andruid.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.andruidteam.andruid.db.entity.GameEntity;

import java.util.List;

@Dao
public interface GameDao {

    @Query("SELECT * FROM games")
    LiveData<List<GameEntity>> loadAllGame();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<GameEntity> games);

    @Query("select * from games where id = :gameId")
    LiveData<GameEntity> loadGame(int gameId);

    @Query("select * from games where id = :gameId")
    GameEntity loadGameSync(int gameId);

}
