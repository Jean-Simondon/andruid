package com.andruidteam.andruid.ui.fragment.home_dm;

import android.os.Bundle;
import android.util.Log;
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
import com.andruidteam.andruid.databinding.FragmentHomeDmBinding;
import com.andruidteam.andruid.databinding.FragmentJournalBinding;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;
import com.andruidteam.andruid.viewmodel.GameViewModel;

public class HomeDmFragment extends Fragment {

    public static final String TAG = "HomeDmFragment";

    private FragmentHomeDmBinding mBinding;

    private GameViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_dm, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        mBinding.setGame(viewModel.getGame());

        /**
         * TO DO
         * méthode pour modifier le contenu des champs textView, peut-être avec un OnLongClickListener
         */

    }

}