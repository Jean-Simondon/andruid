package com.andruidteam.andruid.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.andruidteam.andruid.db.entity.Character;

import java.util.List;

@Dao
public interface CharacterDao {

    @Query("SELECT * FROM characters")
    LiveData<List<Character>> getAll();

    @Query("SELECT * FROM characters WHERE id IN (:characterIds)")
    LiveData<Character> getAllByIds(int characterIds);

    @Query("SELECT * FROM characters WHERE id = (:characterId)")
    LiveData<Character> getByID(int characterId);

    @Insert
    void insertAll(Character... characters);

    @Insert
    void insert(Character... characters);

    @Delete
    void delete(Character character);

}
