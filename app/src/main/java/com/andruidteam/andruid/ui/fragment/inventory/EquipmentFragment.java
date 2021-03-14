package com.andruidteam.andruid.ui.fragment.inventory;

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
import com.andruidteam.andruid.databinding.FragmentEquipmentBinding;
import com.andruidteam.andruid.databinding.FragmentSkillBinding;
import com.andruidteam.andruid.rds.Requests;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EquipmentFragment extends Fragment {
    public static final String TAG = "EquipmentFragment";

    private FragmentEquipmentBinding mBinding;

    private CharacterViewModel viewModel;

    private String equipmentKey;

    private boolean isAdded;

    private Equipment equipment;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_equipment, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        if (equipmentKey == null) {
            equipmentKey = getArguments().getString("equipmentKey");
        }
        isAdded = viewModel.getCharacter().getEquipments().containsKey(equipmentKey);
        mBinding.setIsAdded(isAdded);
        getAndDisplayEquipment();

        mBinding.addequipmentButton.setOnClickListener(buttonView -> {
            if (isAdded) {
                viewModel.getCharacter().removeEquipment(equipment.getIndex());
                isAdded = false;
                mBinding.setIsAdded(isAdded);
            } else {
                viewModel.getCharacter().addEquipment(equipment.getIndex(), equipment.getName());
                isAdded = true;
                mBinding.setIsAdded(isAdded);
            }
        });
    }

    private void getAndDisplayEquipment() {
        viewModel.getRepository().doGETJsonObject("https://www.dnd5eapi.co/api/equipment/" + equipmentKey, response -> {
            try {
                equipment = new Equipment();
                equipment.setIndex(equipmentKey);
                equipment.setName(Requests.getStringOrEmpty(response, "name"));
                StringBuilder description = new StringBuilder();
                JSONArray descriptions = Requests.getJSONArrayOrNull(response, "desc");
                if (descriptions != null) {
                    for (int i = 0; i < descriptions.length(); i++) {
                        description.append(descriptions.getString(i)).append("\n");
                    }
                }
                equipment.setDescription(description.toString());
                JSONObject category = Requests.getJSONObjectOrNull(response,"equipment_category");
                equipment.setCategory(category != null ? Requests.getStringOrEmpty(category,"name") : "");
                equipment.setToolCategory(Requests.getStringOrEmpty(response, "tool_category"));
                equipment.setWeight(Requests.getStringOrEmpty(response, "weight"));
                JSONObject cost = Requests.getJSONObjectOrNull(response,"cost");
                equipment.setCost(cost != null ? Requests.getStringOrEmpty(cost,"quantity") : "");
                JSONObject gearCategory = Requests.getJSONObjectOrNull(response,"gear_category");
                equipment.setGearCategory(gearCategory != null ? Requests.getStringOrEmpty(gearCategory,"name") : "");
                equipment.setQuantity(Requests.getStringOrEmpty(response, "quantity"));
                equipment.setVehicleCategory(Requests.getStringOrEmpty(response, "vehicle_category"));
                mBinding.setEquipment(equipment);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, this.getContext());
    }

    public class Equipment {
        private String index = "";
        private String name = "";
        private String description = "";
        private String category = "";
        private String toolCategory = "";
        private String vehicleCategory = "";
        private String gearCategory = "";
        private String cost = "";
        private String weight = "";
        private String quantity = "";

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getToolCategory() {
            return toolCategory;
        }

        public void setToolCategory(String toolCategory) {
            this.toolCategory = toolCategory;
        }

        public String getVehicleCategory() {
            return vehicleCategory;
        }

        public void setVehicleCategory(String vehicleCategory) {
            this.vehicleCategory = vehicleCategory;
        }

        public String getGearCategory() {
            return gearCategory;
        }

        public void setGearCategory(String gearCategory) {
            this.gearCategory = gearCategory;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

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

    }

}
