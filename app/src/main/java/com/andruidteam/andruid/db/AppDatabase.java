package com.andruidteam.andruid.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
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
import com.andruidteam.andruid.db.entity.GameEntity;
import com.andruidteam.andruid.db.entity.CharacterEntity;

import java.util.List;

@Database(entities = {GameEntity.class, CharacterEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sIntance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "andruid-db";

    public abstract GameDao mGameDao();

    public abstract CharacterDao mCharacterDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

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

                        // Generate the data for pre-population
                        AppDatabase database = AppDatabase.getInstance(appContext, executors);
//                           List<GameEntity> products = DataGenerator.generateGames();
//                            List<PlayableCharacterEntity> comments =
//                             DataGenerator.generateCommentsForGames(games);
//                            insertData(database, games, playableCharacters);

                            // notify that the database was created and it's ready to be used
                       database.setDatabaseCreated();
                        });
                    }
                })
//        .addMigrations(MIGRATION_1_2)
        .build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    private static void insertData(final AppDatabase database, final List<GameEntity> games, final List<CharacterEntity> characters) {
        database.runInTransaction(() -> {
            database.mGameDao().insertAll(games);
            database.mCharacterDao().insertAll(characters);
        });
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) { }
    };


}

    // at runtime, instance of :
    // Room.databaseBuilder
    // Room.inMemoryDatabaseBuilder

/**
 * Va déifnir la liste d'entitée et data à accéder dans la base de données
 * Va aussi être l'accès principale au connexion sous jacente
 *
 */

