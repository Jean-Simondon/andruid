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

    private InventoryViewModel minventoryViewModel;
    //    private FragmentActivity myContext;

    public InventoryFragment() {
        super(R.layout.fragment_inventory);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // ---------------------------------------  Lien avec le ViewModelProvider pour gérer les données injectés dans le XML ----------------------------------------------------

        // Le ViewModelProvider nous procure une référence vers notre InventoryViewModel
        // Le ViewModel contient les données propres à notre fragment, notamment la valeur des éléments du XML comme les chaines de caractères
        minventoryViewModel = new ViewModelProvider(this).get(InventoryViewModel.class);

        // ---------------------------------------  Récupération du layourt sous la forme d'une hiérarchie d'élément ----------------------------------------------------

        // On instancie un layout en XML dans un objet de type vue
        // comme le paramètre container est précisé, il s'agira de toute une hiérarchie d'élément
        View root = inflater.inflate(R.layout.fragment_inventory, container, false);

        // ---------------------------------------  FragmentManager : Objet pour gérer les fragments ----------------------------------------------------

        // On récupère le fragment manager qui nousn permettra, par exemple de passer à un autre fragment
        FragmentManager fragmentManager = getFragmentManager();
//        FragmentManager fragManager = myContext.getSupportFragmentManager();

        // ---------------------------------------  LIFECYCLEOWNER : Objet pour gérer les cycles de vie de fragments ----------------------------------------------------

//        LifecycleOwner lifecycleOwner = (LifecycleOwner) getLifecycle();

//        LifecycleOwner viewLifecycleOwner =  getViewLifecycleOwner();
//        LifecycleOwner viewllifecycleOwner2 = getViewLifecycleOwnerLiveData();
        // utile à observer un élément de la vue uniquement dans un cas spécifique,

        // Utile à placer un observer
        //        lifecycleOwner.status.observe()


        // ---------------------------------------  TEXT VIEW EXEMPLE ----------------------------------------------------

//        InventoryFragment fragment = (InventoryFragment) fragmentManager.findFragmentById(R.id.fragment_container);

        // dans cette hiérarchie, on récupère l'élément text_inventory
        final TextView textView = root.findViewById(R.id.text_inventory);

        // De notre ViewModel, on appel un getter, sur lequel on place un observer, qui revient à attendre un chagment de la valeur
        // À ce moment là, quand la valeur changera, on appliquera la valeur à l'élément textView dans le layout
        // Une manière de garder la valeur actualiser même quand elle change
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







