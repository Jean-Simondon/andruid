package com.andruidteam.andruid.ui.fragment.inventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentEquipmentItemBinding;
import com.andruidteam.andruid.databinding.FragmentSpellItemBinding;

import java.util.ArrayList;
import java.util.Map;

public class EquipmentListAdapter extends BaseAdapter {

    public static final String TAG = "EquipmentListAdapter";

    private Map<String, String> equipments;
    private ArrayList<String> keys;

    public EquipmentListAdapter(Context context, Map<String, String> equipments) {
        this.equipments = equipments;
        this.keys = new ArrayList<>();
        for (Map.Entry<String, String> entry : equipments.entrySet()) {
            if (entry.getKey() != null) keys.add(entry.getKey());
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return equipments.size();
    }

    @Override
    public Object getItem(int position) {
        return equipments.get(keys.get(position));
    }

    public String getItemKey(int position) {
        return keys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSpells(Map<String, String> equipments) {
        this.equipments = equipments;
        this.keys = new ArrayList<>();
        for (Map.Entry<String, String> entry : equipments.entrySet()) {
            keys.add(entry.getKey());
        }
        notifyDataSetChanged();
    }

    public void update() {
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FragmentEquipmentItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_equipment_item, parent, false);
        binding.setName(equipments.get(keys.get(position)));
        return binding.getRoot();
    }
}