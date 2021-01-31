package com.andruidteam.andruid.ui.dice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.andruidteam.andruid.R;

public class DiceFragment extends Fragment {

    private DiceViewModel mDiceViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mDiceViewModel =  new ViewModelProvider(this).get(DiceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dice, container, false);
        final TextView textView = root.findViewById(R.id.text_dice);

        mDiceViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}