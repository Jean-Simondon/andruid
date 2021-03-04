package com.andruidteam.andruid.ui.fragment.home_pc;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentHomePcBinding;
import com.andruidteam.andruid.ui.activity.CharacterActivity;
import com.andruidteam.andruid.viewmodel.CharacterListViewModel;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;

public class HomePcFragment extends Fragment {

    public static final String TAG = "HomePcFragment";

    private FragmentHomePcBinding mBinding;

    private CharacterViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_pc, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);


        mBinding.setCharacter(viewModel.getCharacter());

//        mBinding.setLifecycleOwner(getViewLifecycleOwner());
//        mBinding.CharacterViewModel(model);
//        subscribeToModel(model);

//        mBinding.firstName.setText("Ici le fragment Home PC");

        /**
         * Ci dessous, initialisation des valeurs de la vue avec les éléments du view model
         */

//        mBinding.firstName.setText(mCharacterViewModel.getFirstName());

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


}