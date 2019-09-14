package com.mipt.smehelper.network;

import com.mipt.smehelper.EBMessages.UsersFetchedMessage;
import com.mipt.smehelper.models.User;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class WorkerFetcher {
    private static ClientApiGet clientApiGet = NetworkService.getInstance().getClientApi();

    static List<User> fromAvito(User user) {
        String baseURL = "https://rest-app.net/api/ads?login=mikel1998@mail.ru&token=cea83367c2231a09e8dce54b70c2dc91&category_id=";
        List<User> users = new ArrayList<>();

        return users;
    }

    // Run in new thread, register event
    static void fromUs(User user) {
        new Thread() {
            public void run() {
                try {
                    List<User> users = clientApiGet.getWorkers().execute().body();
                    if (user != null) {
                        EventBus.getDefault().post(new UsersFetchedMessage(users));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
