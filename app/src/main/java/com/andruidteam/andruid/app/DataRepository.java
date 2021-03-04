package com.andruidteam.andruid.app;

import com.andruidteam.andruid.db.AppDatabase;
import com.andruidteam.andruid.db.DataGenerator;
import com.andruidteam.andruid.db.entity.GameEntity;
import com.andruidteam.andruid.db.entity.CharacterEntity;
import com.andruidteam.andruid.model.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository handling the work with games and characters.
 */
public class DataRepository {

    public static final String TAG = "DataRepository";

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;

    private static ArrayList<CharacterEntity> mCharacters;

    private static ArrayList<GameEntity> mGames;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        if(mCharacters == null) {
            mCharacters = DataGenerator.generateCharacters(); // on charge tous les personnages de la BDD (le data Generator en réalité)
        }
        if(mCharacters == null) {
            mGames = DataGenerator.generateGames(); // on charge tous les games de la BDD (le data Generator en réalité)
        }
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

    public CharacterEntity getCharacterById(int id) {
        return mCharacters.get(id - 1); // - 1 pour s'accorder avec la taille de la lsite comme les ID commencent à 1
    }

    public ArrayList<CharacterEntity> getAllCharacters() {
        return mCharacters;
    }

    public void createNewCharacter() {
        mCharacters.add(new CharacterEntity(
                mCharacters.size() + 1,
                "FirstName",
                "LastName",
                "Ma classe",
                "Humain",
                0
        ));
     }

    // ---------------- GAME --------------------------------

    public GameEntity getGameById(int id) {
        return mGames.get(id - 1);
    }

    public ArrayList<GameEntity> getAllGames() {
        return mGames;
    }

    public void createNewGame() {
        mGames.add(new GameEntity(
                mGames.size() + 1,
                "Un titre",
                "Une description"
        ));
    }

}
