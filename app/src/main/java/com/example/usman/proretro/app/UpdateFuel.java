package com.example.usman.proretro.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.usman.proretro.R;
import com.example.usman.proretro.interfaces.Api;
import com.example.usman.proretro.models.Fuel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by usman on 29/03/2018.
 */

public class UpdateFuel extends AppCompatActivity{

    private EditText id;
    private EditText updateUserPerson;
    private EditText updateAmount;
    private EditText updateLitres;
    private EditText updateDate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent ina = getIntent();
        long getid = 0;
        String userP = null, amount = null, lit = null, date = null;

        Bundle extras = getIntent().getExtras();
        /*if (extras != null) {
             getid = extras.getLong("fuelId",0);
             userP = extras.getString("userPerson", "");
             amount = extras.getString("amount", "");
             lit = extras.getString("litre", "");
             date = extras.getString("date", "");
        }*/
        id = (EditText) findViewById(R.id.etId);
        updateUserPerson = (EditText) findViewById(R.id.upUserPerson);
        updateAmount = (EditText) findViewById(R.id.upAmount);
        updateLitres = (EditText) findViewById(R.id.upLitre);
        updateDate = (EditText) findViewById(R.id.upDate);
        
       // id.setText(ina.getExtras().getLong("fuelId"));
        String ids = String.valueOf(ina.getLongExtra("fuelId", 0));
        id.setText(ids);
        updateUserPerson.setText(ina.getStringExtra("userPerson"));
        updateAmount.setText(ina.getStringExtra("amount"));
        updateLitres.setText(ina.getStringExtra("litres"));
        updateDate.setText(ina.getStringExtra("date"));

        Button updates = (Button) findViewById(R.id.upFuel);


        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fuel-hero.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Api apiService = retrofit.create(Api.class);

        updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String person = updateUserPerson.getText().toString();
                String amt = updateAmount.getText().toString();
                String ltr = updateLitres.getText().toString();
                String dte = updateDate.getText().toString();


                Fuel fuel = new Fuel(person, amt, ltr, dte);

                // Call<Fuel> call = apiService.editFuels(7, fuel);
                Call<Fuel> call = apiService.editFuels(Integer.parseInt(id.getText().toString()), fuel);
                call.enqueue(new Callback<Fuel>() {
                    @Override
                    public void onResponse(Call<Fuel> call, Response<Fuel> response) {
                        Toast.makeText(UpdateFuel.this, "Yay", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Fuel> call, Throwable t) {

                    }
                });
            }

        });
    }}