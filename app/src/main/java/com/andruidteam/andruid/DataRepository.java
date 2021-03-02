package com.andruidteam.andruid;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.andruidteam.andruid.db.AppDatabase;
import com.andruidteam.andruid.db.entity.GameEntity;
import com.andruidteam.andruid.db.entity.CharacterEntity;
import com.andruidteam.andruid.model.Character;

import java.util.List;

/**
 * Repository handling the work with games and characters.
 */
public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    // ---------------- CHARACTER --------------------------------

    public CharacterEntity getCharacter(int id) {
        return mDatabase.characterDao().loadCharacter(id);
    }

    public List<CharacterEntity> getAllCharacters() {
        return mDatabase.characterDao().loadAllCharacters();
    }

    public CharacterEntity createNewCharacter() {
        CharacterEntity character = new CharacterEntity();
        character.setFirstName("drizzt");
        character.setLastName("do'urden");
        character.setClasse("clerc");
        character.setLevel(10);
        character.setRace("Elfe noir");
//        mDatabase.characterDao().insert(character);
        return character;
     }

    // ---------------- GAME --------------------------------

    public GameEntity getGame(int id) {
        return mDatabase.gameDao().loadGame(id);
    }

    public List<GameEntity> getAllGames() {
        return mDatabase.gameDao().loadAllGames();
    }

    public GameEntity createNewGames() {
        GameEntity game = new GameEntity();
        game.setTitle("Un titre");
        game.setDescription("Une description");
        mDatabase.gameDao().insert(game);
        return game;
    }
}
