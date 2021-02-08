package com.andruidteam.andruid.ui.home_dm;

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

public class HomeDmFragment extends Fragment {

    private HomeDmViewModel mHomeDmViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mHomeDmViewModel =
                new ViewModelProvider(this).get(HomeDmViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home_dm, container, false);
        final TextView textView = root.findViewById(R.id.text_home_dm);
        mHomeDmViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}