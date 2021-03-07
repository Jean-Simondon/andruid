package com.andruidteam.andruid.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.andruidteam.andruid.db.entity.CharacterEntity;

import java.util.List;

@Dao
public interface CharacterDao {

    @Query("SELECT * FROM characters")
    List<CharacterEntity> loadAllCharacters();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CharacterEntity> characters);

    @Query("SELECT * FROM characters WHERE id = :characterIds")
    CharacterEntity loadCharacter(int characterIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CharacterEntity character);

}
