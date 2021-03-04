package com.andruidteam.andruid.ui.fragment.script;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentScriptBinding;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;
import com.andruidteam.andruid.viewmodel.GameViewModel;

public class ScriptFragment extends Fragment {

    public static final String TAG = "ScriptFragment";

    private FragmentScriptBinding mBinding;

    private GameViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_script, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        mBinding.setGame(viewModel.getGame());

        /**
         * TO DO
         *
         */

    }

}