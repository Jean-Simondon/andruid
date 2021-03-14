package com.andruidteam.andruid.ui.fragment.skill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentSpellItemBinding;
import com.andruidteam.andruid.databinding.FragmentSpellItemBindingImpl;

import java.util.ArrayList;
import java.util.Map;

public class SkillListAdapter extends BaseAdapter {

    public static final String TAG = "CharacterListAdapter";

    private Map<String, String> skills;
    private ArrayList<String> keys;

    public SkillListAdapter(Context context, Map<String, String> skills) {
        this.skills = skills;
        this.keys = new ArrayList<>();
        for (Map.Entry<String, String> entry : skills.entrySet()) {
            if (entry.getKey() != null) keys.add(entry.getKey());
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return skills.size();
    }

    @Override
    public Object getItem(int position) {
        return skills.get(keys.get(position));
    }

    public String getItemKey(int position) {
        return keys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSpells(Map<String, String> skills) {
        this.skills = skills;
        this.keys = new ArrayList<>();
        for (Map.Entry<String, String> entry : skills.entrySet()) {
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
        binding.setName(skills.get(keys.get(position)));
        return binding.getRoot();
    }
}