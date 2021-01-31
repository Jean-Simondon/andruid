package com.andruidteam.andruid.controller;

import android.os.Bundle;
import android.view.Menu;
import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.andruidteam.andruid.R;
import com.google.android.material.navigation.NavigationView;

/**
 * Main activity for the PC part of the app
 * Holds the Navigation Host Fragment, Drawer, Toolbar etc.
 */
public class MainPcActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pc); // layout principal avec drawer, toolbar, fragment, et navigation view
        mDrawerLayout = findViewById(R.id.drawer_layout_pc); // drawer : container général de activity_main_pc
        setSupportActionBar(findViewById(R.id.toolbar_pc)); // la toolbar au dessus du fragment

        NavigationView navigationView = findViewById(R.id.nav_view_pc); // tout en bas de activity_main_pc, c'est le panneau latéral contenant le menu

        // On récupère chaque item du menu à l'aide de leur ID
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.HomePcFragment, R.id.diceFragment, R.id.detailCaractereFragment, R.id.inventoryFragment, R.id.journalFragment, R.id.skillFragment, R.id.spellFragment)
                .setOpenableLayout(mDrawerLayout)
                .build();

        // Acquisition du nav controller par FragmentManager > navHostFragment > navController
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment_pc);
        NavController navController = navHostFragment.getNavController();

        // On connecte le navController avec la toolbar, de manière à afficher le nom du bon menu, et à dynamiser le menu burger (ouvre le panneau ou fait un retour vers le fragment précédent)
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        // On connecte le navController avec le navigation View de manière à afficher les menu dans le panneau latéral
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_pc, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_pc);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}