package com.andruidteam.andruid.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.andruidteam.andruid.IOnBackPressed;
import com.andruidteam.andruid.R;
import com.andruidteam.andruid.controller.DungeonMasterActivity;

public class GamePickingFragment extends Fragment implements IOnBackPressed {

    public static final String TAG = "GamePickingFragment";
    public View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.game_picking_fragment, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO récupérer toutes les parties dans la bdd et les proposer ici sous leur nom


        /**
         * Un bouton pour chaque partie existante
         */




        /**
         * Un bouton pour créer une nouvelle partie
         */
        Button buttonToDM = view.findViewById(R.id.toDMActivity);
        buttonToDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DungeonMasterActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

}
