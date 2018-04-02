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


public class DeleteFuelActivity extends AppCompatActivity {

    private EditText deletefuelId;
    private Button deleteButton;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_fuel);

        Intent in = getIntent();

        String ids = String.valueOf(in.getLongExtra("fuelId", 0));


        deletefuelId = (EditText) findViewById(R.id.etIdFuel);
        deleteButton = (Button) findViewById(R.id.btDelBtn);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteNetworkRequest((long) Integer.parseInt(deletefuelId.getText().toString()));
            }
        });

        deletefuelId.setText(ids);
    }

    private void deleteNetworkRequest(Long id) {

       Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fuel-hero.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api apiService = retrofit.create(Api.class);


        Call<Fuel> call = apiService.deleteFuels(id);


        call.enqueue(new Callback<Fuel>() {
            @Override
            public void onResponse(Call<Fuel> call, Response<Fuel> response) {
                Toast.makeText(DeleteFuelActivity.this, "Deleted :)", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DeleteFuelActivity.this, FuelActivity.class));
            }

            @Override
            public void onFailure(Call<Fuel> call, Throwable t) {
                Toast.makeText(DeleteFuelActivity.this, "Sorry :(", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DeleteFuelActivity.this, FuelActivity.class));
            }
        });
    }
}