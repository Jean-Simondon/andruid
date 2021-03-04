package com.andruidteam.andruid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.andruidteam.andruid.app.AndruidApp;
import com.andruidteam.andruid.app.DataRepository;
import com.andruidteam.andruid.db.entity.GameEntity;

import java.util.ArrayList;

public class GameListViewModel extends AndroidViewModel {

    public static final String TAG = "GameListViewModel";

    private final SavedStateHandle mSavedStateHandler;
    private final DataRepository mRepository;
    private static ArrayList<GameEntity> mGame;

    private MutableLiveData<ArrayList<GameEntity>> mObservableGames;

    public GameListViewModel(@NonNull Application application, @NonNull SavedStateHandle savedStateHandle) {
        super(application);
        mSavedStateHandler = savedStateHandle;
        mRepository = ((AndruidApp) application).getRepository();
        mGame = mRepository.getAllGames();
    }

    public ArrayList<GameEntity> getGames() {
        return mGame;
    }

    public GameEntity getGameById(int id) {
        return mRepository.getGameById(id);
    }

    public void addGame() {
        mRepository.createNewGame();
    }

    public LiveData<ArrayList<GameEntity>> getObservableGames() {
        if( mObservableGames == null ) {
            mObservableGames = new MutableLiveData<ArrayList<GameEntity>>();
            loadGames();
        }
        return mObservableGames;
    }

    public void loadGames() {
//        mObservableGames = mRepository.getAllGames();
    }



}
