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
import com.example.findtheq.StationUserView;
import com.example.findtheq.models.ClientRetrofit;
import com.example.findtheq.models.Station;
import com.example.findtheq.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FillingStationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FillingStationFragment extends Fragment {

    EditText id, name, ownername, phonenumber, address, password ;

    Button btnstationRegister;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FillingStationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FillingStationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FillingStationFragment newInstance(String param1, String param2) {
        FillingStationFragment fragment = new FillingStationFragment();
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
        View view = inflater.inflate(R.layout.fragment_filling_station, container, false);

        id = view.findViewById(R.id.stationid);
        name = view.findViewById(R.id.stationname);
        ownername = view.findViewById(R.id.ownername);
        phonenumber = view.findViewById(R.id.ownerphonenumber);
        address = view.findViewById(R.id.stationaddress);
        password = view.findViewById(R.id.stationpassword);

        btnstationRegister = view.findViewById(R.id.btnstationRegister);

        btnstationRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    if(TextUtils.isEmpty(id.getText().toString())){
                        id.requestFocus();
                        id.setError("Please enter valid station ID");
                    }else if(TextUtils.isEmpty(name.getText().toString())){
                        name.requestFocus();
                        name.setError("Please enter valid station name");
                    }else if(TextUtils.isEmpty(ownername.getText().toString())){
                        ownername.requestFocus();
                        ownername.setError("Please enter valid Owner Name");
                    }else if(TextUtils.isEmpty(phonenumber.getText().toString())){
                        phonenumber.requestFocus();
                        phonenumber.setError("Please enter valid phone number");
                    }else if(TextUtils.isEmpty(address.getText().toString())){
                        address.requestFocus();
                        address.setError("Please enter valid vehicle address");
                    }else if(TextUtils.isEmpty(password.getText().toString())){
                        password.requestFocus();
                        password.setError("Please enter valid password");
                    }else{
                        Station station = new Station(id.getText().toString(),
                                name.getText().toString(),
                                ownername.getText().toString(),
                                phonenumber.getText().toString(),
                                address.getText().toString(),
                                password.getText().toString()
                        );

                        registerStationSave(station);
                    }

                }catch (Exception e){
                    Toast.makeText(getActivity().getApplicationContext(),"Internal Server Error",Toast.LENGTH_SHORT).show();

                }

            }
        });


        return view;
    }

    private void registerStationSave(Station station) {
        Call<Station> call = ClientRetrofit.getInstance().getMyApi().stationRegister(station);

        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                if(response.code() == 201) {
                    Toast.makeText(getActivity().getApplicationContext(), "Register successfully" , Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getActivity(), StationUserView.class);
                    startActivity(i);
                }else if(response.code() == 400){
                    Toast.makeText(getActivity().getApplicationContext(), "Register unsuccessfully" , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}