package com.andruidteam.andruid.util;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.db.entity.CharacterEntity;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    List<? extends CharacterEntity> mCharacterList;

    @Nullable
    private final CharacterClickCallback mCharacterClickCallback;

    public CharacterAdapter(@Nullable CharacterClickCallback clickCallback)
    {
        mCharacterClickCallback = clickCallback;
        setHasStableIds(true);
    }

    public void setCharacterList(final List<? extends CharacterEntity> characterList) {
        if( mCharacterList == null) {
            mCharacterList = characterList;
            notifyItemRangeInserted(0, characterList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff((new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mCharacterList.size();
                }

                @Override
                public int getNewListSize() {
                    return characterList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mCharacterList.get(oldItemPosition).getId() == characterList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Character newCharacter = characterList.get(newItemPosition);
                    Character oldCharacter = mCharacterList.get(oldItemPosition);
                    return newCharacter.getId() == oldCharacter.getId();
                }
            }));
            mCharacterList = characterList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CharacterItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.)
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    static class CharacterViewHolder extends RecyclerView.ViewHolder {

        final CharacterItemBinding binding;

        public CharacterViewHolder(CharacterItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

}



