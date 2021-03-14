package com.andruidteam.andruid.app;

import android.app.Application;
import android.util.Log;

import com.andruidteam.andruid.db.AppDatabase;

/**
 * Classe d'application Android, afin de travailler avec un Singleton
 */
public class AndruidApp extends Application {

    public static final String TAG = "AndruidApp";

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
        mAppExecutors = new AppExecutors();
    }

    public AppDatabase getDatabase() {
        Log.d(TAG, "getDatabase: ");
        return AppDatabase.getInstance(this, mAppExecutors);
    }

    public DataRepository getRepository() {
        Log.d(TAG, "getRepository: ");
        return DataRepository.getInstance(getDatabase());
    }

}
