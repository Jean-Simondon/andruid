package com.andruidteam.andruid.controller;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.andruidteam.andruid.R;
import com.google.android.material.navigation.NavigationView;

public class MainPcActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pc);

        Toolbar toolbar = findViewById(R.id.toolbar_pc);
        setSupportActionBar(toolbar);

        // On récupère le drawer, conteneur du NavigationView
        DrawerLayout drawer = findViewById(R.id.drawer_layout_pc);
        // On récupère le NavigationView, conteneur du menu
        NavigationView navigationView = findViewById(R.id.nav_view_pc);

        // On récupère chaque item du menu à l'aide de leur ID pour que chaque menu soient considéré comme une destination de haut niveau
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home_pc, R.id.nav_dice, R.id.nav_detail_caractere, R.id.nav_inventory, R.id.nav_journal, R.id.nav_skill, R.id.nav_spell)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_pc);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
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

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }
}