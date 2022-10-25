package com.example.findtheq.service;

import com.example.findtheq.models.Station;
import com.example.findtheq.models.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserClient {

    String BASE_URL = "https://findtheqapi.herokuapp.com/api/";

    @POST("customers/register")
    Call<User> executeRegister(@Body User user);

    @POST("customers/login")
    Call<User> executeLogin(@Body HashMap<String, String> map);

    @GET("customers/{email}")
    Call<User> getUserByEmail(@Path("email") String email);

    @POST("fuelstation/login")
    Call<Station> executeLoginStationOwner(@Body HashMap<String, String> map);

    @POST("fuelstation/fuelStationRegister")
    Call<Station> stationRegister(@Body Station station);

    @GET("fuelstation/getCount/{stationid}")
    Call<Object> getQueueCount(@Path("stationid") String stationid);

    //get all stations
    @GET("fuelstation/viewAllFuelStation")
    Call<List<Station>> getStations();

    //update isJoined or not
    @PUT("customers/updateJoined/{email}")
    Call<Object> updateJoinedStatus(@Path("email") String email);

    @PUT("customers/setStatus/{email}")
    Call<Object> updateJoinedStatusFalse(@Path("email") String email);

}
