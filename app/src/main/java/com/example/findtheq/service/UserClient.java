package com.example.findtheq.service;

import com.example.findtheq.models.Station;
import com.example.findtheq.models.StockModel;
import com.example.findtheq.models.UpdateStatusModel;
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

//    String BASE_URL = "https://findtheqapi.herokuapp.com/api/";
    String BASE_URL = "http://10.0.2.2:4000/api/";

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

    //get all stations
    @GET("fuelstation/viewOneFuelStation/{id}")
    Call<Station> getOneStations(@Path("id") String id);

    //update isJoined or not
    @PUT("customers/updateJoined/{email}")
    Call<Object> updateJoinedStatus(@Path("email") String email);

    @PUT("customers/setStatus/{email}")
    Call<Object> updateJoinedStatusFalse(@Path("email") String email);

    //update joined status and increase/decrease vehicle count
    @POST("customers/joinedstatus")
    Call<Object> updateStatusAndCount(@Body UpdateStatusModel model);

    @PUT("fuelstation/updateStock/{id}")
    Call<Object> updateStockDetails(@Path("id") String id, @Body StockModel stockModel);

    @PUT("fuelstation/updateStockTime/{id}")
    Call<Object> updateTime(@Path("id") String id, @Body Station station);

    @PUT("fuelstation/updateStatus/{id}/{status}")
    Call<Object> updateStatus(@Path("id") String id, @Path("status") String status);
}
