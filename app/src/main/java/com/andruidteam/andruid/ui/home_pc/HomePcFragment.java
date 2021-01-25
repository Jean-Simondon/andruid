package com.andruidteam.andruid.ui.home_pc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.andruidteam.andruid.R;

public class HomePcFragment extends Fragment {

    private HomePcViewModel mHomePcViewModel;

    // La méthode onCreateView est appelé quand le Fragment doit sa hiérarchie d'objet/view
    // Que ce soit dynamiquement ou vie un layout XML


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialiation de données
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mHomePcViewModel = new ViewModelProvider(this).get(HomePcViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home_pc, container, false);

        final TextView textView = root.findViewById(R.id.text_home_pc);

        mHomePcViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // On renvoie une vue, root, qui est obtenue en récupénant le layout du fragment en question, son parent(container),
        return root;
    }


    // appelés juste après la création
    // Tout les setup de vue doivent avoir lieux ici
    // Que ce soit la recherche de vue comme de l'attache d'event Listener
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);


        /*
        // Remplacement de fragment
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.your_placeholder, new FooFragment());
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
         */


    }
}