package com.andruidteam.andruid.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.andruidteam.andruid.IOnBackPressed;
import com.andruidteam.andruid.R;
import com.andruidteam.andruid.controller.PlayableCharacterActivity;

public class CharacterPickingFragment extends Fragment implements IOnBackPressed {

    public static final String TAG = "CharacterPickingFragment";
    public View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.character_picking_fragment, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO récupérer tous les personnages de la bdd et les proposer ici sous leur nom

        /**
         * Un bouton pour chaque personnage
         */




        /**
         * Un bouton pour créer un nouveau personnage
         */
        Button buttonToPC = view.findViewById(R.id.toPCActivity);
        buttonToPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayableCharacterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

}
