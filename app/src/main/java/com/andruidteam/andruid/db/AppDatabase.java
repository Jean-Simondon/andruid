package com.andruidteam.andruid.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.andruidteam.andruid.app.AppExecutors;
import com.andruidteam.andruid.db.dao.GameDao;
import com.andruidteam.andruid.db.dao.CharacterDao;
import com.andruidteam.andruid.db.entity.GameEntity;
import com.andruidteam.andruid.db.entity.CharacterEntity;
import com.andruidteam.andruid.util.StringListMapConverter;

import java.util.List;

@Database(entities = {GameEntity.class, CharacterEntity.class}, version = 2)
@TypeConverters(StringListMapConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sIntance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "andruid-db";

    public abstract GameDao gameDao();
    public abstract CharacterDao characterDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

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
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            addDelay();
                            AppDatabase database = AppDatabase.getInstance(appContext, executors);
                            List<CharacterEntity> characters = DataGenerator.generateCharacters();
                            insertData(database, characters);
                            database.setDatabaseCreated();
                        });
                    }
                })
              .build();
    }

    /**
     * Si on veut emplir la base de donnée
     * @param database
     * @param characters
     */
    private static void insertData(final AppDatabase database, final List<CharacterEntity> characters) {
        database.runInTransaction(() -> {
            database.characterDao().insertAll(characters);
        });
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * Fait passer la valeur de la création de base de données à vrai
     */
    private void setDatabaseCreated(){
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