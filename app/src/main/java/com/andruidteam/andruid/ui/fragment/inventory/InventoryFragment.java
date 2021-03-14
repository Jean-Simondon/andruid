package com.andruidteam.andruid.ui.fragment.inventory;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentInventoryBinding;
import com.andruidteam.andruid.databinding.FragmentJournalBinding;
import com.andruidteam.andruid.ui.fragment.spell.SpellListAdapter;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryFragment extends Fragment implements LifecycleOwner {

    public static final String TAG = "InventoryFragment";

    private FragmentInventoryBinding mBinding;

    private CharacterViewModel viewModel;

    private HashMap<String, String> allEquipments= new HashMap<>();

    private Map<String, String> characterEquipments = new HashMap<>();

    private boolean checked;

    private EquipmentListAdapter mEquipmentListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_inventory, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);

        characterEquipments = viewModel.getCharacter().getEquipments();
        checked = false;
        if (mEquipmentListAdapter == null) {
            mEquipmentListAdapter = new EquipmentListAdapter(requireActivity(), characterEquipments);
        } else {
            mEquipmentListAdapter.setSpells(characterEquipments);
        }
        mBinding.equipmentsList.setAdapter(mEquipmentListAdapter);
        getAllEquipments();

        mBinding.searchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String currentText = charSequence.toString().trim();
                Map<String, String> currentEquipments = checked ? allEquipments : characterEquipments;
                Map<String, String> filteredSpells = currentEquipments.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().toLowerCase().startsWith(currentText.toLowerCase()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                mEquipmentListAdapter.setSpells(filteredSpells);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mBinding.switchAllEquipments.setOnCheckedChangeListener((CompoundButton button, boolean checked) -> {
            if (checked) {
                mEquipmentListAdapter.setSpells(allEquipments);
            } else {
                mEquipmentListAdapter.setSpells(characterEquipments);
            }
            this.checked = checked;
        });

        mBinding.equipmentsList.setOnItemClickListener((parent, listView, position, id) -> {
            Log.d(TAG, "on Item Click: " + mEquipmentListAdapter.getItem(position));
            Bundle bundle = new Bundle();
            bundle.putString("equipmentKey", mEquipmentListAdapter.getItemKey(position));
            Navigation.findNavController(getView()).navigate(R.id.action_inventoryFragment_to_equipmentFragment, bundle);
        });

    }

    private void getAllEquipments() {
        if (allEquipments.isEmpty()) {
            viewModel.getRepository().doGETJsonObject("https://www.dnd5eapi.co/api/equipment", response -> {
                try {
                    JSONArray spellJsonArray = response.getJSONArray("results");
                    for (int i = 0; i < spellJsonArray.length(); i++) {
                        String index = spellJsonArray.getJSONObject(i).getString("index");
                        String name = spellJsonArray.getJSONObject(i).getString("name");
                        allEquipments.put(index, name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, this.getContext());
        }
    }

}

