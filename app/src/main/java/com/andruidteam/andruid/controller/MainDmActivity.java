package com.andruidteam.andruid.controller;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.andruidteam.andruid.R;
import com.google.android.material.navigation.NavigationView;


/**
 * MainDmActivity sera la single activity au dessus de toutes les fonctionnalités de la branche Dungeon Master de l'application
 */
public class MainDmActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_dm);
        /**
         * Dans le layout activity_main_dm, on a l'architecture suviante :
         * DrawerLayout : contient tout le reste
         *      Toolbar : la barre d'outil en haut de page
         *      Fragment : la page remplacement à la demande depuis les liens du navigation view
         *      Navigation View : le menu apparaisant depuis la gauche
         */

        // On récupère le drawer, conteneur de tout le reste
        DrawerLayout drawer = findViewById(R.id.drawer_layout_dm);

        // Mise en place de de la barre d'outils au dessus du fragment
        setSupportActionBar(findViewById(R.id.toolbar_dm));

        // On récupère le NavigationView, conteneur du menu et de son header
        NavigationView navigationView = findViewById(R.id.nav_view_dm);

        // On récupère chaque item du menu à l'aide de leur ID pour que chaque menu soient considéré comme une destination de haut niveau
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home_dm, R.id.nav_codex, R.id.nav_dice, R.id.nav_script, R.id.nav_map, R.id.nav_team_info, R.id.nav_fight, R.id.nav_music)
                .setOpenableLayout(drawer)
                .build();

        // On récupère le fragment remplaçable
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_dm);
        // On le connecte à la barre d'outil (pour afficher le nom du fragment actuel)
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        // On le connecte au menu du navigation view pour obtenir le bon fragment quand on clic sur les liens
        NavigationUI.setupWithNavController(navigationView, navController);

        // Le menu est en place, les fragment aussi, et on a un fragment de démarrage qui est définit dans mobile_navigation_dm
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_dm, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_dm);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        // Pour revenir au choix de DM ou PC
        setResult(RESULT_OK);
        finish();
    }
}