package com.mipt.smehelper.network;

import com.mipt.smehelper.models.Notification;
import com.mipt.smehelper.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClientApiGet {

    @GET("/get_notification")
    Call<Notification> getNotification(@Query("name") String userName);
}
