package com.andruidteam.andruid.ui.fragment.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentCharacterItemBinding;
import com.andruidteam.andruid.databinding.FragmentGameItemBinding;
import com.andruidteam.andruid.db.entity.CharacterEntity;
import com.andruidteam.andruid.db.entity.GameEntity;

import java.util.ArrayList;

public class GameListAdapter extends BaseAdapter {

    public static final String TAG = "GameListAdapter";

    private LayoutInflater layoutInflater;
    private ArrayList<GameEntity> mGameList;

    public GameListAdapter(Context context, ArrayList<GameEntity> games) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mGameList = games;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mGameList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mGameList.get(position).getId();
    }

    public void setGameList(ArrayList<GameEntity> games) {
        this.mGameList = games;
        notifyDataSetChanged();
    }

    public void update() {
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FragmentGameItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_game_item, parent, false);
        binding.setGame(mGameList.get(position));
        return binding.getRoot();
    }
}
