package com.example.usman.proretro.app;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.usman.proretro.R;
import com.example.usman.proretro.interfaces.Api;
import com.example.usman.proretro.models.Location;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import android.support.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.security.AccessController.getContext;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Toolbar toolbar;
    private Polyline route = null;
    private PolylineOptions routeOpts = null;
    private boolean drawTrack = true;
    long locationId;
    String driverId, date;
    double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        toolbar = (Toolbar) findViewById(R.id.refresh);
        /*setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getActionBar().getDisplayOptions();*/
       // getActionBar().setDisplayHomeAsUpEnabled(true);

      String respo = getIntent().getStringExtra("respo");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fuel-hero.herokuapp.com/")
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fuel-hero.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api apiService = retrofit.create(Api.class);

        Call<List<Location>> call = apiService.getLocations();

        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {

//                List<Location> Latlng = (List<Location>) getIntent().getExtras().get("lat");
              List<Location> locLis = response.body();

                Toast.makeText(MapsActivity.this, ""+locLis.size(), Toast.LENGTH_SHORT).show();
                lat = getIntent().getDoubleExtra("lat", 0);
                lng = getIntent().getDoubleExtra("langi", 0);

                if (locLis.size() > 0) {
                    LatLng Helsini = new LatLng(locLis.get(1).getLat(), locLis.get(1).getLangi());
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Helsini, 15));
                } else {
                    LatLng Helsini = new LatLng(60.172503, 24.939974);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Helsini, 15));
                }

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy ");
                String strDate = sdf.format(calendar.getTime());


                //filterPolyLi("1",  "10/04/2018", locLis, getResources().getColor(R.color.colorAccent));
                filterPolyLi("",  strDate, locLis,getResources().getColor(R.color.colorAccent), "ALI");
                //filterPolyLi("2",  "09/04/2018", locLis, getResources().getColor(R.color.colorPrimaryDark));
               // filterPolyLi("1",  "2018 / 04 / 10 ", locLis, getResources().getColor(R.color.colorPrimaryDark));
        }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Toast.makeText(MapsActivity.this, "Check if retrieving or refresh", Toast.LENGTH_SHORT).show();
            }
        });
        mMap.getCameraPosition();
    }

    public void filterPolyLi(String id, String date, List<Location> locLis, int colr, String name){

        /*LatLng starting = new LatLng(locLis.get(0).getLat(), locLis.get(0).getLangi());
        int end = locLis.size()-1;
        LatLng ending = new LatLng(locLis.get(end).getLat(), locLis.get(end).getLangi());*/

            List<LatLng> latlngs = new ArrayList<>();

            for(int i=0; i<locLis.size(); i++){
                if ((locLis.get(i).getDriverId().equalsIgnoreCase(id)) && (locLis.get(i).getDate().equalsIgnoreCase(date)) ) {
                    latlngs.add(new LatLng(locLis.get(i).getLat(), locLis.get(i).getLangi()));
                    mMap.addMarker(new MarkerOptions().position(new LatLng(locLis.get(i).getLat(), locLis.get(i).getLangi()))
                            .title(name+" "+id)
                            .snippet(""+date)
                    );
                }
            }
            PolylineOptions rectOptions = new PolylineOptions().addAll(latlngs);

            rectOptions.color(colr).width(5)
                    .geodesic(true);
            mMap.addPolyline(rectOptions);
       // mMap.addMarker(new MarkerOptions().position(starting).title("Marker in beginnig"));
        //mMap.addMarker(new MarkerOptions().position(ending).title("Marker in ending"));
        }
}
