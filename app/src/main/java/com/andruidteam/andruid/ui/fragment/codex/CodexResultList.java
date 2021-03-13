package com.andruidteam.andruid.ui.fragment.codex;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentCodexResultListBinding;
import com.andruidteam.andruid.viewmodel.GameViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CodexResultList extends Fragment {

    public static final String TAG = "CodexResultList";

    private FragmentCodexResultListBinding mBinding;

    private GameViewModel viewModel;

    private CodexResultAdapter mCodexResultAdapter;

    private JSONArray jsonArray;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_codex_result_list, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        mBinding.title.setText(viewModel.getCurrentCodexSearch());

        String params = viewModel.getCurrentCodexSearch();
        getJSON(params);

    }

    public void getJSON(String param) {
        Log.d(TAG, "getJSON: ");
        viewModel.getRepository().doGETJsonObject("https://www.dnd5eapi.co/api/" + param, response -> {
            try {
                jsonArray = response.getJSONArray("results");
                displayJSON();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, this.getContext());
    }

    private void displayJSON() {
        Log.d(TAG, "displayJSON: ");

        mCodexResultAdapter = new CodexResultAdapter(requireActivity(), jsonArray);
        mBinding.resultList.setAdapter(mCodexResultAdapter);
        mCodexResultAdapter.setJSONList(jsonArray);

        viewModel.setCodexResultAdapter(mCodexResultAdapter);

        mBinding.resultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("POSITION", position);
                Navigation.findNavController(view).navigate(R.id.action_result_list_to_result_detail_item, bundle);
            }
        });

    }

}
