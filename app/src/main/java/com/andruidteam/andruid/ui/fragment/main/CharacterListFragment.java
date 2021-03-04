package com.andruidteam.andruid.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentCharacterListBinding;
import com.andruidteam.andruid.ui.activity.CharacterActivity;
import com.andruidteam.andruid.viewmodel.CharacterListViewModel;

import com.andruidteam.andruid.util.IOnBackPressed;

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
                mCharacterAdapter.update();
            }
        });

        /**
         * Au clic sur un élément de la liste, c'est un choix de personnage, et on continue vers l'activity PlayableCharacterActivity
         */
        mBinding.charactersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CharacterActivity.class);
                Bundle bundle = new Bundle();
                Log.d("CharacterListFragment", "on Item Click" + String.valueOf(mCharacterAdapter.getItemId(position)));
                bundle.putInt(CharacterActivity.INPUT_CHARACTER_ID, (int) mCharacterAdapter.getItemId(position));
                intent.putExtras(bundle);
                Log.d(TAG, "onItemClick:");
                startActivity(intent);
            }
        });











    }

    @Override
    public void onDestroyView() {
//        mBinding = null;
//        mCharacterAdapter = null;
        super.onDestroyView();
    }

    @Override
    public boolean onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        return true;
    }

}
