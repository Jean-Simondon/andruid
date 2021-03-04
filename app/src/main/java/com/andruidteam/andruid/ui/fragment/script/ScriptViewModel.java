package com.andruidteam.andruid.ui.fragment.script;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScriptViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ScriptViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is script fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}