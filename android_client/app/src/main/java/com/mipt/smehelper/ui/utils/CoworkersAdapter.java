package com.mipt.smehelper.ui.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mipt.smehelper.R;
import com.mipt.smehelper.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class CoworkersAdapter extends RecyclerView.Adapter<CoworkersAdapter.CoworkerHolder> {

    private List<User> coworkers;

    public interface ClickCallback {
        void onClickCoworker(int position);
    }

    private ClickCallback clickCallback;

    public CoworkersAdapter(List<User> coworkers, ClickCallback clickCallback) {
        this.coworkers = coworkers;
        this.clickCallback = clickCallback;
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
        holder.bind(currentCoworker, clickCallback, position);
    }

    @Override
    public int getItemCount() {
        return coworkers.size();
    }

    public void addCoworkers(List<User> coworkers) {
        this.coworkers.addAll(coworkers);
    }

    class CoworkerHolder extends RecyclerView.ViewHolder {

        private ImageView profileImg;
        private TextView name;
        private TextView job;

        CoworkerHolder(View itemView) {
            super(itemView);
            profileImg = itemView.findViewById(R.id.profile_img);
            name = itemView.findViewById(R.id.coworker_name);
            job = itemView.findViewById(R.id.coworker_job);
        }

        void bind(User currentCoworker, ClickCallback clickCallback, int position) {
            name.setText(currentCoworker.getName());
            job.setText(currentCoworker.getJob());
            int number = new Random().nextInt(90);
            Picasso.get().load(String.format("https://randomuser.me/api/portraits/men/%d.jpg", number))
                    .transform(new CircleTransform())
                    .into(profileImg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickCallback.onClickCoworker(position);
                }
            });
        }
    }
}
