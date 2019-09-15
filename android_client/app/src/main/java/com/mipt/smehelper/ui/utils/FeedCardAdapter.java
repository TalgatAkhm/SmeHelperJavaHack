package com.mipt.smehelper.ui.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mipt.smehelper.R;
import com.mipt.smehelper.models.Notification;

import java.util.List;

public class FeedCardAdapter extends BaseAdapter {

    private List<Notification> cardList;
    private LayoutInflater inflater;

    public FeedCardAdapter(Context context, List<Notification> cardList) {
        this.cardList = cardList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cardList.size();
    }

    @Override
    public Object getItem(int position) {
        return cardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.card_feed_view, parent, false);
        }

        TextView title = view.findViewById(R.id.card_title);
        TextView text = view.findViewById(R.id.card_text);
        TextView date = view.findViewById(R.id.card_date);

        Notification currentNotification = cardList.get(position);

        title.setText(currentNotification.getTitle());
        text.setText(currentNotification.getText());
        date.setText(currentNotification.getDate());

        return view;
    }

    public void addNotification(Notification notification) {
        cardList.add(notification);
    }
}
