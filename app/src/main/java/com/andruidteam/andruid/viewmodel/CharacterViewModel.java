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
import com.andruidteam.andruid.db.entity.Character;


public class CharacterViewModel extends AndroidViewModel {

    private final LiveData<Character> mObservableCharacter;

    private final int mCharacterId;

    public CharacterViewModel(@NonNull Application application, DataRepository repository, final int characterId) {
        super(application);
        mCharacterId = characterId;

        mObservableCharacter = repository.loadCharacter(mCharacterId);
    }

    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */
    public LiveData<Character> getCharacter() {
        return mObservableCharacter;
    }

    public String getFirstName() {
        return mObservableCharacter.getValue().firstName;
    }

    public String getLastName() {
        return mObservableCharacter.getValue().lastName;
    }

    public String getClasse() {
        return mObservableCharacter.getValue().classe;
    }

    public String getRace() {
        return mObservableCharacter.getValue().race;
    }

    public int getLevel() {
        return mObservableCharacter.getValue().level;
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

        private final int mCharacterId;

        private final DataRepository mRepository;

        public Factory(@NonNull Application application, int characterId) {
            mApplication = application;
            mCharacterId = characterId;
            mRepository = ((AndruidApp) application).getRepository();
        }

        @SuppressWarnings("unchecked")
        @Override
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new CharacterViewModel(mApplication, mRepository, mCharacterId);
        }
    }

}
