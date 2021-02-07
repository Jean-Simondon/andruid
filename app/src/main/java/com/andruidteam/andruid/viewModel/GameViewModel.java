package com.andruidteam.andruid.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.andruidteam.andruid.repository.DataRepository;

public class GameViewModel extends AndroidViewModel {

//    private final LiveData<String> mFirstName;
//    private final LiveData<String> mLastName;
//    private final LiveData<String> mRace;
//    private final LiveData<String> mClasse;

    public GameViewModel(@NonNull Application application, DataRepository repository) {
        super(application);

/*        mFirstName = repository.loadFirstName();
        mLastName = repository.loadLastName();
        mRace = repository.loadRace();
        mClasse = repository.loadClasse();

 */
    }
/*
    public LiveData<String> getFirstName() {
        return mFirstName;
    }

    public LiveData<String> getLastName() {
        return mLastName;
    }

    public LiveData<String> getRace() {
        return mRace;
    }

    public LiveData<String> getClasse() {
        return mClasse;
    }

 */
}
