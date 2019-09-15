package com.mipt.smehelper.ui.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mipt.smehelper.R;
import com.mipt.smehelper.models.User;

import java.util.List;

public class CoworkersAdapter extends RecyclerView.Adapter<CoworkersAdapter.CoworkerHolder> {

    private List<User> coworkers;

    public CoworkersAdapter(List<User> coworkers) {
        this.coworkers = coworkers;
    }

    @NonNull
    @Override
    public CoworkerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CoworkerHolder(inflater.inflate(R.layout.coworker_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CoworkerHolder holder, int position) {
        User currentCoworker = coworkers.get(position);
    }

    @Override
    public int getItemCount() {
        return coworkers.size();
    }

    class CoworkerHolder extends RecyclerView.ViewHolder {


        public CoworkerHolder(View itemView) {
            super(itemView);
        }
    }
}
