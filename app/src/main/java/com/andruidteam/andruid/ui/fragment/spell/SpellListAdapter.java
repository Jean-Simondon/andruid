package com.andruidteam.andruid.ui.fragment.spell;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentCharacterItemBinding;
import com.andruidteam.andruid.databinding.FragmentSpellItemBinding;
import com.andruidteam.andruid.db.entity.CharacterEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpellListAdapter extends BaseAdapter {

    public static final String TAG = "SpellListAdapter";

    private Map<String, String> spells;
    private ArrayList<String> keys;

    public SpellListAdapter(Context context, Map<String, String> spells) {
        this.spells = spells;
        this.keys = new ArrayList<>();
        for (Map.Entry<String, String> entry : spells.entrySet()) {
            if (entry.getKey() != null) keys.add(entry.getKey());
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return spells.size();
    }

    @Override
    public Object getItem(int position) {
        return spells.get(keys.get(position));
    }

    public String getItemKey(int position) {
        return keys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSpells(Map<String, String> spells) {
        this.spells = spells;
        this.keys = new ArrayList<>();
        for (Map.Entry<String, String> entry : spells.entrySet()) {
            keys.add(entry.getKey());
        }
        notifyDataSetChanged();
    }

    public void update() {
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FragmentSpellItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_spell_item, parent, false);
        binding.setName(spells.get(keys.get(position)));
        return binding.getRoot();
    }
}