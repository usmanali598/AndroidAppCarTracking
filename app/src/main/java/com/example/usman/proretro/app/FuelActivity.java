package com.example.usman.proretro.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
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
 * Created by usman on 15/03/2018.
 */

public class FuelActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuel_activity);

        Intent in = getIntent();

        toolbar = (Toolbar) findViewById(R.id.refresh);
        getSupportActionBar().setTitle("Fuel List");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView listView = (ListView) findViewById(R.id.fuel_list);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fuel-hero.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api apiService = retrofit.create(Api.class);

        Call<List<Fuel>> call = apiService.getFuels();


        call.enqueue(new Callback<List<Fuel>>() {
            @Override
            public void onResponse(Call<List<Fuel>> call, Response<List<Fuel>> response) {

                List<Fuel> fulLis = response.body();

                listView.setAdapter(new FuelAdapter(FuelActivity.this, fulLis));
            }

            @Override
            public void onFailure(Call<List<Fuel>> call, Throwable t) {

                Toast.makeText(FuelActivity.this, "error :( "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.get_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String title = (String)item.getTitle();
        //Toast.makeText(this, "title is: "+title, Toast.LENGTH_SHORT).show();

        switch (item.getItemId()){
            case R.id.refresh:
                Toast.makeText(this, "Refreshed :)", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

        }

        return true;
    }
}
