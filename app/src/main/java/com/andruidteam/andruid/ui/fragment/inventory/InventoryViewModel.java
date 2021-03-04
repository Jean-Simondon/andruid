package com.andruidteam.andruid.ui.fragment.inventory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * Cette classe, ViewModel, est utile afin de sauvegarder
 * des données propre à l'UI jusqu'au onDestroy()
 * ainsi qu'à communiquer en temps réel entre les fragments présent sur le même écran
 * Il gère aussi les données de l'UI.
 * Mais il doit jamais détenir des références propres à un autre niveau de hiérarchie (l'activité au dessuss
 * ou bien des fragments à venir)
 * Exemple : Très utile pour restaurer les données lors du changement d'orientation sans qu'elles ne soient réinitialiser
 */

public class InventoryViewModel extends ViewModel {

    /**
     * Le ViewModel garde toutes les données instancié, plutôt que ce soit les fragments et activity sinon on les perds lors d'une rotation
     * Le ViewModel se reposent sur le Repository pour récupérer les données
     */

    /**
     * Contient les LiveData, notion importante
     */
    private SavedStateHandle state;
    public LiveData<List<String>> filteredData;
    private InventoryRepository mInventoryRepository;
    private MutableLiveData<String> mText;

    /**
     * @param savedStatedHandle is a key/Value map dont la clef est le nom de la variable, afin de retrouver les valeurs et de les instancier à nouveau
     *      car les valeurs persiste ainsi après que le processus soit tué par le système et reste disponible dans l'objet
     */

    public InventoryViewModel(SavedStateHandle savedStatedHandle) {

        state = savedStatedHandle;

        mText = new MutableLiveData<>();
        mText = state.get("ma_variable");
//        mText.setValue("This is inventory fragment");
    }

    // un getter sur cette chaine de caractère
    public LiveData<String> getText() {
        return mText;
    }



/**
 * Documentation de SavedStateHandle
 * https://developer.android.com/topic/libraries/architecture/viewmodel-savedstate#java
 */




}