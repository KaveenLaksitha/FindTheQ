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

    TextView carCount , vanCount , busCount, bikeCount, allCount  ;
    Button joinQueue , exitbefore , exitafter;
    String idStation = "001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_details);

        allCount = findViewById(R.id.allCount);

        Call<Object> call = ClientRetrofit.getInstance().getMyApi().getQueueCount(idStation);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object>  call, Response<Object> response) {

                System.out.println("response " + response.body().toString());
                String message  = response.body().toString();
                System.out.println(message.substring(7,10));

                allCount.setText(message.substring(7,10));

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("Error", t.getMessage());

            }
        });

    }

}