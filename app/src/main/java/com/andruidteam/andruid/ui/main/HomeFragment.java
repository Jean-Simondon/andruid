package com.andruidteam.andruid.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.andruidteam.andruid.util.IOnBackPressed;
import com.andruidteam.andruid.R;

public class HomeFragment extends Fragment implements IOnBackPressed {

    public static final String TAG = "HomeFragment";
    public View root;

    /**
     * On commence ici, en connection le layout correspondant à la classe HomeFragment
     */
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragmentManager = getFragmentManager(); // le fragment Manager, qui permet d'ajouter, retirer ou remplacer des fragments (leur gestion en général)

        // Les 2 gros boutons du layout
        Button buttonToDM = view.findViewById(R.id.toDMPicking);
        Button buttonToPC = view.findViewById(R.id.toPCPicking);

        /**
         * Direction la partie de l'appli pour le Dungeon Master
         */
        buttonToDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Ici, récupérer tous les Games existant et les passer en argument au prochain fragment pour les présenter comme une liste
                // Piste : récupérer le DataRepository qui instancie la base de données (mDatabase) qui elle même contient le gameDAO (mGameDAO)
                // écrire la méthode getAllCharacter() dans le DataReposotiry, qui appellera mDatabase.mGameDao().getAll() et faire un setArgument sur l'instance du fragment ci dessous, tel que :
//                Bundle args = new Bundle();
//                args.putInt(LIST_ALL_GAME, datarepository().getAllGame());
                GamePickingFragment fragment = new GamePickingFragment();
                // fragment.setArguments(args);
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container_main, fragment, GamePickingFragment.TAG)
                        .commit();
            }
        });

        /**
         * Direction la partie de l'appli pour le playable Character
         */
        buttonToPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO même chose que pour les Games mais pour les characters
                CharacterPickingFragment fragment = new CharacterPickingFragment();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container_main, fragment, CharacterPickingFragment.TAG)
                        .commit();
            }
        });

    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}
