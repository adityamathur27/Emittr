package com.aditya.emittr.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aditya.emittr.R;

import org.jetbrains.annotations.Nullable;

public class UserprogressFragment extends Fragment {

    public UserprogressFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_userprogress_fragment, container, false);

             // Display the points

        TextView pointsTextView = view.findViewById(R.id.textView); // replace with your actual TextView ID
        pointsTextView.setText("Result " +String.valueOf(QuestionFragment.points));

        return view;
    }
}