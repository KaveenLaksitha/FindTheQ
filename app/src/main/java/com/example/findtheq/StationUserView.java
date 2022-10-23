package com.example.findtheq;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.Window;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class StationUserView extends AppCompatActivity {

    Button btnUpdateStock, btnUpdateTime, btnTerminate;
    TimePickerDialog timePickerDialog;
    RadioButton genderradioButton;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_user_view);

        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }

        btnUpdateStock = findViewById(R.id.btnUpdateStock);
        btnUpdateTime = findViewById(R.id.btnUpdateTime);
        btnTerminate = findViewById(R.id.btnTerminate);

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
        //Function to display the UpdateStockDialog
        void showUpdateStockDialog() {
            final Dialog dialog = new Dialog(StationUserView.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.update_stock_modal);

            //Initializing the views of the dialog.
            final EditText updateTxtPt92 = dialog.findViewById(R.id.etPetrol);
            final EditText updateTxtD92 = dialog.findViewById(R.id.etDiesel);
            Button btnUpdateStockModal = dialog.findViewById(R.id.btnUpdateStock);
            Button btnCancelUpdateStock = dialog.findViewById(R.id.btnCancelStock);

            dialog.show();

            btnUpdateStockModal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
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

        final EditText updateTimeArrival = dialog.findViewById(R.id.etArrivalTimeStock);
        final EditText updateTimeFinish = dialog.findViewById(R.id.etFinishTimeStock);
        Button btnUpdateArrivals = dialog.findViewById(R.id.btnUpdateTimeStock);
        Button btnCancelTimeArrivals = dialog.findViewById(R.id.btnCancelStockTime);

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
                dialog.dismiss();
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

        radioGroup=dialog.findViewById(R.id.radioGroup);
        Button btnUpdateStatus = dialog.findViewById(R.id.btnUpdateStatus);
        Button btnCancelStatus = dialog.findViewById(R.id.btnCancelStatus);

        dialog.show();

        btnUpdateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                genderradioButton = dialog.findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(StationUserView.this,"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    String txt = (String) genderradioButton.getText();
                    Boolean status;
                    if(txt.toLowerCase().contains("active")){
                        status = true;
                    }else{
                        status = false;
                    }
                    System.out.println("status >>>>" + status);
                    Toast.makeText(StationUserView.this,genderradioButton.getText(), Toast.LENGTH_SHORT).show();
                }
//                dialog.dismiss();
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