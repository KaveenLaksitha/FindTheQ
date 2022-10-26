package com.example.findtheq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findtheq.models.ClientRetrofit;
import com.example.findtheq.models.Station;
import com.example.findtheq.models.UpdateStatusModel;
import com.example.findtheq.models.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QueueDetails extends AppCompatActivity {

    TextView title, dieselStock, petrolStock, allCount, carCount, vanCount, busCount, bikeCount, tukCount;
    Button joinQueue, exitbefore, exitafter;
    String idStation = "001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_details);

        //buttons
        joinQueue = findViewById(R.id.joinQueue);
        exitbefore = findViewById(R.id.exitbefore);
        exitafter = findViewById(R.id.exitafter);

        Intent intent = getIntent();

        //check whether user is already joined to the queue or not
        //and enable/disable relevant buttons
        checkIsJoined();
        setExitButtonStatusonLoad();

        title = findViewById(R.id.queueDetails);
        dieselStock = findViewById(R.id.dieselStock);
        petrolStock = findViewById(R.id.petrolStock);
        allCount = findViewById(R.id.allCount);
        carCount = findViewById(R.id.carCount);
        vanCount = findViewById(R.id.vanCount);
        busCount = findViewById(R.id.busCount);
        bikeCount = findViewById(R.id.bikeCount);
        tukCount = findViewById(R.id.tukCount);

        int queueLength = (Integer.parseInt(intent.getStringExtra("car")) + Integer.parseInt(intent.getStringExtra("van")) + Integer.parseInt(intent.getStringExtra("bike")) + Integer.parseInt(intent.getStringExtra("tuk")) + Integer.parseInt(intent.getStringExtra("bus")));

        String email = intent.getStringExtra("email");
        String id = intent.getStringExtra("id");
        String vehicleType = "Car";

        title.setText(intent.getStringExtra("name"));
        allCount.setText(String.valueOf(queueLength));
        dieselStock.setText(intent.getStringExtra("diesel"));
        petrolStock.setText(intent.getStringExtra("petrol"));
        carCount.setText(intent.getStringExtra("car"));
        vanCount.setText(intent.getStringExtra("van"));
        bikeCount.setText(intent.getStringExtra("bike"));
        tukCount.setText(intent.getStringExtra("tuk"));
        busCount.setText(intent.getStringExtra("bus"));

        //check for fuel availability
        Call<Station> call = ClientRetrofit.getInstance().getMyApi().getOneStations(id);

        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                if (response.code() == 200) {
                    System.out.println(response.body().getStatus());
                    if(response.body().getStatus().toLowerCase().contains("out of stock")){
                        joinQueue.setVisibility(View.GONE);
                        exitbefore.setVisibility(View.GONE);
                        exitafter.setVisibility(View.GONE);
                        return;
                    }

                } else if (response.code() == 404) {
                    joinQueue.setEnabled(false);
                    exitbefore.setEnabled(true);
                    exitafter.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
            }
        });

        //method that calls when user needs to join the queue
        joinQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateStatusModel body = new UpdateStatusModel(email,true,id,vehicleType);
                Call<Object> call = ClientRetrofit.getInstance().getMyApi().updateStatusAndCount(body);
                call.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {

                        if (response.code() != 200) {
                            Toast.makeText(QueueDetails.this, "Ops, Error occurred!", Toast.LENGTH_LONG).show();
                            joinQueue.setEnabled(true);
                            return;
                        }
                        Toast.makeText(QueueDetails.this, "Success!", Toast.LENGTH_SHORT).show();
                        joinQueue.setEnabled(false);
                        exitbefore.setEnabled(true);
                        exitafter.setEnabled(true);
                        Intent i = new Intent();
                        i.setClass(getApplicationContext(),StationListView.class).putExtra("email",email);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.e("Error", t.getMessage());

                    }
                });
            }
        });

        //method that calls when user needs to exit the queue
        exitbefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateStatusModel body = new UpdateStatusModel(email,false,id,vehicleType);
                Call<Object> call = ClientRetrofit.getInstance().getMyApi().updateStatusAndCount(body);
                call.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {

                        if (response.code() != 200) {
                            Toast.makeText(QueueDetails.this, "Ops, Error occurred!", Toast.LENGTH_LONG).show();
                            joinQueue.setEnabled(true);
                            return;
                        }
                        Toast.makeText(QueueDetails.this, "Success!", Toast.LENGTH_SHORT).show();
                        exitbefore.setEnabled(false);
                        exitafter.setEnabled(false);
                        joinQueue.setEnabled(true);
                        Intent i = new Intent();
                        i.setClass(QueueDetails.this, StationListView.class).putExtra("email",email);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.e("Error", t.getMessage());

                    }
                });
            }
        });

    }

    private void checkIsJoined() {
        Call<User> call = ClientRetrofit.getInstance().getMyApi().getUserByEmail("r");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.code() != 200) {
                    Toast.makeText(QueueDetails.this, "Ops, Error occurred!", Toast.LENGTH_LONG).show();
                    joinQueue.setEnabled(true);
                    return;
                }
                if (response.body().getIsJoined().contains("true")) {
                    Toast.makeText(QueueDetails.this, "You have already joined to another queue!", Toast.LENGTH_LONG).show();
                    joinQueue.setEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error", t.getMessage());

            }
        });
    }

    private void setExitButtonStatusonLoad() {
        Call<User> call = ClientRetrofit.getInstance().getMyApi().getUserByEmail("r");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.code() != 200) {
                    Toast.makeText(QueueDetails.this, "Ops, Error occurred!", Toast.LENGTH_LONG).show();
                    exitbefore.setEnabled(true);
                    exitafter.setEnabled(true);
                    return;
                }
                if (response.body().getIsJoined().contains("false")) {
                    exitbefore.setEnabled(false);
                    exitafter.setEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error", t.getMessage());

            }
        });
    }

}