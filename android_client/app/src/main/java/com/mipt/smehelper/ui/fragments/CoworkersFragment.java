package com.mipt.smehelper.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mipt.smehelper.R;
import com.mipt.smehelper.models.User;
import com.mipt.smehelper.ui.utils.CoworkersAdapter;

import java.util.ArrayList;
import java.util.List;

public class CoworkersFragment extends Fragment implements CoworkersAdapter.ClickCallback {

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

        final List<User> currentCoworkers = new ArrayList<>();
        User user = new User();
        user.setName("Молчанов Василий Геннадьевич");
        user.setJob("Фермер, перевозит овощи, разводит кур");
        user.setCity("Москва");
        currentCoworkers.add(user);
        CoworkersAdapter coworkersAdapter = new CoworkersAdapter(currentCoworkers, this);
        coworkersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        coworkersRecyclerView.setAdapter(coworkersAdapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // get users from server
                User user = new User();
                user.setName("Молчанов Василий Геннадьевич");
                user.setJob("Фермер, перевозит овощи, разводит кур");
                user.setCity("Москва");
                currentCoworkers.add(user);
                currentCoworkers.add(user);
                currentCoworkers.add(user);
                currentCoworkers.add(user);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        coworkersAdapter.addCoworkers(currentCoworkers);
                        coworkersAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.INVISIBLE);
                        coworkersRecyclerView.setVisibility(View.VISIBLE);
                    }
                }, 1000);
            }
        }).start();

        return rootView;
    }

    @Override
    public void onClickCoworker(int position) {
//        Intent intent = new Intent(getActivity(), UserActivity.class);
//        startActivity(intent);
    }
}
