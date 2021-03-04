package com.andruidteam.andruid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.andruidteam.andruid.app.AndruidApp;
import com.andruidteam.andruid.app.DataRepository;
import com.andruidteam.andruid.db.entity.GameEntity;


public class GameViewModel extends AndroidViewModel {

    public static final String TAG = "GameViewModel";

    private final GameEntity mGame;

    private final int mGameId;

    public GameViewModel(@NonNull Application application, DataRepository repository, final int gameId) {
        super(application);
        mGameId = gameId;
        mGame = repository.getGame(mGameId);
    }

    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */
    public GameEntity getGame() {
        return mGame;
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
