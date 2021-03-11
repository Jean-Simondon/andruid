package com.andruidteam.andruid.ui.fragment.codex.characterData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentCharacterDataCodexBinding;

public class CharacterData extends Fragment {

    public static final String TAG = "CharacterData";

    private FragmentCharacterDataCodexBinding mBinding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_data_codex, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.abilityScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : afficher le résultat de https://www.dnd5eapi.co/api/
            }
        });

        mBinding.skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : afficher le résultat de https://www.dnd5eapi.co/api/
            }
        });

        mBinding.proficiencies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : afficher le résultat de https://www.dnd5eapi.co/api/
            }
        });

        mBinding.languages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : afficher le résultat de https://www.dnd5eapi.co/api/
            }
        });

        mBinding.alignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : afficher le résultat de https://www.dnd5eapi.co/api/
            }
        });

    }

}
