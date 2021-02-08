package com.andruidteam.andruid.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.andruidteam.andruid.db.entity.CharacterEntity;

import java.util.List;

public interface CharacterDao {

    /**
     * Data access object
     * Main component of room
     * Utile pour l'accès à la base de données plutôt que des queryBuilder ou direct queries
     *
     */
    @Query("SELECT * FROM characters")
    LiveData<List<CharacterEntity>> loadAllCharacters();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CharacterEntity> characters);

    @Query("select * from characters where id = :characterId")
    LiveData<CharacterEntity> loadCharacter(int characterId);

    @Query("select * from characters where id = :characterId")
    CharacterEntity loadCharacterSync(int characterId);

}
