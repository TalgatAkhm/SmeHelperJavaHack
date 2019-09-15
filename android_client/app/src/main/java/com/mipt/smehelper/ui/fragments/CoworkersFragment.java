package com.mipt.smehelper.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mipt.smehelper.Data;
import com.mipt.smehelper.R;
import com.mipt.smehelper.models.User;
import com.mipt.smehelper.network.ClientApiPost;
import com.mipt.smehelper.network.NetworkService;
import com.mipt.smehelper.network.WorkerFetcher;
import com.mipt.smehelper.ui.utils.CoworkersAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoworkersFragment extends Fragment implements CoworkersAdapter.ClickCallback {

    private RecyclerView coworkersRecyclerView;
    private ProgressBar progressBar;

    private static ClientApiPost clientApiPost = NetworkService.getInstance().getPostClientApi();

    private final static String TAG = "CoworkersFragment";

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
        CoworkersAdapter coworkersAdapter = new CoworkersAdapter(currentCoworkers, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(coworkersRecyclerView.getContext(),
                layoutManager.getOrientation());
        coworkersRecyclerView.addItemDecoration(dividerItemDecoration);
        coworkersRecyclerView.setLayoutManager(layoutManager);
        coworkersRecyclerView.setAdapter(coworkersAdapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // get users from server

                try {
                    String[] ids = clientApiPost.getWorkersPairs(Data.getInstance().getUser().
                            getName()).execute().body().getIds().split(" ");
                    Log.d(TAG, "ids = " + Arrays.toString(ids));

                    for (String id : ids) {
                        List<User> avitoUsers = WorkerFetcher.fromAvito(Integer.valueOf(id));
                        if (avitoUsers != null && avitoUsers.size() != 0) {
                            currentCoworkers.addAll(avitoUsers);
                        }
                    }

                    currentCoworkers.addAll(WorkerFetcher.fromUs(Data.getInstance().getUser()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

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
