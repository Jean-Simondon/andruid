package com.andruidteam.andruid;

import android.app.Application;
import com.andruidteam.andruid.db.AppDatabase;

/**
 * Android Application class. Used for accessing singletons.
 */
public class AndruidApp extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppExecutors = new AppExecutors();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this, mAppExecutors);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }

}
