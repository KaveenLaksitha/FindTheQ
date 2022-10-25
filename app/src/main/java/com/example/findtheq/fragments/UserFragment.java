package com.example.findtheq.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findtheq.LoginActivity;
import com.example.findtheq.R;
import com.example.findtheq.RegisterActivity;
import com.example.findtheq.models.ClientRetrofit;
import com.example.findtheq.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    EditText customername, email,  vehicleid, nic, phonenumber, vehicletype, password;

    Button btnRegister;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user, container, false);

        customername = view.findViewById(R.id.cusname);
        email = view.findViewById(R.id.cusemail);
        vehicleid = view.findViewById(R.id.cusvehicleid);
        nic = view.findViewById(R.id.cusnic);
        phonenumber = view.findViewById(R.id.cuspno);
        vehicletype = view.findViewById(R.id.cusvehicletype);
        password = view.findViewById(R.id.cuspassword);

        btnRegister = view.findViewById(R.id.btnRegister);

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try{
                    if(TextUtils.isEmpty(customername.getText().toString())){
                        customername.requestFocus();
                        customername.setError("Please enter valid username");
                    }else if(TextUtils.isEmpty(email.getText().toString())){
                        email.requestFocus();
                        email.setError("Please enter valid email");
                    }else if(!email.getText().toString().matches(emailPattern)){
                        email.requestFocus();
                        email.setError("invalid email!");
                    }else if(TextUtils.isEmpty(vehicleid.getText().toString())){
                        vehicleid.requestFocus();
                        vehicleid.setError("Please enter valid vehicle ID");
                    }else if(TextUtils.isEmpty(nic.getText().toString())){
                        nic.requestFocus();
                        nic.setError("Please enter valid NIC");
                    }else if(TextUtils.isEmpty(phonenumber.getText().toString())){
                        phonenumber.requestFocus();
                        phonenumber.setError("Please enter valid phone number");
                    }else if(TextUtils.isEmpty(vehicletype.getText().toString())){
                        vehicletype.requestFocus();
                        vehicletype.setError("Please enter valid vehicle Type");
                    }else if(TextUtils.isEmpty(vehicletype.getText().toString())){
                        vehicletype.requestFocus();
                        vehicletype.setError("Please enter valid vehicle Type");
                    }else if(TextUtils.isEmpty(password.getText().toString())){
                        password.requestFocus();
                        password.setError("Please enter valid password");
                    }else{

                        User customer = new User(customername.getText().toString(),
                                email.getText().toString(),
                                vehicleid.getText().toString(),
                                nic.getText().toString(),
                                phonenumber.getText().toString(),
                                vehicletype.getText().toString(),
                                password.getText().toString(),
                                "false");

                        System.out.println( "data coming" + btnRegister);
                        registerUserSave(customer);
                    }

                }catch (Exception e){
                    Toast.makeText(getActivity().getApplicationContext(),"Internal Server Error",Toast.LENGTH_SHORT).show();

                }
            }
        });
        return  view;
    }


    public void registerUserSave(User customer) {

        Call<User> call = ClientRetrofit.getInstance().getMyApi().executeRegister(customer);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 201) {
                    Toast.makeText(getActivity().getApplicationContext(), "Register successfully" , Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    startActivity(i);
                }else if(response.code() == 400){
                    Toast.makeText(getActivity().getApplicationContext(), "Register unsuccessfully" , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}