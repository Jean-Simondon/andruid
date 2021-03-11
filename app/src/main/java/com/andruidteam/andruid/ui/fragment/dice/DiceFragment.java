package com.andruidteam.andruid.ui.fragment.dice;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentDiceBinding;
import com.andruidteam.andruid.databinding.FragmentJournalBinding;
import com.andruidteam.andruid.viewmodel.CharacterViewModel;

import java.util.Random;

public class DiceFragment extends Fragment {

    public static final String TAG = "DiceFragment";

    private FragmentDiceBinding mBinding;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dice, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.diceResult.setTextColor(Color.parseColor("#000000"));
        mBinding.historic1.setTextColor(Color.parseColor("#606060"));
        mBinding.historic2.setTextColor(Color.parseColor("#808080"));
        mBinding.historic3.setTextColor(Color.parseColor("#A0A0A0"));
        mBinding.historic4.setTextColor(Color.parseColor("#C0C0C0"));
        mBinding.historic5.setTextColor(Color.parseColor("#E8E8E8"));



        mBinding.dice1D4.setOnClickListener(view1 -> {
            int roll = rollDice(4);
            updateResults("1D4: " + roll, mBinding);
        });

        mBinding.dice1D6.setOnClickListener(view12 -> {
            int roll = rollDice(6);
            updateResults("1D6: " + roll, mBinding);
        });

        mBinding.dice1D8.setOnClickListener(view13 -> {
            int roll = rollDice(8);
            updateResults("1D8: " + roll, mBinding);
        });

        mBinding.dice1D10.setOnClickListener(view14 -> {
            int roll = rollDice(10);
            updateResults("1D10: " + roll, mBinding);
        });

        mBinding.dice1D12.setOnClickListener(view15 -> {
            int roll = rollDice(12);
            updateResults("1D12: " + roll, mBinding);
        });

        mBinding.dice1D20.setOnClickListener(view16 -> {
            int roll = rollDice(20);
            updateResults("1D20: " + roll, mBinding);
        });

        mBinding.dice1D100.setOnClickListener(view17 -> {
            int roll = rollDice(100);

            updateResults("1D100: " + roll, mBinding);
        });


    }

    public static void updateResults(String lastRoll, FragmentDiceBinding bind){
        bind.historic5.setText(bind.historic4.getText());
        bind.historic4.setText(bind.historic3.getText());
        bind.historic3.setText(bind.historic2.getText());
        bind.historic2.setText(bind.historic1.getText());
        bind.historic1.setText(bind.diceResult.getText());
        bind.diceResult.setText(lastRoll);



    }

    public static int rollDice(int size){
        return new Random().nextInt(size) + 1;
    }

}