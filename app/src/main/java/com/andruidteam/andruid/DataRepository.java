package com.andruidteam.andruid;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.andruidteam.andruid.db.AppDatabase;
import com.andruidteam.andruid.db.entity.Game;
import com.andruidteam.andruid.db.entity.Character;

import java.util.List;

/**
 * Repository handling the work with games and characters.
 */
public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;

    private MediatorLiveData<List<Game>> mObservableGame;
    private MediatorLiveData<List<Character>> mObservableCharacters;



    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObservableGame = new MediatorLiveData<>();
        mObservableCharacters = new MediatorLiveData<>();

        mObservableGame.addSource(mDatabase.mGameDao().getAll(),
                gameEntities -> {
                    if( mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableGame.postValue(gameEntities);
                    }
                });

        mObservableCharacters.addSource(mDatabase.mCharacterDao().getAll(),
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
    public LiveData<List<Game>> getGames() {
        return mObservableGame;
    }

    public LiveData<Game> loadGame(final int gameId) {
        return mDatabase.mGameDao().loadAllByIds(gameId);
    }

    /**
     * Get the list of characters from the database and get notified when the data changes.
     */
    public LiveData<List<Character>> getCharacters() {
        return mObservableCharacters;
    }

    public LiveData<Character> loadCharacter(final int characterId) {
        return mDatabase.mCharacterDao().getAllByIds(characterId);
    }





}
