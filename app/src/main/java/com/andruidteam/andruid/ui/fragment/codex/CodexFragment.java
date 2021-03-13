package com.andruidteam.andruid.ui.fragment.codex;

import android.os.Bundle;
import android.util.Log;
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
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentCodexBinding;
import com.andruidteam.andruid.viewmodel.GameViewModel;

public class CodexFragment extends Fragment {

    public static final String TAG = "CodexFragment";

    private FragmentCodexBinding mBinding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_codex, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);

        mBinding.characterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: To character data");
                Navigation.findNavController(view).navigate(R.id.action_nav_codex_to_character_data_codex);
            }
        });

        mBinding.classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: To classes");
                Navigation.findNavController(view).navigate(R.id.action_nav_codex_to_classes_codex);
            }
        });

        mBinding.equipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: to equipment");
                Navigation.findNavController(view).navigate(R.id.action_nav_codex_to_equipement_codex);
            }
        });

        mBinding.races.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: to races");
                Navigation.findNavController(view).navigate(R.id.action_nav_codex_to_races_codex);
            }
        });

        mBinding.spells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: to spells");
                Navigation.findNavController(view).navigate(R.id.action_nav_codex_to_spells_codex);
            }
        });

        mBinding.monsters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: to monster");
                Navigation.findNavController(view).navigate(R.id.action_nav_codex_to_monsters_codex);
            }
        });

        mBinding.gameMechanics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: to game mechanics");
                Navigation.findNavController(view).navigate(R.id.action_nav_codex_to_game_mechanics_codex);
            }
        });

        mBinding.rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: to rules");
                Navigation.findNavController(view).navigate(R.id.action_nav_codex_to_rules_codex);
            }
        });

    }

}