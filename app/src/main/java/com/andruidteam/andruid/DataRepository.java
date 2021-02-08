package com.andruidteam.andruid;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.andruidteam.andruid.db.AppDatabase;
import com.andruidteam.andruid.db.entity.GameEntity;
import com.andruidteam.andruid.db.entity.CharacterEntity;

import java.util.List;

/**
 * Repository handling the work with games and characters.
 */
public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;

    private MediatorLiveData<List<GameEntity>> mObservableGame;
    private MediatorLiveData<List<CharacterEntity>> mObservableCharacters;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObservableGame = new MediatorLiveData<>();
        mObservableCharacters = new MediatorLiveData<>();

        mObservableGame.addSource(mDatabase.mGameDao().loadAllGame(),
                gameEntities -> {
                    if( mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableGame.postValue(gameEntities);
                    }
                });

        mObservableCharacters.addSource(mDatabase.mCharacterDao().loadAllCharacters(),
                characterEntities -> {
                    if( mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableCharacters.postValue(characterEntities);
                    }
                });

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

    /**
     * Get the list of games from the database and get notified when the data changes.
     */
    public LiveData<List<GameEntity>> getGames() {
        return mObservableGame;
    }

    public LiveData<GameEntity> loadGame(final int gameId) {
        return mDatabase.mGameDao().loadGame(gameId);
    }

    /**
     * Get the list of characters from the database and get notified when the data changes.
     */
    public LiveData<List<CharacterEntity>> getCharacter() {
        return mObservableCharacters;
    }

    public LiveData<CharacterEntity> loadCharacter(final int characterId) {
        return mDatabase.mCharacterDao().loadCharacter(characterId);
    }


}
