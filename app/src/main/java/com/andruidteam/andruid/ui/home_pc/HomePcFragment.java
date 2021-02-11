package com.andruidteam.andruid.ui.home_pc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.andruidteam.andruid.DataRepository;
import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentHomePcBinding;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;

public class HomePcFragment extends Fragment {

    public static final String TAG = "HomePcFragment";

    private static final String KEY_CHARACTER_ID = "character_id";

    private FragmentHomePcBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentHomePcBinding.inflate(inflater, container, false);

        View view = mBinding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CharacterViewModel.Factory factory = new CharacterViewModel.Factory(requireActivity().getApplication(), requireArguments().getInt(KEY_CHARACTER_ID));

        final CharacterViewModel mCharacterViewModel = new ViewModelProvider(this, factory).get(CharacterViewModel.class);

//        mBinding.setLifecycleOwner(getViewLifecycleOwner());
//        mBinding.setProductViewModel(model);
//        subscribeToModel(model);



        mBinding.firstName.setText("Ici le fragment Home PC");

        /**
         * Ci dessous, initialisation des valeurs de la vue avec les éléments du view model
         */

        mBinding.firstName.setText(mCharacterViewModel.getFirstName());




    /*
        final TextView textView = view.findViewById(R.id.name);
        mCharacterViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

     */

//        mBinding.setLifecycleOwner(getViewLifecycleOwner());
//        mBinding.setProductViewModel(model);


        /**
         * Ici on ajoute des listeners sur les éléments du mBinding
         * Comme un bouton par exemple
         */


    }

    @Override
    public void onDestroy() {
        mBinding = null;
        super.onDestroy();
    }

    /** Creates product fragment for specific product ID */
    public static HomePcFragment forCharacter(int characterId) {
        HomePcFragment fragment = new HomePcFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_CHARACTER_ID, characterId);
        fragment.setArguments(args);
        return fragment;
    }


}