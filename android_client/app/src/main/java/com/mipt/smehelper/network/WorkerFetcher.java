package com.mipt.smehelper.network;

import android.util.Log;

import com.mipt.smehelper.EBMessages.UsersFetchedMessage;
import com.mipt.smehelper.models.User;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WorkerFetcher {
    private static ClientApiGet clientApiGet = NetworkService.getInstance().getClientApi();

    private final static String TAG = "WorkerFetcher";
    private final static int READ_TIMEOUT_SEC = 30;

    public static void fromAvito(int avitoId) {
        new Thread() {
            public void run() {
                String baseURL = "https://rest-app.net/api/ads?login=mikel1998@mail.ru&token=cea83367c2231a09e8dce54b70c2dc91&category_id=" + String.valueOf(avitoId) + "&limit=10";
                List<User> users = new ArrayList<>();
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .readTimeout(READ_TIMEOUT_SEC, TimeUnit.SECONDS)
                        .build();
                Request.Builder builder = new Request.Builder();

                builder = builder.url(baseURL);

                Request request = builder.build();

                Call call = okHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    JSONArray values = new JSONObject(response.body().string()).getJSONArray("data");
                    Log.d(TAG, "avito response is ok");

                    for (int i = 0; i < values.length(); i++) {
                        JSONObject object = values.getJSONObject(i);

                        User user = new User();
                        user.setSmallUSN(true);
                        user.setRegDate(new DateTime().toString());
                        user.setName(object.getString("name"));
                        user.setJob(object.getString("title"));
                        user.setCity(object.getString("city"));
                        user.setAmount(Math.random() * 100000);

                        users.add(user);
                        Log.d(TAG, String.format("User with name %s added", user.getName()));
                    }

                    EventBus.getDefault().post(new UsersFetchedMessage(users));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }.start();
    }

    // Register event
    public static void fromUs(User user) {
        new Thread() {
            public void run() {
                try {
                    List<User> users = clientApiGet.getWorkers().execute().body();
                    if (user != null) {
                        Log.d(TAG, "users count fetched: " + String.valueOf(users.size()));
                        EventBus.getDefault().post(new UsersFetchedMessage(users));
                    } else {
                        Log.e(TAG, "users are null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
