package com.andruidteam.andruid.ui.detailCaractere;

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

public class DetailCaractereFragment extends Fragment {

    private DetailCaractereViewModel mdetailCaractereViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mdetailCaractereViewModel = new ViewModelProvider(this).get(DetailCaractereViewModel.class);
        View root = inflater.inflate(R.layout.fragment_detail_caractere, container, false);
        final TextView textView = root.findViewById(R.id.text_detail_caractere);

        mdetailCaractereViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;

    }
}