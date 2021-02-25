package com.andruidteam.andruid.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.andruidteam.andruid.db.entity.CharacterEntity;

import java.util.List;

@Dao
public interface CharacterDao {

    @Query("SELECT * FROM characters")
    LiveData<List<CharacterEntity>> getAll();

    @Query("SELECT * FROM characters WHERE id IN (:characterIds)")
    LiveData<CharacterEntity> loadCharacter(int characterIds);







    @Query("SELECT * FROM characters WHERE id = (:characterId)")
    LiveData<CharacterEntity> getByID(int characterId);

    @Query("SELECT id, firstName, lastName FROM characters")
    LiveData<List<CharacterEntity>> getOverview();

    @Insert
    void insertAll(CharacterEntity... characters);

    @Insert
    void insert(CharacterEntity... characters);

    @Delete
    void delete(CharacterEntity character);

}
