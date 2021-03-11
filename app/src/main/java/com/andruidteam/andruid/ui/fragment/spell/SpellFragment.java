package com.andruidteam.andruid.ui.fragment.spell;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentSpellBinding;
import com.andruidteam.andruid.rds.RequestQueueSingleton;
import com.andruidteam.andruid.ui.activity.CharacterActivity;
import com.andruidteam.andruid.ui.fragment.main.CharacterListAdapter;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SpellFragment extends Fragment {

    public static final String TAG = "SpellFragment";

    private FragmentSpellBinding mBinding;

    private CharacterViewModel viewModel;

    private HashMap<String, String> spells = new HashMap<>();

    private SpellListAdapter mSpellListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_spell, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        getAndDisplaySpells();
//        mBinding.searchName.setOnEditorActionListener((textView, actionId, keyEvent) -> {
//            textView
//            String currentText = textView.getText().toString().trim();
//            if (actionId == EditorInfo.IME_ACTION_DONE && !currentText.equals("")) {
//                Map<String, String> filteredSpells = spells.entrySet()
//                        .stream()
//                        .filter(entry -> entry.getValue().toLowerCase().startsWith(currentText.toLowerCase()))
//                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//                mSpellListAdapter.setSpells(filteredSpells);
//                return true;
//            }
//            return false;
//        });

        mBinding.searchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String currentText = charSequence.toString().trim();
                if (!currentText.equals("")) {
                    Map<String, String> filteredSpells = spells.entrySet()
                            .stream()
                            .filter(entry -> entry.getValue().toLowerCase().startsWith(currentText.toLowerCase()))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mSpellListAdapter.setSpells(filteredSpells);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void getAndDisplaySpells() {
        if (spells.isEmpty()) {
            mBinding.setIsLoading(true);
            viewModel.getRepository().doGETJsonObject("https://www.dnd5eapi.co/api/spells", response -> {
                try {
                    JSONArray spellJsonArray = response.getJSONArray("results");
                    for (int i = 0; i < spellJsonArray.length(); i++) {
                        String index = spellJsonArray.getJSONObject(i).getString("index");
                        String name = spellJsonArray.getJSONObject(i).getString("name");
                        spells.put(index, name);
                    }
                    displaySpells();
                    mBinding.setIsLoading(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, this.getContext());
        } else {
            displaySpells();
        }
    }

    private void displaySpells() {
        mSpellListAdapter = new SpellListAdapter(requireActivity(), spells);
        mBinding.spellsList.setAdapter(mSpellListAdapter);

        mBinding.spellsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "on Item Click: " + mSpellListAdapter.getItem(position));
            }
        });
    }


}