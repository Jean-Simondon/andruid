package com.andruidteam.andruid.ui.home_pc;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomePcViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomePcViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home PC fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}