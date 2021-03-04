package com.andruidteam.andruid.viewmodel;

import android.app.Application;

import com.andruidteam.andruid.app.AndruidApp;
import com.andruidteam.andruid.app.DataRepository;
import com.andruidteam.andruid.db.entity.CharacterEntity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

public class CharacterListViewModel extends AndroidViewModel {

    public static final String TAG = "CharacterListViewModel";

    private final SavedStateHandle mSavedStateHandler;
    private final DataRepository mRepository;
    private static ArrayList<CharacterEntity> mCharacters;

    private MutableLiveData<ArrayList<CharacterEntity>> mObservableCharacters;

    public CharacterListViewModel(@NonNull Application application, @NonNull SavedStateHandle savedStateHandle) {
        super(application);
        mSavedStateHandler = savedStateHandle;
        mRepository = ((AndruidApp) application).getRepository();
        mCharacters = mRepository.getAllCharacters();
    }

    public ArrayList<CharacterEntity> getCharacters() {
        return mCharacters;
    }

    public CharacterEntity getCharacterById(int id) {
        return mRepository.getCharacterById(id);
    }

    public void addCharacter() {
        mRepository.createNewCharacter();
    }






    public LiveData<ArrayList<CharacterEntity>> getObservableCharacters() {
        if( mObservableCharacters == null ) {
            mObservableCharacters = new MutableLiveData<ArrayList<CharacterEntity>>();
            loadCharacters();
        }
        return mObservableCharacters;
    }

    public void loadCharacters() {
//        mObservableCharacters = mRepository.getAllCharacters();
    }






}