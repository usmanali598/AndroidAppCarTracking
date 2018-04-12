package com.example.usman.proretro.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.usman.proretro.R;
import com.example.usman.proretro.interfaces.Api;
import com.example.usman.proretro.models.Fuel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by usman on 29/03/2018.
 */

public class AddFuelActivity extends AppCompatActivity {
    private EditText User;
    private EditText Amount;
    private EditText Litres;
    private EditText DateFuel;
    private Button AddFuel;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel);

        getSupportActionBar().setTitle("Add Fuels");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent in = getIntent();

        User = (EditText) findViewById(R.id.etUserperson);
        Amount = (EditText) findViewById(R.id.etAmount);
        Litres = (EditText) findViewById(R.id.etLitre);
        DateFuel = (EditText) findViewById(R.id.dateFuel);
        AddFuel = (Button) findViewById(R.id.btFuel);

        AddFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fuel fuel = new Fuel(
                        User.getText().toString(),
                        Amount.getText().toString(),
                        Litres.getText().toString(),
                        DateFuel.getText().toString()
                );
                sendNetworkRequest(fuel);
            }
        });
    }
    private void sendNetworkRequest(Fuel fuel) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fuel-hero.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api apiService = retrofit.create(Api.class);
        Call<List<Fuel>> call = apiService.addFuels(fuel);
        call.enqueue(new Callback<List<Fuel>>() {
            @Override
            public void onResponse(Call<List<Fuel>> call, Response<List<Fuel>> response) {
                startActivity(new Intent(AddFuelActivity.this, FuelActivity.class));
            }

            @Override
            public void onFailure(Call<List<Fuel>> call, Throwable t) {
                startActivity(new Intent(AddFuelActivity.this, FuelActivity.class));
            }
        });
    }
}
