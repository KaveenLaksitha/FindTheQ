package com.example.findtheq.service;

import com.example.findtheq.models.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {

    String BASE_URL = "http://10.0.2.2:4000/";

    @POST("api/customers/register")
    Call<Void> executeRegister(@Body HashMap<String, String > map);

    @POST("api/customers/login")
    Call<User> executeLogin(@Body HashMap<String, String> map);

}
