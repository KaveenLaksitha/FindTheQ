package com.example.findtheq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class StationListView extends AppCompatActivity {

    ListView stationListDetailsView;

    String[] stationId;
    String[] stationName;
    String[] ownerName;
    String[] phoneNumber;
    String[] address;
    String[] arrivalTime;
    String[] finishTime;
    String[] stock;
    String[] queue;
    String[] queueLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list_view);

    }
}