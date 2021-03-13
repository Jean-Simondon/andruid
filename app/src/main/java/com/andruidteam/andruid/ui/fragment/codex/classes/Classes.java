package com.andruidteam.andruid.ui.fragment.codex.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentClassesCodexBinding;
import com.andruidteam.andruid.ui.fragment.codex.CodexResultAdapter;
import com.andruidteam.andruid.viewmodel.GameViewModel;

public class Classes extends Fragment {

    public static final String TAG = "Classes";

    private FragmentClassesCodexBinding mBinding;

    private GameViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_classes_codex, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        mBinding.classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setCurrentCodexSearch("classes");
                goToListResult(view);
            }
        });

        mBinding.subclasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setCurrentCodexSearch("subclasses");
                goToListResult(view);
            }
        });

        mBinding.features.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setCurrentCodexSearch("features");
                goToListResult(view);
            }
        });

        mBinding.startingEquipement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setCurrentCodexSearch("starting-equipment");
                goToListResult(view);
            }
        });

    }

    private void goToListResult(View view) {
        Navigation.findNavController(view).navigate(R.id.action_classes_codex_to_result_list);
    }

}
