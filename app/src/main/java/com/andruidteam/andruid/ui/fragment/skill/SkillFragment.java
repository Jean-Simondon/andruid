package com.andruidteam.andruid.ui.fragment.skill;

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

public class SkillFragment extends Fragment {

    private SkillViewModel mSkillViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mSkillViewModel =
                new ViewModelProvider(this).get(SkillViewModel.class);
        View root = inflater.inflate(R.layout.fragment_skill, container, false);
        final TextView textView = root.findViewById(R.id.text_skill);
        mSkillViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}