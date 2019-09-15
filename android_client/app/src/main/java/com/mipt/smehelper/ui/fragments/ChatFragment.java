package com.mipt.smehelper.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mipt.smehelper.R;
import com.mipt.smehelper.ui.MainMenu;
import com.mipt.smehelper.ui.chat.Dialog;
import com.mipt.smehelper.ui.chat.Message;
import com.mipt.smehelper.ui.chat.User;
import com.mipt.smehelper.ui.utils.CircleTransform;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatFragment extends Fragment {
    @BindView(R.id.dialogsList)
    DialogsList dialogsList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, rootView);

        dialogsList = rootView.findViewById(R.id.dialogsList);

        DialogsListAdapter dialogsListAdapter = new DialogsListAdapter<>(new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                //If you using another library - write here your way to load image
                Picasso.get().load(url)
                        .transform(new CircleTransform())
                        .into(imageView);
            }
        });

        dialogsList.setAdapter(dialogsListAdapter);
        int number = new Random().nextInt(90);
        int number1 = new Random().nextInt(90);
        int number2 = new Random().nextInt(90);
        int number3 = new Random().nextInt(90);
        int number4 = new Random().nextInt(90);
        int number5 = new Random().nextInt(90);
        User user1 = new User("0", "Misha", String.format("https://randomuser.me/api/portraits/men/%d.jpg", number), true);
        User user2 = new User("1", "Misha", String.format("https://randomuser.me/api/portraits/men/%d.jpg", number1), true);
        User user3 = new User("2", "Misha", String.format("https://randomuser.me/api/portraits/men/%d.jpg", number2), false);
        User user4 = new User("3", "Misha", String.format("https://randomuser.me/api/portraits/men/%d.jpg", number3), true);
        User user5 = new User("4", "Misha", String.format("https://randomuser.me/api/portraits/men/%d.jpg", number4), true);
        User user6 = new User("4", "Misha", String.format("https://randomuser.me/api/portraits/men/%d.jpg", number5), true);
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        dialogsListAdapter.addItem(new Dialog("1", "Костя", String.format("https://randomuser.me/api/portraits/men/%d.jpg", number),
                users, new Message("0", user6, "Да, готов заключить договор"), 1));
        dialogsListAdapter.addItem(new Dialog("1", "ООО Грузоперевозки", String.format("https://randomuser.me/api/portraits/men/%d.jpg", number),
                users, new Message("0", user2, "Куда доставить вагон с железобетоном?"), 2));
        dialogsListAdapter.addItem(new Dialog("1", "Алексей", String.format("https://randomuser.me/api/portraits/men/%d.jpg", number),
                users, new Message("0", user3, "Не найдется пара лишних рук? В долгу не останусь!"), 1));
        dialogsListAdapter.addItem(new Dialog("1", "Миша авто", String.format("https://randomuser.me/api/portraits/men/%d.jpg", number),
                users, new Message("0", user4, "Давайте на следующей неделе"), 1));
        dialogsListAdapter.addItem(new Dialog("1", "Валера", String.format("https://randomuser.me/api/portraits/men/%d.jpg", number),
                users, new Message("0", user5, "Смогу доставить только завтра, извините"), 3));
        dialogsListAdapter.notifyDataSetChanged();

        return rootView;
    }
}
