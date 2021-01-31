package com.andruidteam.andruid.ui.team_info;

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

public class TeamInfoFragment extends Fragment {

    private TeamInfoViewModel teamInfoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        teamInfoViewModel =  new ViewModelProvider(this).get(TeamInfoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_team_info, container, false);
        final TextView textView = root.findViewById(R.id.text_team_info);
        teamInfoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}