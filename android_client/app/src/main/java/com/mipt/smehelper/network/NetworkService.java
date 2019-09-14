package com.mipt.smehelper.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Service for initializing retrofit.
 */
public final class NetworkService {

    private static NetworkService mInstance;

    private static final String BASE_URL = "http://10.91.5.245:8080";
    private static final String POST_URL = "http://10.91.5.245:8080";

    private Retrofit retrofit;
    private Retrofit postRetrofit;

    private NetworkService() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient)
                .build();
        postRetrofit = new Retrofit.Builder()
                .baseUrl(POST_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }

        return mInstance;
    }

    public ClientApiGet getClientApi() {
        return retrofit.create(ClientApiGet.class);
    }

    public ClientApiPost getPostClientApi() {
        return postRetrofit.create(ClientApiPost.class);
    }

}
