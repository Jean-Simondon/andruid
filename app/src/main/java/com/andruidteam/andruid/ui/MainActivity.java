package com.andruidteam.andruid.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.andruidteam.andruid.util.IOnBackPressed;
import com.andruidteam.andruid.R;
import com.andruidteam.andruid.ui.main.HomeFragment;

import com.andruidteam.andruid.model.Character;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    /**
     * Tout commence ici, la Main activity, point de départ de l'application
     * Ceci dit, l'application a déjà appelé la class AndruidApp pour un singleton de l'application
     * qui elle-même a instancié le DataRepository et AppDatabase
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // la base
        setContentView(R.layout.activity_main); // layout qui ne contient qu'un fragment container (pour le remplir aussitôt après)

        /**
         * A peine commencé, on remplit le fragment container du layout par le fragment qui contient les 2 boutons pour choisir Dungeon Master ou Playable Character
         */
        if (savedInstanceState == null) {
            HomeFragment fragment = new HomeFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_main, fragment, HomeFragment.TAG) // la classe de ce fragment et tous ceux de la mainActivity sont dans ui > main
                    .commit();
            // Après ça, tous les fragements de la MainActyvity sont dans res > main.
        }

    }

    // Gestion du dépilement des fragments quand appuie sur retour arrière, sinon on sort de l'activité, donc de l'application plutôt que de revenir au fragment précédent
    @Override public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_main);
        if (!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPressed()) {
            super.onBackPressed();
        }
    }

}