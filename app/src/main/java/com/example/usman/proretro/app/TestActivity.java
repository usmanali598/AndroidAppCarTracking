package com.example.usman.proretro.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.usman.proretro.R;

import java.util.ArrayList;

/**
 * Created by usman on 06/04/2018.
 */

public class TestActivity extends AppCompatActivity {

    private TextView tv;
    long locationId;
    String driverId, date;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_test);

       tv = findViewById(R.id.tv1);
        locationId = getIntent().getLongExtra("locationId", 0);
        driverId = getIntent().getStringExtra("driverId");
      double lat = getIntent().getDoubleExtra("lat", 0.00);
      double lng = getIntent().getDoubleExtra("langi", 0.00);
        date = getIntent().getStringExtra("date");

        //ArrayList<Double> listDouble = (ArrayList<Double>) getIntent().getSerializableExtra("arraylist");

             //Intent intent = getIntent();
             //intent.getStringExtra("list");
//        tv.append("\n" +intent.getExtras().get("coordinates"));
        tv.append("\n"+ " - "+locationId+" - "+driverId+" - "+lat+" - "+lng+" "+date);
        //ArrayList<Double> listDouble = (ArrayList<Double>) getIntent().getSerializableExtra("arraylist");

        //tv.append("\n"+intent.getStringExtra("list"));
        //tv.append("\n"+listDouble);

    }
}
