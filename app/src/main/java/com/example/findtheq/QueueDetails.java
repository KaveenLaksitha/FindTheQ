package com.example.findtheq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findtheq.models.ClientRetrofit;
import com.example.findtheq.models.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QueueDetails extends AppCompatActivity {

    TextView carCount , vanCount , busCount, bikeCount, allCount, tukCount;
    Button joinQueue , exitbefore , exitafter;
    String idStation = "001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_details);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        System.out.println("data>>>>>>"+ name);

        allCount = findViewById(R.id.allCount);
        carCount = findViewById(R.id.carCount);
        vanCount = findViewById(R.id.vanCount);
        busCount = findViewById(R.id.busCount);
        bikeCount = findViewById(R.id.bikeCount);
        tukCount = findViewById(R.id.tukCount);

        carCount.setText(intent.getStringExtra("car"));

        Call<Object> call = ClientRetrofit.getInstance().getMyApi().getQueueCount(idStation);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object>  call, Response<Object> response) {

                String message  = response.body().toString();

                allCount.setText(message.substring(7,10));

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("Error", t.getMessage());

            }
        });

    }

}