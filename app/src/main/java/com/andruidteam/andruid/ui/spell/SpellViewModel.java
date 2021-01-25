package com.andruidteam.andruid.ui.spell;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SpellViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SpellViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is spell fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}