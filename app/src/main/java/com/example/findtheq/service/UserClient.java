package com.example.findtheq.service;

import com.example.findtheq.models.Station;
import com.example.findtheq.models.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {

    String BASE_URL = "http://10.0.2.2:4000/api/";

    @POST("customers/register")
    Call<User> executeRegister(@Body User user);

    @POST("customers/login")
    Call<User> executeLogin(@Body HashMap<String, String> map);

    @POST("stations/register")
    Call<Station> stationRegister(@Body Station station);


}
