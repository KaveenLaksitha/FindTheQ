package com.example.findtheq;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.Window;

public class StationUserView extends AppCompatActivity {

    Button btnUpdateStock, btnUpdateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_user_view);

        btnUpdateStock = findViewById(R.id.btnUpdateStock);
        btnUpdateTime = findViewById(R.id.btnUpdateTime);

        btnUpdateStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUpdateStockDialog();
            }
        });
//        btnUpdateTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showUpdateStockDialog();
//            }
//        });
    }
        //Function to display the custom dialog.
        void showUpdateStockDialog() {
            final Dialog dialog = new Dialog(StationUserView.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.update_stock_modal);

            //Initializing the views of the dialog.
            final EditText updateTxtPt92 = dialog.findViewById(R.id.updateTxtPt92);
            final EditText updateTxtD92 = dialog.findViewById(R.id.updateTxtD92);
            Button btnUpdateStockModal = dialog.findViewById(R.id.btnUpdateStockModal);
            Button btnCancelUpdateStock = dialog.findViewById(R.id.btnCancelUpdateStock);


            btnUpdateStockModal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String txtPt92 = updateTxtPt92.getText().toString();
                    String txtD92 = updateTxtD92.getText().toString();

                    dialog.dismiss();
                }
            });

            btnCancelUpdateStock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }

}