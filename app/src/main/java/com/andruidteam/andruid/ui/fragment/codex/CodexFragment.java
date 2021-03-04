package com.andruidteam.andruid.ui.fragment.codex;

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

public class CodexFragment extends Fragment {

    private CodexViewModel codexViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        codexViewModel = new ViewModelProvider(this).get(CodexViewModel.class);

        View root = inflater.inflate(R.layout.fragment_codex, container, false);

        final TextView textView = root.findViewById(R.id.text_codex);

        codexViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;

    }
}