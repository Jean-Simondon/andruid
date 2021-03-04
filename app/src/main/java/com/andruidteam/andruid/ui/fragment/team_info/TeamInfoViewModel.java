package com.andruidteam.andruid.ui.fragment.team_info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TeamInfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TeamInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is team info fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}