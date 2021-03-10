package com.andruidteam.andruid.app;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.andruidteam.andruid.db.AppDatabase;
import com.andruidteam.andruid.db.DataGenerator;
import com.andruidteam.andruid.db.entity.GameEntity;
import com.andruidteam.andruid.db.entity.CharacterEntity;
import com.andruidteam.andruid.model.Game;
import com.andruidteam.andruid.rds.RequestQueueSingleton;
import com.andruidteam.andruid.rds.Requests;

import org.json.JSONArray;
import org.json.JSONObject;

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
            Log.d(TAG, "DataRepository: chargement des characters");
            mCharacters = DataGenerator.generateCharacters(); // on charge tous les personnages de la BDD (le data Generator en réalité)
        }
        if(mGames == null) {
            Log.d(TAG, "DataRepository: chargement des games");
            mGames = DataGenerator.generateGames(); // on charge tous les games de la BDD (le data Generator en réalité)
        }
    }

    public static DataRepository getInstance(final AppDatabase database) {
        Log.d(TAG, "getInstance: de repository");
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
        Log.d(TAG, "getCharacterById: ");
        return mCharacters.get(id - 1); // - 1 pour s'accorder avec la taille de la lsite comme les ID commencent à 1
    }

    public ArrayList<CharacterEntity> getAllCharacters() {
        Log.d(TAG, "getAllCharacters: ");
        return mCharacters;
    }

    public void createNewCharacter() {
        Log.d(TAG, "createNewCharacter: ");
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
        Log.d(TAG, "getGameById: ");
        return mGames.get(id - 1);
    }

    public ArrayList<GameEntity> getAllGames() {
        Log.d(TAG, "getAllGames: ");
        return mGames;
    }

    public void createNewGame() {
        Log.d(TAG, "createNewGame: ");
        mGames.add(new GameEntity(
                mGames.size() + 1,
                "Un titre",
                "Une description"
        ));
    }

    // ---------------- API CALLS --------------------------------

    public void doGETJsonObject(String url, Response.Listener<JSONObject> responseListener, Context context) {
        RequestQueue queue = RequestQueueSingleton.getInstance(context);
        JsonObjectRequest req = Requests.GETJsonObject(url, responseListener);
        queue.add(req);
    }

    public void doGETJsonArray(String url, Response.Listener<JSONArray> responseListener, Context context) {
        RequestQueue queue = RequestQueueSingleton.getInstance(context);
        JsonArrayRequest req = Requests.GETJsonArray(url, responseListener);
        queue.add(req);
    }

}
