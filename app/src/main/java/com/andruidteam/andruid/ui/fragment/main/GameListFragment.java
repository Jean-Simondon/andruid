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

import com.andruidteam.andruid.databinding.FragmentGameListBinding;
import com.andruidteam.andruid.util.IOnBackPressed;
import com.andruidteam.andruid.R;
import com.andruidteam.andruid.ui.activity.DungMasterActivity;
import com.andruidteam.andruid.viewmodel.GameListViewModel;

public class GameListFragment extends Fragment implements IOnBackPressed {

    public static final String TAG = "GameListFragment";

    private GameListAdapter mGameAdapter;

    private FragmentGameListBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_list, container, false);
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        GameListViewModel viewModel = new ViewModelProvider(this).get(GameListViewModel.class);

        mGameAdapter = new GameListAdapter(requireActivity(), viewModel.getGames());
        mBinding.gamesList.setAdapter(mGameAdapter);

        mGameAdapter.setGameList(viewModel.getGames());

        mBinding.newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                viewModel.addGame();
                mGameAdapter.update();
            }
        });

        mBinding.gamesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: ");
                Intent intent = new Intent(getActivity(), DungMasterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(DungMasterActivity.INPUT_GAME_ID, (int) mGameAdapter.getItemId(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
//        mBinding = null;
//        mGameAdapter = null;
        super.onDestroyView();
    }

    @Override
    public boolean onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        return true;
    }

}
