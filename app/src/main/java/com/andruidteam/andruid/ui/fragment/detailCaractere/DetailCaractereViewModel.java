package com.andruidteam.andruid.ui.fragment.detailCaractere;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class DetailCaractereViewModel extends ViewModel {

    private SavedStateHandle state;
    private DetailCaractereRepository mDetailCaractereRepository;

    private MutableLiveData<String> mFirstName;
    private MutableLiveData<String> mLastName;
    private MutableLiveData<Integer> mLevel;

    /**
     * Classes
     * Race
     * Historiques
     * Caractéristiques
     *
     */

    public DetailCaractereViewModel(SavedStateHandle savedStatedHandle) {
        state = savedStatedHandle;
        mDetailCaractereRepository = new DetailCaractereRepository();

//        mLastName = new MutableLiveData<String>();
//        mLevel = new MutableLiveData<Integer>();

        createOrGetFirstName();

        mLastName = state.get("lastName");
        mLevel = state.get("level");



    }



    public MutableLiveData<String> getFirstName() {
        if( mFirstName == null) {
            mFirstName = new MutableLiveData<String>();
            return state.get("firstName");
        }
        return mFirstName;
    }


    public MutableLiveData<String> createOrGetFirstName()
    {
        mFirstName.setValue(state.get("firstName"));;
        if( mFirstName == null ) {
            mFirstName = new MutableLiveData<String>();
        }
        return mFirstName;
    }


    public void setFirstName(String firstName)
    {
        if( mFirstName == null) {
            mFirstName = new MutableLiveData<String>(firstName);
        }
        state.set("firstName", firstName);
    }


}