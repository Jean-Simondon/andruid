package com.andruidteam.andruid.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentCharacterListBinding;
import com.andruidteam.andruid.ui.MainActivity;
import com.andruidteam.andruid.viewmodel.CharacterListViewModel;

import com.andruidteam.andruid.util.IOnBackPressed;

import java.util.ArrayList;

public class CharacterListFragment extends Fragment implements IOnBackPressed {

    public static final String TAG = "CharacterListFragment";

    private CharacterListAdapter mCharacterAdapter;

    private FragmentCharacterListBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_list, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CharacterListViewModel viewModel = new ViewModelProvider(this).get(CharacterListViewModel.class);

        mCharacterAdapter = new CharacterListAdapter(requireActivity(), viewModel.getCharacters());
        mBinding.charactersList.setAdapter(mCharacterAdapter);

        mCharacterAdapter.setCharacterList(viewModel.getCharacters());

        mBinding.newCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addCharacter();
                mCharacterAdapter.setCharacterList(viewModel.getCharacters());
            }
        });

        mBinding.charactersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCharacterAdapter.getItemId(position);
            }
        });

    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        mCharacterAdapter = null;
        super.onDestroyView();
    }

    private final CharacterClickCallback mCharacterClickCallback = myCharacter -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) requireActivity()).pickCharacter(myCharacter);
        }
    };

    @Override
    public boolean onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        /*
        FragmentManager fragmentManager = getFragmentManager();
        HomeFragment fragment = new HomeFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_main, fragment, HomeFragment.TAG)
                .commit();

         */
        return true;
    }

}
