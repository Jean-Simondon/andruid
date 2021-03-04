package com.andruidteam.andruid.app;

import com.andruidteam.andruid.db.AppDatabase;
import com.andruidteam.andruid.db.DataGenerator;
import com.andruidteam.andruid.db.entity.GameEntity;
import com.andruidteam.andruid.db.entity.CharacterEntity;

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

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        if(mCharacters == null) {
            mCharacters = DataGenerator.generateCharacters(); // on charge tous les personnages de la BDD (le data Generator en réalité)
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
