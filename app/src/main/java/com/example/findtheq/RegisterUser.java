package com.example.findtheq;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterUser extends AppCompatActivity {

        Spinner spinner;
        String[] vehicleType = {"Car" , "Van" , "Bus", "Bike" , "Tractor" , "Other"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_user);
            spinner = findViewById(R.id.cusvehicletype);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterUser.this, android.R.layout.simple_spinner_item, vehicleType);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String value = parent.getItemAtPosition(position).toString();
                    Toast.makeText(RegisterUser.this, value, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

