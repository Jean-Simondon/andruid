package com.andruidteam.andruid.ui.inventory;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

    // Un attribut, qui est une chaine de caractère
    private MutableLiveData<String> mText;

    // Le constructeur, ou on donne une valeur pour la première fois à notre chaine de caractère
    public InventoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is inventory fragment");
    }

    // un getter sur cette chaine de caractère
    public LiveData<String> getText() {
        return mText;
    }








}