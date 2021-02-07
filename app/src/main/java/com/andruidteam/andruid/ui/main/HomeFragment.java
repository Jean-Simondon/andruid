package com.andruidteam.andruid.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.controller.DungeonMasterActivity;
import com.andruidteam.andruid.controller.PlayableCharacterActivity;
import com.andruidteam.andruid.ui.inventory.InventoryViewModel;

public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";
    public View root;

    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragmentManager = getFragmentManager();

        Button buttonToDM = view.findViewById(R.id.toDMPicking);
        buttonToDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamePickingFragment fragment = new GamePickingFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_main, fragment, GamePickingFragment.TAG)
                        .commit();
            }
        });

        Button buttonToPC = view.findViewById(R.id.toPCPicking);
        buttonToPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharacterPickingFragment fragment = new CharacterPickingFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_main, fragment, CharacterPickingFragment.TAG)
                        .commit();
            }
        });

    }
}



