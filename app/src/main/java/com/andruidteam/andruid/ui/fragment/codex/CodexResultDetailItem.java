package com.andruidteam.andruid.ui.fragment.codex;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentCodexResultDetailItemBinding;
import com.andruidteam.andruid.ui.fragment.codex.characterData.AbilityScoresDynamicTemplate;
import com.andruidteam.andruid.ui.fragment.codex.characterData.AlignmentsDynamicTemplate;
import com.andruidteam.andruid.ui.fragment.codex.characterData.LanguagesDynamicTemplate;
import com.andruidteam.andruid.ui.fragment.codex.characterData.ProficienciesDynamicTemplate;
import com.andruidteam.andruid.ui.fragment.codex.characterData.SkillsDynamicTemplate;
import com.andruidteam.andruid.ui.fragment.codex.classes.ClassesDynamicTemplate;
import com.andruidteam.andruid.ui.fragment.codex.classes.FeaturesDynamicTemplate;
import com.andruidteam.andruid.ui.fragment.codex.classes.StartingEquipmentDynamicTemplate;
import com.andruidteam.andruid.ui.fragment.codex.classes.SubClassesDynamicTemplate;
import com.andruidteam.andruid.viewmodel.GameViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CodexResultDetailItem extends Fragment {

    public static final String TAG = "CodexResultDetailItem";

    private FragmentCodexResultDetailItemBinding mBinding;

    private GameViewModel viewModel;

    private JSONObject jsonObject;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_codex_result_detail_item, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        int position = getArguments().getInt("POSITION");
        JSONObject jsonObjectFromList = (JSONObject)viewModel.getCodexResultAdapter().getObject(position);

        String url = null;
        try {
            url = jsonObjectFromList.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        viewModel.getRepository().doGETJsonObject("https://www.dnd5eapi.co" + url, response -> {
            Log.d(TAG, response.toString());
            dynamiseTemplate(response);
        }, this.getContext());

    }

    public void dynamiseTemplate(JSONObject response) {

        String name = viewModel.getCurrentCodexSearch();

        switch (name) {
            case "ability-scores":
                AbilityScoresDynamicTemplate.SetDynamicTemplate(requireActivity(), response, mBinding);
                break;
            case "skills":
                SkillsDynamicTemplate.SetDynamicTemplate(requireActivity(), response, mBinding);
                break;
            case "proficiencies":
                ProficienciesDynamicTemplate.SetDynamicTemplate(requireActivity(), response, mBinding);
                break;
            case "languages":
                LanguagesDynamicTemplate.SetDynamicTemplate(requireActivity(), response, mBinding);
                break;
            case "alignments":
                AlignmentsDynamicTemplate.SetDynamicTemplate(requireActivity(), response, mBinding);
                break;
            case "classes":
                ClassesDynamicTemplate.SetDynamicTemplate(requireActivity(), response, mBinding);
                break;
            case "subclasses":
                SubClassesDynamicTemplate.SetDynamicTemplate(requireActivity(), response, mBinding);
                break;
            case "features":
                FeaturesDynamicTemplate.SetDynamicTemplate(requireActivity(), response, mBinding);
                break;
            case "starting-equipment":
                StartingEquipmentDynamicTemplate.SetDynamicTemplate(requireActivity(), response, mBinding);
                break;
            case "races":
                // code block
                break;
            case "subraces":
                // code block
                break;
            case "equipment-categories":
                // code block
                break;
            case "equipment/Weapon":
                // code block
                break;
            default:
                // code block
        }

    }


}
