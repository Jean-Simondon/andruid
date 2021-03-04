package com.andruidteam.andruid.ui.fragment.inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;

import com.andruidteam.andruid.R;

public class ExampleFragment extends Fragment implements LifecycleOwner {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_example, container, false);
        FragmentManager fragmentManager = getFragmentManager();

        return root;
    }
}
