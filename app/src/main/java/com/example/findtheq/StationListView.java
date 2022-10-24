package com.example.findtheq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.findtheq.models.Station;
import com.example.findtheq.service.UserClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StationListView extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Station> stationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list_view);

        recyclerView = findViewById(R.id.recycler_station_list_view);
        stationList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://findtheqapi.herokuapp.com/api/").addConverterFactory(GsonConverterFactory.create()).build();

        UserClient stationApi = retrofit.create(UserClient.class);
        Call<List<Station>> call = stationApi.getStations();

        call.enqueue(new Callback<List<Station>>() {
            @Override
            public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
                if(response.code() != 200){
                    System.out.println("error while reading station list");
                    return;
                }
                List<Station> stations = response.body();

                for(Station station : stations){
//                    String res = "";

//                    res += station.getStock();
//                    Log.v("get stations res>>>>",""+ res);

//                    int id = Integer.parseInt(station.getId());
//                    String stationName = station.getName();
//                    String status = station.getStatus();
//                    String queueLength = String.valueOf(
//                            (station.getQueue().getBike())+
//                                    (station.getQueue().getBus())+
//                                    (station.getQueue().getCar())+
//                                    (station.getQueue().getTuk())+
//                                    (station.getQueue().getVan()));
//                    String queueLength = "10";

                    stationList.add(station);
                }
                PutDataIntoRecyclerView(stationList);
            }

            @Override
            public void onFailure(Call<List<Station>> call, Throwable t) {

            }
        });
    }

    private void PutDataIntoRecyclerView(List<Station> stationList){
        ListAdapter adapter = new ListAdapter(this, stationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    };
}