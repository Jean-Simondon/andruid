package com.andruidteam.andruid.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.andruidteam.andruid.AppExecutors;
import com.andruidteam.andruid.db.dao.GameDao;
import com.andruidteam.andruid.db.dao.CharacterDao;
import com.andruidteam.andruid.db.entity.Game;
import com.andruidteam.andruid.db.entity.Character;

import java.util.List;

@Database(entities = {Game.class, Character.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sIntance;
    public static final String DATABASE_NAME = "andruid-db";

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public abstract GameDao mGameDao();
    public abstract CharacterDao mCharacterDao();

    /**
     * Si l'instance de la base de données n'exite pas encore, on la crée et on la renvoie dans sInstance
     * Et notifie la classe que l'instance est créée
     * @param context
     * @param executors
     * @return une instance singleton de la base de données
     */
    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if(sIntance == null) {
            synchronized (AppDatabase.class) {
                if( sIntance == null ) {
                    sIntance = buildDatabase(context.getApplicationContext(), executors);
                    sIntance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sIntance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static AppDatabase buildDatabase(final Context appContext, final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
              .build();
    }


    /**
     * Si on veut emplir la base de donnée
     * @param database
     * @param games
     * @param characters
     */
    /*
    private static void insertData(final AppDatabase database, final List<Game> games, final List<Character> characters) {
        database.runInTransaction(() -> {
            database.mGameDao().insertAll(games);
            database.mCharacterDao().insertAll(characters);
        });
    }

     */






    /** -

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    /**
     * Fait passer la valeur de la création de base de données à vrai
     */
    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }


    /**
     * Vérifier que la base de données est créée
     * @return
     */
    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

}