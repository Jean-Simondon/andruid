package com.andruidteam.andruid.ui.fragment.dice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DiceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DiceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Dice fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}