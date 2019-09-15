package com.mipt.smehelper.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mipt.smehelper.R;

public class CoworkersFragment extends Fragment {

    private RecyclerView coworkersRecyclerView;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_coworkers_screen, container, false);

        coworkersRecyclerView = rootView.findViewById(R.id.coworkers_rv);
        progressBar = rootView.findViewById(R.id.coworkers_pb);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // get users from server

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                        coworkersRecyclerView.setVisibility(View.VISIBLE);
                    }
                }, 1000);
            }
        });

        return rootView;
    }
}
