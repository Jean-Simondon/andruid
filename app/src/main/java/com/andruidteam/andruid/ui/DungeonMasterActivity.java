package com.andruidteam.andruid.ui;

import android.os.Bundle;
import android.view.Menu;

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

public class DungeonMasterActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dm);
        mDrawerLayout = findViewById(R.id.drawer_layout_dm);
        setSupportActionBar(findViewById(R.id.toolbar_dm));

        NavigationView navigationView = findViewById(R.id.nav_view_dm);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home_dm, R.id.nav_codex, R.id.diceFragment, R.id.nav_script, R.id.nav_map, R.id.nav_team_info, R.id.nav_fight, R.id.nav_music)
                .setOpenableLayout(mDrawerLayout)
                .build();

        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment_dm);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_dm, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_dm);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}