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
import androidx.lifecycle.LiveData;

import com.andruidteam.andruid.db.entity.Game;
import com.andruidteam.andruid.util.IOnBackPressed;
import com.andruidteam.andruid.R;
import com.andruidteam.andruid.ui.DungeonMasterActivity;

import java.util.List;

public class GamePickingFragment extends Fragment implements IOnBackPressed {

    public static final String TAG = "GamePickingFragment";
    public View root;
    public LiveData<List<Game>> allGames;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.game_picking_fragment, container, false);

        // ici, on doit instancier tous les jeux depuis la base de données, et les


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        // TODO Ici, récupérer tous les Games existant et les passer en argument au prochain fragment pour les présenter comme une liste
        // Piste : récupérer le DataRepository qui instancie la base de données (mDatabase) qui elle même contient le gameDAO (mGameDAO)
        // écrire la méthode getAllCharacter() dans le DataReposotiry, qui appellera mDatabase.mGameDao().getAll() et faire un setArgument sur l'instance du fragment ci dessous, tel que :



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
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        /*        FragmentManager fragmentManager = getFragmentManager();
        HomeFragment fragment = new HomeFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_main, fragment, HomeFragment.TAG)
                .commit();
 */
        return true;
    }

}
