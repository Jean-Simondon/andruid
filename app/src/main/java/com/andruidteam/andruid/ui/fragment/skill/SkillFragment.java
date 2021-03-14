package com.andruidteam.andruid.ui.fragment.skill;

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
import com.andruidteam.andruid.databinding.FragmentSkillBinding;
import com.andruidteam.andruid.databinding.FragmentSpellBinding;
import com.andruidteam.andruid.rds.Requests;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SkillFragment extends Fragment {
    public static final String TAG = "SkillFragment";

    private FragmentSkillBinding mBinding;

    private CharacterViewModel viewModel;

    private String skillKey;

    private boolean isAdded;

    private Skill skill;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_skill, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        if (skillKey == null) {
            skillKey = getArguments().getString("skillKey");
        }
        isAdded = viewModel.getCharacter().getSkills().containsKey(skillKey);
        mBinding.setIsAdded(isAdded);
        getAndDisplaySkill();

        mBinding.addSkillButton.setOnClickListener(buttonView -> {
            if (isAdded) {
                viewModel.getCharacter().removeSkill(skill.getIndex());
                isAdded = false;
                mBinding.setIsAdded(isAdded);
            } else {
                viewModel.getCharacter().addSkill(skill.getIndex(), skill.getName());
                isAdded = true;
                mBinding.setIsAdded(isAdded);
            }
        });
    }

    private void getAndDisplaySkill() {
        viewModel.getRepository().doGETJsonObject("https://www.dnd5eapi.co/api/skills/" + skillKey, response -> {
            try {
                skill = new Skill();
                skill.setIndex(skillKey);
                skill.setName(Requests.getStringOrEmpty(response, "name"));
                StringBuilder description = new StringBuilder();
                JSONArray descriptions = Requests.getJSONArrayOrNull(response, "desc");
                if (descriptions != null) {
                    for (int i = 0; i < descriptions.length(); i++) {
                        description.append(descriptions.getString(i)).append("\n");
                    }
                }
                skill.setDescription(description.toString());
                JSONObject abilityScore = Requests.getJSONObjectOrNull(response,"ability_score");
                skill.setAbilityScore(abilityScore != null ? Requests.getStringOrEmpty(abilityScore,"name") : "");
                mBinding.setSkill(skill);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, this.getContext());
    }

    public class Skill {
        private String index = "";
        private String name = "";
        private String description = "";
        private String abilityScore = "";

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAbilityScore() {
            return abilityScore;
        }

        public void setAbilityScore(String abilityScore) {
            this.abilityScore = abilityScore;
        }
    }

}
