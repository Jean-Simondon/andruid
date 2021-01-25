package com.andruidteam.andruid.ui.home_dm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeDmViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeDmViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home DM fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}