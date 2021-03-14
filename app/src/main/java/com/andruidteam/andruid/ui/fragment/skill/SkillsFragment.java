package com.andruidteam.andruid.ui.fragment.skill;

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
import com.andruidteam.andruid.databinding.FragmentSkillsBinding;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SkillsFragment extends Fragment {

    public static final String TAG = "SkillsFragment";

    private FragmentSkillsBinding mBinding;

    private CharacterViewModel viewModel;

    private HashMap<String, String> allSkills = new HashMap<>();

    private Map<String, String> characterSkills = new HashMap<>();

    private boolean checked;

    private SkillListAdapter mSkillListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_skills, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        characterSkills = viewModel.getCharacter().getSkills();
        checked = false;
        if (mSkillListAdapter == null) {
            mSkillListAdapter = new SkillListAdapter(requireActivity(), characterSkills);
        } else {
            mSkillListAdapter.setSpells(characterSkills);
        }
        mBinding.skillsList.setAdapter(mSkillListAdapter);
        getAllSKills();

        mBinding.searchName.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        String currentText = charSequence.toString().trim();
                        Map<String, String> currentSkills = checked ? allSkills : characterSkills;
                        Map<String, String> filteredSpells = currentSkills.entrySet()
                                .stream()
                                .filter(entry -> entry.getValue().toLowerCase().startsWith(currentText.toLowerCase()))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                        mSkillListAdapter.setSpells(filteredSpells);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

        mBinding.switchAllSkills.setOnCheckedChangeListener((CompoundButton button, boolean checked) -> {
            if (checked) {
                mSkillListAdapter.setSpells(allSkills);
            } else {
                mSkillListAdapter.setSpells(characterSkills);
            }
            this.checked = checked;
        });

        mBinding.skillsList.setOnItemClickListener((parent, listView, position, id) -> {
            Log.d(TAG, "on Item Click: " + mSkillListAdapter.getItem(position));
            Bundle bundle = new Bundle();
            bundle.putString("skillKey", mSkillListAdapter.getItemKey(position));
            Navigation.findNavController(getView()).navigate(R.id.action_skillsFragment_to_skillFragment, bundle);
        });

    }


    private void getAllSKills() {
        if (allSkills.isEmpty()) {
            viewModel.getRepository().doGETJsonObject("https://www.dnd5eapi.co/api/skills", response -> {
                try {
                    JSONArray spellJsonArray = response.getJSONArray("results");
                    for (int i = 0; i < spellJsonArray.length(); i++) {
                        String index = spellJsonArray.getJSONObject(i).getString("index");
                        String name = spellJsonArray.getJSONObject(i).getString("name");
                        allSkills.put(index, name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, this.getContext());
        }
    }

}