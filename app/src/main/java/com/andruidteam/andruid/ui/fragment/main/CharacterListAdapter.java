package com.andruidteam.andruid.ui.fragment.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentCharacterItemBinding;
import com.andruidteam.andruid.db.entity.CharacterEntity;

import java.util.ArrayList;

public class CharacterListAdapter extends BaseAdapter {

    public static final String TAG = "CharacterListAdapter";

    private LayoutInflater layoutInflater;
    private ArrayList<CharacterEntity> mCharacterList;

    public CharacterListAdapter(Context context, ArrayList<CharacterEntity> characters) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mCharacterList = characters;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCharacterList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCharacterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mCharacterList.get(position).getId();
    }

    public void setCharacterList(ArrayList<CharacterEntity> characters) {
        this.mCharacterList = characters;
        notifyDataSetChanged();
    }

    public void update() {
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FragmentCharacterItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_character_item, parent, false);
        binding.setCharacter(mCharacterList.get(position));
        return binding.getRoot();
    }
}
