package com.example.usman.proretro.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usman.proretro.R;
import com.example.usman.proretro.interfaces.Api;
import com.example.usman.proretro.models.Location;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by usman on 04/04/2018.
 */

public class LocationActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Intent in = getIntent();

        toolbar = (Toolbar) findViewById(R.id.refresh);
        getSupportActionBar().setTitle("Location List");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView listView = (ListView) findViewById(R.id.location_list);

       // Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fuel-hero.herokuapp.com/")
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.65:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api apiService = retrofit.create(Api.class);

        Call<List<Location>> call = apiService.getLocations();


        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
               // Toast.makeText(LocationActivity.this, "Fetching Location", Toast.LENGTH_SHORT).show();

                List<Location> locLis = response.body();
                Toast.makeText(LocationActivity.this, "Size : "+locLis.size(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(LocationActivity.this, ""+locLis.get(0), Toast.LENGTH_SHORT).show();
                listView.setAdapter(new LocationAdapter(LocationActivity.this, locLis));
              /*  for(int i=1;i<=locLis.size();i++){
                   double lat = getIntent().getDoubleExtra("lat", 0.00);
                    double lng = getIntent().getDoubleExtra("langi", 0.00);
                    Log.d( "onResponse1: ", String.valueOf(lat+" - "+lng));
                    // Toast.makeText(MapsActivity.this, ""+lat+lng, Toast.LENGTH_SHORT).show();


                }*/


            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Toast.makeText(LocationActivity.this, "Check if retrieving or refresh", Toast.LENGTH_SHORT).show();
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

        //String title = (String)item.getTitle();
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
