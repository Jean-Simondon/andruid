package com.andruidteam.andruid.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import com.andruidteam.andruid.AndruidApp;
import com.andruidteam.andruid.DataRepository;
import com.andruidteam.andruid.db.entity.CharacterEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;

public class CharacterListViewModel extends AndroidViewModel {

    private final SavedStateHandle mSavedStateHandler;
    private final DataRepository mRepository;
    private ArrayList<CharacterEntity> mCharacters;

    public CharacterListViewModel(@NonNull Application application, @NonNull SavedStateHandle savedStateHandle) {
        super(application);
        mSavedStateHandler = savedStateHandle;
        mRepository = ((AndruidApp) application).getRepository();
        mCharacters = new ArrayList<>();
    }

    public ArrayList<CharacterEntity> getCharacters() {
        return mCharacters;
    }

    public void addCharacter()
    {
        mCharacters.add(mRepository.createNewCharacter());
    }

}