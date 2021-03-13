package com.andruidteam.andruid.ui.fragment.codex.characterData;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentCharacterDataCodexBinding;
import com.andruidteam.andruid.ui.fragment.codex.CodexResultAdapter;
import com.andruidteam.andruid.ui.fragment.spell.SpellListAdapter;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;
import com.andruidteam.andruid.viewmodel.GameViewModel;

import org.json.JSONArray;
import org.json.JSONException;

public class CharacterData extends Fragment {

    public static final String TAG = "CharacterData";

    private FragmentCharacterDataCodexBinding mBinding;

    private GameViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_data_codex, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        mBinding.abilityScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setCurrentCodexSearch("ability-scores");
                goToListResult(view);
            }
        });

        mBinding.skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setCurrentCodexSearch("skills");
                goToListResult(view);
            }
        });

        mBinding.proficiencies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setCurrentCodexSearch("proficiencies");
                goToListResult(view);
            }
        });

        mBinding.languages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setCurrentCodexSearch("languages");
                goToListResult(view);
            }
        });

        mBinding.alignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setCurrentCodexSearch("alignments");
                goToListResult(view);
            }
        });

    }

    private void goToListResult(View view) {
        Navigation.findNavController(view).navigate(R.id.action_character_data_codex_to_result_list);
    }

}
