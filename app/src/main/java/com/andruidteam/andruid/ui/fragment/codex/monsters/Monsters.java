package com.andruidteam.andruid.ui.fragment.codex.monsters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentGameMechanicsCodexBinding;
import com.andruidteam.andruid.databinding.FragmentMonstersCodexBinding;

public class Monsters extends Fragment {

    public static final String TAG = "Monsters";

    private FragmentMonstersCodexBinding mBinding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_monsters_codex, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO : afficher la liste des monstres


    }

    private void goToListResult(View view) {
        Navigation.findNavController(view).navigate(R.id.action_monsters_codex_to_result_list);
    }

}
