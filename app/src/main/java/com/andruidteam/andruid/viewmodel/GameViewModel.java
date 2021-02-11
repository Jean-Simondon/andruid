package com.andruidteam.andruid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.andruidteam.andruid.AndruidApp;
import com.andruidteam.andruid.DataRepository;
import com.andruidteam.andruid.db.entity.Game;


public class GameViewModel extends AndroidViewModel {

    private final LiveData<Game> mObservableGame;

    private final int mGameId;

    public GameViewModel(@NonNull Application application, DataRepository repository, final int gameId) {
        super(application);
        mGameId = gameId;

        mObservableGame = repository.loadGame(mGameId);
    }

    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */
    public LiveData<Game> getGame() {
        return mObservableGame;
    }

    /**
     * A creator is used to inject the game ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mGameId;

        private final DataRepository mRepository;

        public Factory(@NonNull Application application, int gameId) {
            mApplication = application;
            mGameId = gameId;
            mRepository = ((AndruidApp) application).getRepository();
        }

        @SuppressWarnings("unchecked")
        @Override
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new GameViewModel(mApplication, mRepository, mGameId);
        }
    }

}
