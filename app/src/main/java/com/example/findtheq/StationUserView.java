package com.example.findtheq;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.Window;
import android.widget.Toast;

import com.example.findtheq.models.ClientRetrofit;
import com.example.findtheq.models.Station;
import com.example.findtheq.models.StockModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StationUserView extends AppCompatActivity {
    TextView viewStationStationName, txtCurrentDate, arrivalTimeDisplay, finishTimeDisplay, dieselDisplay, petrolDisplay, statusDisplay;
    Button btnUpdateStock, btnUpdateTime, btnTerminate;
    TimePickerDialog timePickerDialog;
    RadioButton radioButton;
    RadioGroup radioGroup;

    private String stationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_user_view);

        String stationId = getIntent().getStringExtra("id");
        this.stationID = stationId;
        viewStationStationName = findViewById(R.id.viewStationStationName);
        txtCurrentDate = findViewById(R.id.txtCurrentDate);
        petrolDisplay = findViewById(R.id.petrolDisplay);
        dieselDisplay = findViewById(R.id.dieselDisplay);
        arrivalTimeDisplay = findViewById(R.id.arrivalTimeDisplay);
        finishTimeDisplay = findViewById(R.id.finishTimeDisplay);
        statusDisplay = findViewById(R.id.statusDisplay);
        btnUpdateStock = findViewById(R.id.btnUpdateStock);
        btnUpdateTime = findViewById(R.id.btnUpdateTime);
        btnTerminate = findViewById(R.id.btnTerminate);

        //set current date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        txtCurrentDate.setText(dtf.format(now));

        getStation(stationId);

        btnUpdateStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUpdateStockDialog();
            }
        });
        btnUpdateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUpdateTimeDialog();
            }
        });

        btnTerminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUpdateStatusDialog();
            }
        });
    }

    //method to get data from station id
    private void getStation(String stationId) {
        Call<Station> call = ClientRetrofit.getInstance().getMyApi().getOneStations(stationId);

        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                if (response.code() == 200) {

                    viewStationStationName.setText(response.body().getName());
                    petrolDisplay.setText(response.body().getStock().getPetrol().concat(" L"));
                    dieselDisplay.setText(response.body().getStock().getDiesel().concat(" L"));
                    arrivalTimeDisplay.setText(response.body().getArrivaltime());
                    finishTimeDisplay.setText(response.body().getFinishtime());
                    statusDisplay.setText(response.body().getStatus());

                } else if (response.code() == 404) {

                }

            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {


            }
        });
    }

    //Function to display the UpdateStockDialog
    void showUpdateStockDialog() {
        final Dialog dialog = new Dialog(StationUserView.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.update_stock_modal);

        String stationId = getIntent().getStringExtra("id");

        //Initializing the views of the dialog.
        final EditText updateTxtPt92 = dialog.findViewById(R.id.etPetrol);
        final EditText updateTxtD92 = dialog.findViewById(R.id.etDiesel);

        Call<Station> call = ClientRetrofit.getInstance().getMyApi().getOneStations(stationId);
        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                if (response.code() == 200) {
                    updateTxtPt92.setText(response.body().getStock().getPetrol());
                    updateTxtD92.setText(response.body().getStock().getDiesel());
                } else if (response.code() == 404) {

                }
            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {

            }
        });

        dialog.show();

        Button btnUpdateStockModal = dialog.findViewById(R.id.btnUpdateStock);
        Button btnCancelUpdateStock = dialog.findViewById(R.id.btnCancelStock);

        //this will be triggered when user touch the update button
        btnUpdateStockModal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                StockModel stockModelDieselPetrol = new StockModel(updateTxtD92.getText().toString(), updateTxtPt92.getText().toString());
                Call<Object> call2 = ClientRetrofit.getInstance().getMyApi().updateStockDetails(stationId, stockModelDieselPetrol);
                call2.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        if (response.code() == 200) {
                            Toast.makeText(getApplicationContext(), "successfully updated!", Toast.LENGTH_LONG).show();
                            getStation(stationId);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "Oops, Error occurred!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {

                    }
                });
            }
        });

        btnCancelUpdateStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //Function to display the UpdateTimeDialog.
    void showUpdateTimeDialog() {
        final Dialog dialog = new Dialog(StationUserView.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.update_stock_time_modal);

        String stationId = getIntent().getStringExtra("id");

        final EditText updateTimeArrival = dialog.findViewById(R.id.etArrivalTimeStock);
        final EditText updateTimeFinish = dialog.findViewById(R.id.etFinishTimeStock);

        Button btnUpdateArrivals = dialog.findViewById(R.id.btnUpdateTimeStock);
        Button btnCancelTimeArrivals = dialog.findViewById(R.id.btnCancelStockTime);

        Call<Station> call = ClientRetrofit.getInstance().getMyApi().getOneStations(stationId);

        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                if (response.code() == 200) {
                    updateTimeArrival.setText(response.body().getArrivaltime());
                    updateTimeFinish.setText(response.body().getFinishtime());
                } else if (response.code() == 404) {

                }
            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
            }
        });

        //to view time picker
        updateTimeArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timePickerDialog = new TimePickerDialog(StationUserView.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                updateTimeArrival.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                timePickerDialog.show();
            }
        });

        //to view time picker
        updateTimeFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timePickerDialog = new TimePickerDialog(StationUserView.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                updateTimeFinish.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                timePickerDialog.show();
            }
        });

        dialog.show();

        btnUpdateArrivals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set current date
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd");
                LocalDateTime now = LocalDateTime.now();

                Station updateArrivalFinishTime = new Station(stationId, (dtf.format(now) + "@" ).concat(updateTimeArrival.getText().toString()), (dtf.format(now) + "@" ).concat(updateTimeFinish.getText().toString()));
                Call<Object> updateTimeCall = ClientRetrofit.getInstance().getMyApi().updateTime(stationId, updateArrivalFinishTime);
                updateTimeCall.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        if (response.code() == 200) {
                            Toast.makeText(getApplicationContext(), "successfully updated!", Toast.LENGTH_LONG).show();
                            getStation(stationId);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "Oops, Error occurred!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {

                    }
                });
            }
        });

        btnCancelTimeArrivals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //Function to display the setService status dialog.
    void showUpdateStatusDialog() {

        final Dialog dialog = new Dialog(StationUserView.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.update_status_modal);

        String stationId = getIntent().getStringExtra("id");

        radioGroup = dialog.findViewById(R.id.radioGroup);
        Button btnUpdateStatus = dialog.findViewById(R.id.btnUpdateStatus);
        Button btnCancelStatus = dialog.findViewById(R.id.btnCancelStatus);

        if((statusDisplay.getText().toString().contains("Available"))){
                    radioGroup.check(R.id.radiotrue);
        }else{
                    radioGroup.check(R.id.radiofalse);
        }


        dialog.show();

        btnUpdateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = dialog.findViewById(selectedId);

                String status;

                if (selectedId == -1) {
                    Toast.makeText(StationUserView.this, "Nothing selected", Toast.LENGTH_SHORT).show();
                } else {
                    String txt = (String) radioButton.getText();
                    if (txt.toLowerCase().contains("available")) {
                        status = "Available";
                    } else {
                        status = "Out of Stock";
                    }

                    Call<Object> call2 = ClientRetrofit.getInstance().getMyApi().updateStatus(stationId, status);
                    call2.enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            if (response.code() == 200) {
                                Toast.makeText(getApplicationContext(), "successfully updated!", Toast.LENGTH_LONG).show();
                                getStation(stationId);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(getApplicationContext(), "Oops, Error occurred!", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {

                        }
                    });
                }
            }
        });

        btnCancelStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}