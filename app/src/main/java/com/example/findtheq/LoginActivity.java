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
import com.example.findtheq.models.UpdateStatusModel;
import com.example.findtheq.models.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    TextView register , stationOwnerLogin;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin = findViewById(R.id.btnLogin);
        EditText email = findViewById(R.id.useremail);
        EditText password = findViewById(R.id.password);

       signin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            try{
                if(TextUtils.isEmpty(email.getText().toString())){
                    email.requestFocus();
                    email.setError("Please enter valid username");
                }else if(TextUtils.isEmpty(password.getText().toString())){
                    password.requestFocus();
                    password.setError("Please enter valid password");
                }else{
                    User loginUser = new User(email.getText().toString(),password.getText().toString());
                    UserLogin(loginUser);
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),"Internal Server Error",Toast.LENGTH_SHORT).show();
            }
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

        stationOwnerLogin = findViewById(R.id.stationOwnerLogin);

        stationOwnerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StationOwnerLogin.class);
                //            Intent i = new Intent(getApplicationContext(), StationUserView.class);
                startActivity(i);
            }
        });

    }

    private void UserLogin(User loginUser) {
        HashMap<String, String> map = new HashMap<>();
        map.put("email" , loginUser.getEmail().toString());
        map.put("password" , loginUser.getPassword().toString());

        Call<User> call = ClientRetrofit.getInstance().getMyApi().executeLogin(map);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200) {
                    Toast.makeText(LoginActivity.this, "login successfully" , Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), StationListView.class);
                    i.putExtra("email",loginUser.getEmail());
                    i.putExtra("type", response.body().getVehicletype());
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
}