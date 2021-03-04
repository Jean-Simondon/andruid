package com.andruidteam.andruid.ui.fragment.journal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentHomePcBinding;
import com.andruidteam.andruid.databinding.FragmentJournalBinding;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;

public class JournalFragment extends Fragment {

    public static final String TAG = "JournalFragment";

    private FragmentJournalBinding mBinding;

    private CharacterViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_journal, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        mBinding.setCharacter(viewModel.getCharacter());

        /**
         * TODO : Permettre d'écrire dans un textEdit puis d'ajouter l'élément à une liste de commentaires
         *
         */

    }

}