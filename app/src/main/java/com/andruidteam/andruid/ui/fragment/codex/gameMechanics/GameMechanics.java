package com.andruidteam.andruid.ui.fragment.codex.gameMechanics;

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
import com.andruidteam.andruid.databinding.FragmentEquipementCodexBinding;
import com.andruidteam.andruid.databinding.FragmentGameMechanicsCodexBinding;

public class GameMechanics extends Fragment {

    public static final String TAG = "GameMechanics";

    private FragmentGameMechanicsCodexBinding mBinding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_mechanics_codex, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : afficher le résultat de https://www.dnd5eapi.co/api/
            }
        });

        mBinding.damageTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : afficher le résultat de https://www.dnd5eapi.co/api/
            }
        });

        mBinding.magicSchools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : afficher le résultat de https://www.dnd5eapi.co/api/
            }
        });

    }

    private void goToListResult(View view) {
        Navigation.findNavController(view).navigate(R.id.action_game_mechanics_codex_to_result_list);
    }

}
