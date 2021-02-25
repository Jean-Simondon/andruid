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

import com.andruidteam.andruid.ui.DungeonMasterActivity;
import com.andruidteam.andruid.util.IOnBackPressed;
import com.andruidteam.andruid.R;

public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";
    public View root;

    /**
     * Premier fragment de l'application
     * On commence ici, en connectant le layout correspondant à la classe HomeFragment
     */
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fragmentManager = getFragmentManager(); // le fragment Manager, qui permet d'ajouter, retirer ou remplacer des fragments (leur gestion en général)

        /**
         * Direction la partie de l'appli pour le Dungeon Master
         * Au clic, on remplace le fragment actuel (avec les 2 boutons), par celui qui permet de choisir ou créer une nouvelle partie
         */
        view.findViewById(R.id.toDMPicking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamePickingFragment fragment = new GamePickingFragment();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container_main, fragment, GamePickingFragment.TAG)
                        .commit();
            }
        });

        /**
         * Même chose que pour les games mais pour les personnages
         */
        view.findViewById(R.id.toPCPicking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharacterPickingFragment fragment = new CharacterPickingFragment();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container_main, fragment, CharacterPickingFragment.TAG)
                        .commit();
            }
        });

    }

//    @Override
//    public boolean onBackPressed() {
//        return true;
//    }

}
