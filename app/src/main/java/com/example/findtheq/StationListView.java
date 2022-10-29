package com.example.findtheq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.findtheq.DBHandler.DBHandler;
import com.example.findtheq.models.ClientRetrofit;
import com.example.findtheq.models.Station;
import com.example.findtheq.models.UpdateStatusModel;
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
    ImageButton btnLogout;
    List<Station> stationList;
    private ListAdapter itemAdapter;
    private SearchView stationSearch;
    private ProgressBar progressBar;

    private DBHandler dbHandler;

    public StationListView() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list_view);

        btnLogout = findViewById(R.id.btnLogout);


        Intent i = getIntent();
        String email = i.getStringExtra("email");
        String type = i.getStringExtra("type");

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StationListView.this, LoginActivity.class);
                Toast.makeText(StationListView.this, "Signing out..." , Toast.LENGTH_LONG).show();
                dbHandler = new DBHandler(StationListView.this);
                dbHandler.deleteTheTable(email);
                startActivity(i);

            }
        });

        recyclerView = findViewById(R.id.recycler_station_list_view);
        stationList = new ArrayList<>();

        stationSearch = findViewById(R.id.stationSearch);

        progressBar = findViewById(R.id.pBar);
        progressBar.setVisibility(View.VISIBLE);

        //set touch listener to one item
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Intent intent = new Intent();
                        intent.setClass(StationListView.this, QueueDetails.class);
                        intent.putExtra("email",email);
                        intent.putExtra("type",type);
                        intent.putExtra("id",stationList.get(position).getId());
                        intent.putExtra("name",stationList.get(position).getName());
                        intent.putExtra("diesel",stationList.get(position).getStock().getDiesel());
                        intent.putExtra("petrol",stationList.get(position).getStock().getPetrol());
                        intent.putExtra("car",String.valueOf( stationList.get(position).getQueue().getCar()));
                        intent.putExtra("van",String.valueOf(stationList.get(position).getQueue().getVan()));
                        intent.putExtra("bus",String.valueOf(stationList.get(position).getQueue().getBus()));
                        intent.putExtra("bike",String.valueOf(stationList.get(position).getQueue().getBike()));
                        intent.putExtra("tuk",String.valueOf(stationList.get(position).getQueue().getTuk()));

                        startActivity(intent);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    // get the station list again when the activity resumed
    protected void onResume() {
        super.onResume();
        stationList.clear();
        loadStationList();
    }

    private void loadStationList(){
        Call<List<Station>> call = ClientRetrofit.getInstance().getMyApi().getStations();
        call.enqueue(new Callback<List<Station>>() {
            @Override
            public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
                if(response.code() != 200){
                    progressBar.setVisibility(View.VISIBLE);
                    System.out.println("error while reading station list");
                    return;
                }
                List<Station> stations = response.body();
                for(Station station : stations){
                    stationList.add(station);
                }
                PutDataIntoRecyclerView(stationList);
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<List<Station>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    private void PutDataIntoRecyclerView(List<Station> stationList){
        ListAdapter adapter = new ListAdapter(this, stationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    };
}