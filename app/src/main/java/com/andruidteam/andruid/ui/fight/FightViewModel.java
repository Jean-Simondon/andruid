package com.andruidteam.andruid.ui.fight;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FightViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FightViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is fight fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}