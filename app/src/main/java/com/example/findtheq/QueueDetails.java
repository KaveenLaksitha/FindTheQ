package com.example.findtheq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QueueDetails extends AppCompatActivity {

    TextView carCount , vanCount , busCount, bikeCount,allCount  ;
    Button joinQueue , exitbefore , exitafter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_details);
    }



}