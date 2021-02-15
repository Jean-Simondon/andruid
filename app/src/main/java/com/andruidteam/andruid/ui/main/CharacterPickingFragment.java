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
import androidx.fragment.app.FragmentManager;

import com.andruidteam.andruid.DataRepository;
import com.andruidteam.andruid.db.entity.Character;
import com.andruidteam.andruid.util.IOnBackPressed;
import com.andruidteam.andruid.R;
import com.andruidteam.andruid.ui.PlayableCharacterActivity;

import java.util.List;

public class CharacterPickingFragment extends Fragment implements IOnBackPressed {

    public static final String TAG = "CharacterPickingFragment";
    public static final String LIST_ALL_CHARACTER = "listAllCharacter";
    public View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.character_picking_fragment, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO récupérer tous les Character envoyé par arguments depuis le fragment précédent (HomeFragment) et les présenter dans le layout sous forme de liste de bouton

        // Ajouter un onclickListener et instancier complètement celui choisi pour qu'il soit en variable global, puis passer à l'activité PlayableCharacterActivity

        // 1) Récupération argument avec requireArguments().getList(LIST_ALL_CHARACTER)

        // 2) Les placer dans le layout sous la forme d'une liste de bouton

        // 3) placer un onclicklistener sur chacun d'entre eux présent ci dessus
        /*
        buttonCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Passer l'ID du Character choisis en argument dans l'intent
                Bundle bundle = new Bundle();
                bundle.putString(PlayableCharacterActivity.INPUT_CHARACTER_ID, l'id du character );
                Intent intent = new Intent(getActivity(), PlayableCharacterActivity.class);
                startActivity(intent);
            }
        });
        */

        // TODO Remplacer le code dans le listerner ci dessous pour qu'il crée un nouveau Character et l'ajoute à la liste au dessus plutôt que de renvoyer vers la prochaine activity
        // Piste : récupérer le DataRepository  et créer une fonction faisant un insert dans le CharacterDAO

        Button buttonToPC = view.findViewById(R.id.toPCActivity);
        buttonToPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getDatarepos.createNewCharacter();
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        /*
        FragmentManager fragmentManager = getFragmentManager();
        HomeFragment fragment = new HomeFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_main, fragment, HomeFragment.TAG)
                .commit();

         */
        return true;
    }

}
