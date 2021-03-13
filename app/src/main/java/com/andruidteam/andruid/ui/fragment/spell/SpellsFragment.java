package com.andruidteam.andruid.ui.fragment.spell;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentSpellsBinding;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SpellsFragment extends Fragment {

    public static final String TAG = "SpellsFragment";

    private FragmentSpellsBinding mBinding;

    private CharacterViewModel viewModel;

    private HashMap<String, String> allSpells = new HashMap<>();

    private Map<String, String> characterSpells = new HashMap<>();

    private SpellListAdapter mSpellListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_spells, container, false);
        ;
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        characterSpells = viewModel.getCharacter().getSpells();
        mSpellListAdapter = new SpellListAdapter(requireActivity(), characterSpells);
        mBinding.spellsList.setAdapter(mSpellListAdapter);
        getAllSpells();

        mBinding.searchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String currentText = charSequence.toString().trim();
                Map<String, String> filteredSpells = allSpells.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().toLowerCase().startsWith(currentText.toLowerCase()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                mSpellListAdapter.setSpells(filteredSpells);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mBinding.switchAllSpells.setOnCheckedChangeListener((CompoundButton button, boolean checked) -> {
            if (checked) {
                mSpellListAdapter.setSpells(allSpells);
            } else {
                mSpellListAdapter.setSpells(characterSpells);
            }
        });

        mBinding.spellsList.setOnItemClickListener((parent, listView, position, id) -> {
            Log.d(TAG, "on Item Click: " + mSpellListAdapter.getItem(position));
            Bundle bundle = new Bundle();
            bundle.putString("spellKey", mSpellListAdapter.getItemKey(position));
            Navigation.findNavController(getView()).navigate(R.id.action_spellsFragment_to_spellFragment, bundle);
        });
    }

    private void getAllSpells() {
        if (allSpells.isEmpty()) {
            mBinding.setIsLoading(true);
            viewModel.getRepository().doGETJsonObject("https://www.dnd5eapi.co/api/spells", response -> {
                try {
                    JSONArray spellJsonArray = response.getJSONArray("results");
                    for (int i = 0; i < spellJsonArray.length(); i++) {
                        String index = spellJsonArray.getJSONObject(i).getString("index");
                        String name = spellJsonArray.getJSONObject(i).getString("name");
                        allSpells.put(index, name);
                    }
                    mBinding.setIsLoading(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, this.getContext());
        }
    }

}