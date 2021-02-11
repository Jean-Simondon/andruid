package com.andruidteam.andruid.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.andruidteam.andruid.db.entity.Game;

import java.util.List;

@Dao
public interface GameDao {

    @Query("SELECT * FROM games")
    LiveData<List<Game>> getAll();

    @Query("SELECT * FROM games WHERE id IN (:gameIds)")
    LiveData<Game> loadAllByIds(int gameIds);

    @Query("SELECT * FROM games WHERE id = (:gameId)")
    LiveData<Game> getByID(int gameId);

    @Query("SELECT * FROM games WHERE title LIKE :title LIMIT 1")
    LiveData<Game> findByTitle(String title);

    @Insert
    void insertAll(Game... games);

    @Insert
    void insert(Game... games);

    @Delete
    void delete(Game game);

}
