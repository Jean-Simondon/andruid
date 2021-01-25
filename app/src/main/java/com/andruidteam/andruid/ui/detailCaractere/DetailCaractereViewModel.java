package com.andruidteam.andruid.ui.detailCaractere;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DetailCaractereViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DetailCaractereViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is detail caractere fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}