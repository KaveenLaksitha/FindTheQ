package com.example.findtheq.models;

import com.example.findtheq.service.UserClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientRetrofit {
    private static ClientRetrofit instance = null;
    private UserClient myApiclient;

    private ClientRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(UserClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApiclient = retrofit.create(UserClient.class);
    }

    public static synchronized ClientRetrofit getInstance() {
        if (instance == null) {
            instance = new ClientRetrofit();
        }
        return instance;
    }

    public UserClient getMyApi() {
        return myApiclient;
    }

}
