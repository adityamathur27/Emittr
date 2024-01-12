package com.aditya.emittr.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aditya.emittr.R;

import org.jetbrains.annotations.Nullable;

public class LeaderboardFragment extends Fragment {

    public LeaderboardFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_leaderboard_fragment, container, false);
    }
}