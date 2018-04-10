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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usman.proretro.R;
import com.example.usman.proretro.interfaces.Api;
import com.example.usman.proretro.models.Location;
import com.example.usman.proretro.models.User;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        final EditText userName = (EditText) findViewById(R.id.username);
        final EditText passworD = (EditText) findViewById(R.id.password);
        final EditText nave = (EditText) findViewById(R.id.name);
        final EditText evail = (EditText) findViewById(R.id.email);

        Button editin = (Button) findViewById(R.id.btEdt);
        // Button addAdd = (Button) findViewById(R.id.add);
        Button userS = (Button) findViewById(R.id.user);
        Button addFu = (Button) findViewById(R.id.btnAddFuel);
        Button deleteBt = (Button) findViewById(R.id.deleteButton);

        userS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, MainActivity.class));
            }
        });
        Button fuelS = (Button) findViewById(R.id.fuel);
        fuelS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, FuelActivity.class));
            }
        });

        editin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, UpdateFuel.class));
            }
        });
        deleteBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, DeleteFuelActivity.class));
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



        Intent i = new Intent(getApplicationContext(), ServiceClass.class);
        startService(i);
    }

    private boolean runtime_permissions() {
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
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd ");
                    String strDate = sdf.format(calendar.getTime());

                    Location loca = new Location("1",lat, langi, strDate);
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
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.65:8080/")
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

    }
}
       /* @Override
        public void onLocationChanged(Location location) {

            lat = location.getLatitude();
            lng = location.getLongitude();

            if (mMap != null){
                LatLng position = new LatLng(lat, lng);
                Toast.makeText(this, "LocationChanged: "+location.getLatitude()+" - "+location.getLongitude(), Toast.LENGTH_SHORT).show();

                // Showing the current location in Google Map
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat,lng)));

                //double langi = location.getLongitude();

                Location loca = new Location(lat, lng);
                sendNetworkRequest(loca);

                // Zoom in the Google Map
                mMap.animateCamera(CameraUpdateFactory.zoomTo(25));
                if (routeOpts!=null) {
                    LatLng newPoint = new LatLng(location.getLatitude(), location.getLongitude());
                    List<LatLng> points = route.getPoints();
                    points.add(newPoint);
                    route.setPoints(points);
                }
            }*/


  //  }
//-----------------------------------------------------------------

       /* addAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = new User(
                        userName.getText().toString(),
                        passworD.getText().toString(),
                        nave.getText().toString(),
                        evail.getText().toString()
                );
              //  sendNetworkRequest(user);
            }
        });*/

       /* private void sendNetworkRequest(User user) {
        //Heroku server is being used so it was only for localhost
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.103:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Api apiService = retrofit.create(Api.class);
            Call<List<User>> call = apiService.addUsers(user);
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    Toast.makeText(FirstActivity.this, "Here is:" + response.body(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Toast.makeText(FirstActivity.this, "Something stupid happend", Toast.LENGTH_SHORT).show();
                }
            });


        }*/

