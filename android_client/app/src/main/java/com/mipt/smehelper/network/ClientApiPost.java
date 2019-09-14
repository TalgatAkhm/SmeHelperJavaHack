package com.mipt.smehelper.network;

import com.mipt.smehelper.models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClientApiPost {
    @POST("/auth")
    Call<ResponseBody> authClient(@Body User user);
}
