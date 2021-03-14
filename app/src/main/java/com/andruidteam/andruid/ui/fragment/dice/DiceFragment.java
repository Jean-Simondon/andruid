package com.andruidteam.andruid.ui.fragment.dice;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
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

import java.io.IOException;
import java.util.Random;

public class DiceFragment extends Fragment {

    public static final String TAG = "DiceFragment";

    private FragmentDiceBinding mBinding;

    private static MediaPlayer mp = null;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dice, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mBinding.diceResult.setTextColor(Color.parseColor("#000000"));
        mBinding.historic.setTextColor(Color.parseColor("#808080"));
        mBinding.historic.setMovementMethod(new ScrollingMovementMethod());


        mBinding.dice1D4.setOnClickListener(view1 -> {
            int roll = rollDice(4, getActivity().getApplicationContext());
            updateResults("1D4: " + roll, mBinding);
        });

        mBinding.dice1D6.setOnClickListener(view12 -> {
            int roll = rollDice(6, getActivity().getApplicationContext());
            updateResults("1D6: " + roll, mBinding);
        });

        mBinding.dice1D8.setOnClickListener(view13 -> {
            int roll = rollDice(8, getActivity().getApplicationContext());
            updateResults("1D8: " + roll, mBinding);
        });

        mBinding.dice1D10.setOnClickListener(view14 -> {
            int roll = rollDice(10, getActivity().getApplicationContext());
            updateResults("1D10: " + roll, mBinding);
        });

        mBinding.dice1D12.setOnClickListener(view15 -> {
            int roll = rollDice(12, getActivity().getApplicationContext());
            updateResults("1D12: " + roll, mBinding);
        });

        mBinding.dice1D20.setOnClickListener(view16 -> {
            int roll = rollDice(20, getActivity().getApplicationContext());
            updateResults("1D20: " + roll, mBinding);
        });

        mBinding.dice1D100.setOnClickListener(view17 -> {
            int roll = rollDice(100, getActivity().getApplicationContext());

            updateResults("1D100: " + roll, mBinding);
        });

        mBinding.diceHistoryEraser.setOnClickListener(view18 -> eraseHistory(mBinding, getActivity().getApplicationContext()));


    }

    private static void updateResults(String lastRoll, FragmentDiceBinding bind){
        String history = bind.diceResult.getText() + "\n" + bind.historic.getText();
        bind.historic.setText(history);
        bind.diceResult.setText(lastRoll);
        bind.historic.scrollTo(0,0);
    }

    private static void eraseHistory(FragmentDiceBinding bind, Context context){
        MediaPlayer mp = MediaPlayer.create(context, R.raw.wipesound);
        if(mp.isPlaying()){
            mp.stop();
        }
        mp.start();
        mp.setOnCompletionListener(mediaPlayer -> {
            mediaPlayer.release();
            mediaPlayer = null;
        });
        bind.historic.setText("");
        bind.diceResult.setText("");
    }

    private static int rollDice(int size, Context context){

        MediaPlayer mp = MediaPlayer.create(context, R.raw.diceroll);
        if(mp.isPlaying()){
            mp.stop();
        }
        mp.start();
        mp.setOnCompletionListener(mediaPlayer -> {
            mediaPlayer.release();
            mediaPlayer = null;
        });
        return new Random().nextInt(size) + 1;
    }

}