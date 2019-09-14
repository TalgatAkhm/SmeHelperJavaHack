package com.mipt.smehelper.network;

import com.mipt.smehelper.models.Transaction;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClientApiPost {
    @POST("/send_transaction")
    Call<ResponseBody> sendNewTransaction(@Body Transaction transaction);
}
