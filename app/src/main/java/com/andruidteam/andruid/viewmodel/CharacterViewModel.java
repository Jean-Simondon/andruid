package com.andruidteam.andruid.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.andruidteam.andruid.app.AndruidApp;
import com.andruidteam.andruid.app.DataRepository;
import com.andruidteam.andruid.db.entity.CharacterEntity;

import java.util.ArrayList;

public class CharacterViewModel extends AndroidViewModel {

    public static final String TAG = "CharacterViewModel";

    private final CharacterEntity mCharacter;

    private final int mCharacterId;

    private ArrayList<String> mJournal;

    public CharacterViewModel(@NonNull Application application, DataRepository repository, final int characterId) {
        super(application);
        mCharacterId = characterId;
        mCharacter = repository.getCharacterById(mCharacterId);
        mJournal = new ArrayList<>();
    }

    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */
    public CharacterEntity getCharacter() {
        return mCharacter;
    }

    public ArrayList<String> getJournal () {
        return mJournal;
    }

    public void addToJournal(String note) {
        mJournal.add(note);
    }


    /**
     * A creator is used to inject the character ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the character ID can be passed in a public method.
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mCharacterId;

        private final DataRepository mRepository;

        public Factory(@NonNull Application application, @Nullable int characterId) {
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
