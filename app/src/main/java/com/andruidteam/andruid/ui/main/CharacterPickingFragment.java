package com.andruidteam.andruid.ui.main;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.andruidteam.andruid.AndruidApp;
import com.andruidteam.andruid.DataRepository;
import com.andruidteam.andruid.databinding.CharacterPickingFragmentBinding;
import com.andruidteam.andruid.db.entity.Character;
import com.andruidteam.andruid.util.IOnBackPressed;
import com.andruidteam.andruid.R;
import com.andruidteam.andruid.ui.PlayableCharacterActivity;
import com.andruidteam.andruid.viewmodel.CharacterListViewModel;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CharacterPickingFragment extends Fragment implements IOnBackPressed {

    public static final String TAG = "CharacterPickingFragment";
    private CharacterPickingFragmentBinding mBinding;
    public View root;

    public DataRepository mRepository;
    public LiveData<List<Character>> mListCharacter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.character_picking_fragment, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final CharacterListViewModel viewModel = new ViewModelProvider(this).get(CharacterListViewModel.class);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        mBinding.setCharacterListViewModel(viewModel); // on charge le viewModel dans le layout

        mBinding.newCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addCharacter();
            }
        });




        // On récupère la liste de personnage
//        LinearLayout layout = (LinearLayout) root.findViewById(R.id.ListCharacter);
        /*
        for( ListIterator<Character> Character = mListCharacter.getValue().listIterator(); Character.hasNext(); ) {
            Character cc = Character.next();
            Button btn = new Button(requireActivity().getApplication());
            btn.setText("" + cc.getFirstName() + " " + cc.getLastName());
            btn.setId(cc.getId());
            layout.addView(btn);
        }

         */


        /*


        // Puis au clic sur le bouton "Nouveau personnage", on
        Button newCharacter = (MaterialButton) root.findViewById(R.id.newCharacter);
        newCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepository.createNewCharacter();

                // Puis on récupère le personnage, et on le rajoute à la liste

            }
        });



         */



        // Plus tard, sur chaque bouton de personnage, on place un listener pour aller vers l'appli à l'aide de l'ID, et on l'instance dans l'activity
        /*
        buttonCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupération argument avec requireArguments().getList(LIST_ALL_CHARACTER, ID);
                // Passer l'ID du Character choisis en argument dans l'intent
                Bundle bundle = new Bundle();
                bundle.putString(PlayableCharacterActivity.INPUT_CHARACTER_ID, l'id du character );
                Intent intent = new Intent(getActivity(), PlayableCharacterActivity.class);
                startActivity(intent);
            }
        });
        */








    }





    @Override
    public boolean onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        /*
        FragmentManager fragmentManager = getFragmentManager();
        HomeFragment fragment = new HomeFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_main, fragment, HomeFragment.TAG)
                .commit();

         */
        return true;
    }

}
