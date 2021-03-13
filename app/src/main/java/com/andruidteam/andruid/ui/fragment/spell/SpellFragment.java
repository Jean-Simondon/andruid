package com.andruidteam.andruid.ui.fragment.spell;

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
import com.andruidteam.andruid.databinding.FragmentSpellBinding;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;

import org.json.JSONArray;
import org.json.JSONException;

public class SpellFragment extends Fragment {
    public static final String TAG = "SpellFragment";

    private FragmentSpellBinding mBinding;

    private CharacterViewModel viewModel;

    private String spellKey;

    private boolean isAdded;

    private Spell spell;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_spell, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        if (spellKey == null) {
            spellKey = getArguments().getString("spellKey");
        }
        isAdded = viewModel.getCharacter().getSpells().containsKey(spellKey);
        mBinding.setIsAdded(isAdded);
        getAndDisplaySpell();

        mBinding.addSpellButton.setOnClickListener(buttonView -> {
            if (isAdded) {
                viewModel.getCharacter().removeSpell(spell.getIndex());
                isAdded = false;
                mBinding.setIsAdded(isAdded);
            } else {
                viewModel.getCharacter().addSpell(spell.getIndex(), spell.getName());
                isAdded = true;
                mBinding.setIsAdded(isAdded);
            }
        });
    }

    private void getAndDisplaySpell() {
        viewModel.getRepository().doGETJsonObject("https://www.dnd5eapi.co/api/spells/" + spellKey, response -> {
            try {
                spell = new Spell();
                spell.setIndex(spellKey);
                spell.setName(response.getString("name"));
                spell.setRange(response.getString("range"));
                StringBuilder description = new StringBuilder();
                JSONArray descriptions = response.getJSONArray("desc");
                for (int i = 0; i < descriptions.length(); i++) {
                    description.append(descriptions.getString(i)).append("\n");
                }
                spell.setDescription(description.toString());
                spell.setMaterial(response.getString("material"));
                spell.setDuration(response.getString("duration"));
                spell.setConcentration(response.getString("concentration"));
                spell.setCastingTime(response.getString("casting_time"));
                spell.setLevel(response.getString("level"));
                spell.setSchool(response.getJSONObject("school").getString("name"));
                StringBuilder components = new StringBuilder();
                JSONArray componentsArray = response.getJSONArray("components");
                for (int i = 0; i < componentsArray.length(); i++) {
                    components.append(componentsArray.getString(i)).append(", ");
                }
                spell.setComponents(components.toString());
                StringBuilder higherLevel = new StringBuilder();
                JSONArray higherLevelArray = response.getJSONArray("higher_level");
                for (int i = 0; i < higherLevelArray.length(); i++) {
                    higherLevel.append(higherLevelArray.getString(i)).append("\n");
                }
                spell.setHigherLevel(higherLevel.toString());
                mBinding.setSpell(spell);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, this.getContext());
    }

    public class Spell {
        private String index = "";
        private String name = "";
        private String description = "";
        private String higherLevel = "";
        private String range = "";
        private String components = "";
        private String material = "";
        private String duration = "";
        private String concentration = "";
        private String castingTime = "";
        private String level = "";
        private String school = "";

        public String getMaterial() {
            return material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setHigherLevel(String higherLevel) {
            this.higherLevel = higherLevel;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public String getComponents() {
            return components;
        }

        public void setComponents(String components) {
            this.components = components;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getConcentration() {
            return concentration;
        }

        public void setConcentration(String concentration) {
            this.concentration = concentration;
        }

        public String getCastingTime() {
            return castingTime;
        }

        public void setCastingTime(String castingTime) {
            this.castingTime = castingTime;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getHigherLevel() {
            return higherLevel;
        }

        public String getRange() {
            return range;
        }
    }

}
