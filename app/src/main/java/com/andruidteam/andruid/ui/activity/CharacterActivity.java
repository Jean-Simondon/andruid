package com.andruidteam.andruid.ui.activity;

import android.os.Bundle;
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
import com.google.android.material.navigation.NavigationView;

public class CharacterActivity extends AppCompatActivity {

    public static final String TAG = "CharacterActivity";

    public static final String INPUT_CHARACTER_ID = "input_character_id";

    private DrawerLayout mDrawerLayout;

    private AppBarConfiguration mAppBarConfiguration;

    public CharacterViewModel mCharacterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc);

        // On instancie le viewModel à l'aide de l'ID que l'on reçoit de la précédente Activité en ayant cliqué sur un élément de la liste de character
        CharacterViewModel.Factory factory = new CharacterViewModel.Factory(getApplication(), getIntent().getExtras().getInt(INPUT_CHARACTER_ID));
        mCharacterViewModel = new ViewModelProvider(this, factory).get(CharacterViewModel.class);

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