package com.mipt.smehelper.network;

import com.mipt.smehelper.models.Notification;
import com.mipt.smehelper.models.DepIds;
import com.mipt.smehelper.models.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClientApiPost {
    @POST("/register")
    Call<ResponseBody> authClient(@Body User user);

    @POST("/login")
    Call<User> getUser(@Body String userName);

    @POST("/notifications")
    Call<List<Notification>> getNotifications();

    @POST("/get_dep_users")
    Call<List<User>> getWorkers(@Body String userName);

    @POST("/get_deps")
    Call<DepIds> getWorkersPairs(@Body String userName);

    @POST("/doc")
    Call<ResponseBody> getDoc(@Body String json);

}
