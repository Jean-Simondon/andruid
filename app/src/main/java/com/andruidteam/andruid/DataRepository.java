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

    /**
     * Construit le singleton de DataRepository en instanciant la base de données et en chargeant directement les Game et Character, à retirer peut-être plus tard car pas besoin de tous
     */
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

    // Vérifie que le datarepository n'est pas déjà instancier, afin de travailler avec un singleton
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
     *
     * ICI !!!! C'est ici là dessous que l'on rajoute nos méthode pour RECUPERER des données sur la BASE DE DONNEES
     * On utilise l'attribut mDatabase (une instance de la base de données),
     * qui elle même utilise les classes GameDao et CharacterDAO pour appeler leur méthode
     * Si on veut rajouter des ATTRIBUTS, c'est dans DB > ENTITY
     */


    /**
     * Get the list of games from the database and get notified when the data changes.
     */
    public LiveData<List<Game>> getGames() {
        return mObservableGame;
    }

    /**
     * Récupérer un game en fonction de son ID
     */
    public LiveData<Game> loadGame(final int gameId) {
        return mDatabase.mGameDao().loadAllByIds(gameId);
    }

    /**
     * Get the list of characters from the database and get notified when the data changes.
     */
    public void createNewCharacter() {
        Character character = new Character();
        character.setFirstName("John");
        character.setLastName("Do");
        character.setRace("humain");
        character.setClasse("barde");
        character.setLevel(0);
        mDatabase.mCharacterDao().insert(character);
    }


    public LiveData<List<Character>> getCharacters() {
        return mObservableCharacters;
    }

    /**
     * Récupérer un character à l'aide d'un id
     */
    public LiveData<Character> loadCharacter(final int characterId) {
        return mDatabase.mCharacterDao().getAllByIds(characterId);
    }


}
