package com.example.findtheq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = findViewById(R.id.txtViewRegister);
        register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
//                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                Intent i = new Intent(getApplicationContext(), StationUserView.class);
                startActivity(i);
            }
        });
    }
}