package com.example.findtheq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findtheq.fragments.UserFragment;
import com.example.findtheq.models.ClientRetrofit;
import com.example.findtheq.models.User;
import com.example.findtheq.service.UserClient;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {

    TextView register;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin = findViewById(R.id.btnLogin);
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

       signin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               HashMap<String, String> map = new HashMap<>();
               map.put("customername" , username.getText().toString());
               map.put("password" , password.getText().toString());

               Call<User> call = ClientRetrofit.getInstance().getMyApi().executeLogin(map);

               call.enqueue(new Callback<User>() {
                   @Override
                   public void onResponse(Call<User> call, Response<User> response) {
                       if(response.code() == 200) {
                           Toast.makeText(LoginActivity.this, "login successfully" , Toast.LENGTH_LONG).show();
                           Intent i = new Intent(getApplicationContext(), QueueDetails.class);
                           startActivity(i);
                       }else if(response.code() == 404){
                           Toast.makeText(LoginActivity.this, "login unsuccessfully" , Toast.LENGTH_LONG).show();
                       }

                   }

                   @Override
                   public void onFailure(Call<User> call, Throwable t) {
                       Toast.makeText(LoginActivity.this, t.getMessage(),
                               Toast.LENGTH_LONG).show();

                   }
               });
           }
       });

        register = findViewById(R.id.txtViewRegister);

        register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
               Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
  //            Intent i = new Intent(getApplicationContext(), StationUserView.class);
                startActivity(i);
            }
        });
    }
}