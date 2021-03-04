package com.andruidteam.andruid.ui.fragment.fight;

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

public class FightFragment extends Fragment {

    private FightViewModel mfightViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mfightViewModel =
                new ViewModelProvider(this).get(FightViewModel.class);
        View root = inflater.inflate(R.layout.fragment_fight, container, false);
        final TextView textView = root.findViewById(R.id.text_fight);
        mfightViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}