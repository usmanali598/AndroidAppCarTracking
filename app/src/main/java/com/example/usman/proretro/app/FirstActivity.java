package com.example.usman.proretro.app;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.usman.proretro.R;
import com.example.usman.proretro.interfaces.Api;
import com.example.usman.proretro.models.Location;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by usman on 15/03/2018.
 */

public class FirstActivity extends AppCompatActivity {

    //private BroadcastReceiver broadcastReceiver;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button addFu = (Button) findViewById(R.id.btnAddFuel);
        Button fuelS = (Button) findViewById(R.id.fuel);
        //String nem = getIntent().getStringExtra("nameForMap");

        toolbar = (Toolbar)findViewById(R.id.refresh);
        getSupportActionBar().setTitle("Menu");

//        String ides = (String) getIntent().getExtras().get("idMap");
      //  Toast.makeText(this, ""+ides, Toast.LENGTH_SHORT).show();
        fuelS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, FuelActivity.class));
            }
        });

        addFu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, AddFuelActivity.class));
            }
        });

        Button locat = (Button)findViewById(R.id.btLoc);
        locat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, LocationActivity.class));
            }
        });
        Button maps = (Button)findViewById(R.id.btMaps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, MapsActivity.class));
            }
        });
        Button locates = (Button) findViewById(R.id.btnLocate);
        locates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, MapsServiceless.class));
            }
        });
        Button serv = (Button) findViewById(R.id.btnService);
        serv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, IdFieldActivity.class));
            }
        });
      /*  Intent i = new Intent(getApplicationContext(), ServiceClass.class);
        startService(i);*/
    }

  /*  private boolean runtime_permissions() {
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);

            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
            } else {
                runtime_permissions();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (broadcastReceiver == null) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    double lat = (double) intent.getExtras().get("lat");
                    double langi = (double) intent.getExtras().get("lang");

                    Calendar calendar = Calendar.getInstance();
                   // SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd ");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy ");
                    String strDate = sdf.format(calendar.getTime());

                    //String driveId = getIntent().getStringExtra("idForMap");
                    String driveId = (String) getIntent().getExtras().get("idMap");
                    Location loca = new Location(driveId, lat, langi, strDate);
                    sendNetworkRequest(loca);
                }
            };
        }
        registerReceiver(broadcastReceiver, new IntentFilter("location_update"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    private void sendNetworkRequest(Location location) {

      //  Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fuel-hero.herokuapp.com/")
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fuel-hero.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Location>> call = api.addLocations(location);
        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                Toast.makeText(FirstActivity.this, "sendingLoc in db", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Toast.makeText(FirstActivity.this, "check if goes to db", Toast.LENGTH_SHORT).show();
            }
        });

    }*/


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
        }

        return true;
    }
}
