package com.andruidteam.andruid.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.andruidteam.andruid.db.entity.GameEntity;

import java.util.List;

@Dao
public interface GameDao {

    @Query("SELECT * FROM games")
    LiveData<List<GameEntity>> getAll();

    @Query("SELECT * FROM games WHERE id IN (:gameIds)")
    LiveData<GameEntity> loadGame(int gameIds);







    @Query("SELECT * FROM games WHERE id = (:gameId)")
    LiveData<GameEntity> getByID(int gameId);

    @Query("SELECT * FROM games WHERE title LIKE :title LIMIT 1")
    LiveData<GameEntity> findByTitle(String title);

    @Insert
    void insertAll(GameEntity... games);

    @Insert
    void insert(GameEntity... games);

    @Delete
    void delete(GameEntity game);

}
