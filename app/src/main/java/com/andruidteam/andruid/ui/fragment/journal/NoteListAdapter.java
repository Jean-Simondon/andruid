package com.andruidteam.andruid.ui.fragment.journal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentCharacterItemBinding;
import com.andruidteam.andruid.databinding.FragmentNoteItemBinding;
import com.andruidteam.andruid.db.entity.CharacterEntity;

import java.util.ArrayList;

public class NoteListAdapter /**  extends BaseAdapter  */{

    /*
    public static final String TAG = "NoteListAdapter";

//    private LayoutInflater layoutInflater;
    private ArrayList<String> mNoteList;

    public NoteListAdapter(Context context, ArrayList<String> notes) {
//        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mNoteList = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mNoteList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNoteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void update() {
        notifyDataSetChanged();
    }

    public void setNotesList(ArrayList<String> notes) {
        this.mNoteList = notes;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FragmentNoteItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_note_item, parent, false);
        binding.setNote(mNoteList.get(position));
        return binding.getRoot();
    }

     */
}
