package com.andruidteam.andruid.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;
import com.andruidteam.andruid.viewmodel.GameViewModel;
import com.google.android.material.navigation.NavigationView;

public class DungMasterActivity extends AppCompatActivity {

    public static final String TAG = "DungeonMasterActivity";

    public static final String INPUT_GAME_ID = "input_game_id";

    private DrawerLayout mDrawerLayout;

    private AppBarConfiguration mAppBarConfiguration;

    public GameViewModel mGameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dm);

        GameViewModel.Factory factory = new GameViewModel.Factory(getApplication(), getIntent().getExtras().getInt(INPUT_GAME_ID));
        mGameViewModel = new ViewModelProvider(this, factory).get(GameViewModel.class);

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
        Log.d(TAG, "onCreateOptionsMenu: ");
        getMenuInflater().inflate(R.menu.main_dm, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.d(TAG, "onSupportNavigateUp: ");
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_dm);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}