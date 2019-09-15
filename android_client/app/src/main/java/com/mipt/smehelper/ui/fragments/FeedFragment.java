package com.mipt.smehelper.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.mipt.smehelper.Data;
import com.mipt.smehelper.R;
import com.mipt.smehelper.models.Notification;
import com.mipt.smehelper.ui.utils.FeedCardAdapter;

import java.util.List;

public class FeedFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView feedListView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FeedCardAdapter feedCardAdapter;

    private List<Notification> notifications;
    private int notificationCounter = 5;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notifications = Data.getInstance().getNotifications();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed_screen, container, false);

        feedListView = rootView.findViewById(R.id.feed_lv);
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_layout);

        swipeRefreshLayout.setOnRefreshListener(this);


        feedCardAdapter = new FeedCardAdapter(getActivity(), notifications.subList(0, notifications.size() - notificationCounter));
        feedListView.setAdapter(feedCardAdapter);

        return rootView;
    }

    @Override
    public void onRefresh() {
        if (notificationCounter != 1) {
            feedCardAdapter.addNotification(notifications.get(notifications.size() - (--notificationCounter)));
        } else {
            Toast.makeText(getActivity(), "Пока новостей нет", Toast.LENGTH_SHORT).show();
        }
        feedCardAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}
