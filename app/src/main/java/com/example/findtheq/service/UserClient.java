package com.example.findtheq.service;

import com.example.findtheq.models.Station;
import com.example.findtheq.models.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserClient {

    String BASE_URL = "https://findtheqapi.herokuapp.com/api/";

    @POST("customers/register")
    Call<User> executeRegister(@Body User user);

    @POST("customers/login")
    Call<User> executeLogin(@Body HashMap<String, String> map);

    @POST("fuelstation/fuelStationRegister")
    Call<Station> stationRegister(@Body Station station);

    @GET("fuelstation/getCount/{stationid}")
    Call<Object> getQueueCount(@Path("stationid") String stationid);

}
