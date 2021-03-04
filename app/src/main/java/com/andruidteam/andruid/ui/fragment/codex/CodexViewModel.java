package com.andruidteam.andruid.ui.fragment.codex;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CodexViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CodexViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is codex fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}