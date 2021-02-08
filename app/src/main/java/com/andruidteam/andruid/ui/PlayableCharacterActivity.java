package com.andruidteam.andruid.ui;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Query;
import androidx.room.Room;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.db.AppDatabase;
import com.andruidteam.andruid.db.entity.GameEntity;
import com.google.android.material.navigation.NavigationView;

public class PlayableCharacterActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc);
        mDrawerLayout = findViewById(R.id.drawer_layout_pc);
        setSupportActionBar(findViewById(R.id.toolbar_pc));

        NavigationView navigationView = findViewById(R.id.nav_view_pc);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.HomePcFragment, R.id.diceFragment, R.id.detailCaractereFragment, R.id.inventoryFragment, R.id.journalFragment, R.id.skillFragment, R.id.spellFragment)
                .setOpenableLayout(mDrawerLayout)
                .build();

        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment_pc);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



        /**
         * Instance de la base de données
         */
        AppDatabase db = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class, "andruid-db")
                .build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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