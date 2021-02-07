package com.andruidteam.andruid.ui.inventory;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.ui.journal.JournalFragment;

public class InventoryFragment extends Fragment implements LifecycleOwner {

    /**
     * Les Activity et Fragment ne doivent connaitre que les ViewModel
     * Activity et Fragment ne servent qu'à se brancher sur l'interface layout
     *
     */

    private InventoryViewModel minventoryViewModel;

    public InventoryFragment() {
        super(R.layout.fragment_inventory);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        minventoryViewModel = new ViewModelProvider(this).get(InventoryViewModel.class);

        View root = inflater.inflate(R.layout.fragment_inventory, container, false);
        FragmentManager fragmentManager = getFragmentManager();
        final TextView textView = root.findViewById(R.id.text_inventory);

        minventoryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // ---------------------------------------  BOUTON AJOUT NOUVEAU ITEM  ----------------------------------------------------


        final Button addNewItem = root.findViewById(R.id.searchForItemButton);

        addNewItem.setOnClickListener(new View.OnClickListener() {
            int example = 10;
            @Override
            public void onClick(View v) {
                NavDirections action = InventoryFragmentDirections.actionFragmentInventoryToBlankFragment();
                Navigation.findNavController(v).navigate(action);
            }
        });

        // ---------------------------------------  FIN ----------------------------------------------------

        return root;
    }


/*
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        // au moment où un fragment est ajouté à un fragmentManager et attaché à son activité hôte
        // le fragement est alors actif et le fragmentManager gère son cycle de vie
        // C'est le tout premier hook possible dans le temps
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        // Quand le fragment est reitré du Manager, et détaché de l'activité
        // findFragmentById() devient impossible à utiliser sur ce fragment
        // C'est le tout dernier hook appelable dans le temps
        super.onDetach();
    }

*/

}







