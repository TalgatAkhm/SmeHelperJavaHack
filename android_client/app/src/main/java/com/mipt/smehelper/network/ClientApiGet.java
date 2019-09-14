package com.mipt.smehelper.network;

import com.mipt.smehelper.models.Notification;
import com.mipt.smehelper.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClientApiGet {
    @GET("/get_user")
    Call<User> getUser();

    @GET("/get_workers")
    Call<List<User>> getWorkers();

    @GET("/get_notification")
    Call<Notification> getNotification(@Query("userId") Integer userId);
}
