package com.andruidteam.andruid.ui;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Query;
import androidx.room.Room;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.db.AppDatabase;
import com.andruidteam.andruid.db.entity.Game;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;
import com.google.android.material.navigation.NavigationView;

public class PlayableCharacterActivity extends AppCompatActivity {

    public static final String INPUT_CHARACTER_ID = "input_character_id";

    private DrawerLayout mDrawerLayout;
    private AppBarConfiguration mAppBarConfiguration;
    private CharacterViewModel mCharacterViewModel; // À instancier pour l'avoir de manière globale dans l'appli côté Playable Character

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc); // récupération du layout de l'application

        // TODO en arrivant ici, il faut faire récupérer l'arguement dans l'intent qui nous vient du fragment CharacterPickintFragment et s'en servir pour instancier le Character dont les données nous suivront tout au long des fragments
        CharacterViewModel.Factory factory = new CharacterViewModel.Factory(getApplication(), getIntent().getExtras().getInt(INPUT_CHARACTER_ID));
//        mCharacterViewModel = new ViewModelProvider(this, factory).get(CharacterViewModel.class);

        mDrawerLayout = findViewById(R.id.drawer_layout_pc); // récupération du composent XML qui emglobe toute la navigation (drawer layout)
        setSupportActionBar(findViewById(R.id.toolbar_pc)); // la barre d'action au dessus des fragments (toolbar)

        NavigationView navigationView = findViewById(R.id.nav_view_pc); // récupération du panneau menu latérale (navigation view)

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.HomePcFragment, R.id.diceFragment, R.id.detailCaractereFragment, R.id.inventoryFragment, R.id.journalFragment, R.id.skillFragment, R.id.spellFragment)
                .setOpenableLayout(mDrawerLayout)
                .build(); // connexion des éléments du menu à la barre d'action pour voir s'afficher le nom du fragment courant

        // construction du Navigation Component, un framework gérant la navigation de fragment à fragment à l'aide des graph dans res > navigation,
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment_pc);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration); // connexion toolbar et navigation
        NavigationUI.setupWithNavController(navigationView, navController); // connexion panneau menu latéral avec navigation

    }

    // Mise en place du menu en haut à droite (settings)
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