package com.example.findtheq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findtheq.models.ClientRetrofit;
import com.example.findtheq.models.Station;
import com.example.findtheq.models.User;

import java.security.acl.Owner;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StationOwnerLogin extends AppCompatActivity {

    TextView register;
    Button stationOwnerbtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_login);

        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        stationOwnerbtnLogin = findViewById(R.id.stationOwnerbtnLogin);
        EditText id = findViewById(R.id.ownerStationId);
        EditText password = findViewById(R.id.stationOwnerPassword);

        stationOwnerbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (TextUtils.isEmpty(id.getText().toString())) {
                        id.requestFocus();
                        id.setError("Please enter valid station ID");
                    } else if (TextUtils.isEmpty(password.getText().toString())) {
                        password.requestFocus();
                        password.setError("Please enter valid password");
                    } else {
                        Station loginUser = new Station(id.getText().toString(), password.getText().toString());
//                        OwnerLogin(loginUser);
                        OwnerLogin(id.getText().toString(), password.getText().toString());
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //    private void OwnerLogin(Station loginUser) {
    private void OwnerLogin(String id, String password) {

        HashMap<String, String> map = new HashMap<>();
//        map.put("id" , loginUser.getId().toString());
//        map.put("password" , loginUser.getPassword().toString());

        map.put("id", id);
        map.put("password", password);

        Call<Station> call = ClientRetrofit.getInstance().getMyApi().executeLoginStationOwner(map);

        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                if (response.code() == 200) {
                    Toast.makeText(StationOwnerLogin.this, "login successfully", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), StationUserView.class);
//                    i.putExtra("id", loginUser.getId().toString());
                    i.putExtra("id", id);
                    startActivity(i);
                } else if (response.code() == 404) {
                    Toast.makeText(StationOwnerLogin.this, "login unsuccessfully", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
                Toast.makeText(StationOwnerLogin.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}