package com.example.usman.proretro.app;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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


      String respo = getIntent().getStringExtra("respo");

      //Helsinki lat and lng 60.192059, 24.945831


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fuel-hero.herokuapp.com/")
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.65:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api apiService = retrofit.create(Api.class);

        Call<List<Location>> call = apiService.getLocations();

        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                Toast.makeText(MapsActivity.this, "Fetching Location", Toast.LENGTH_SHORT).show();

              List<Location> locLis = response.body();

               // Toast.makeText(MapsActivity.this, ""+locLis.toString(), Toast.LENGTH_SHORT).show();

               // Log.d("response : ", String.valueOf(response.body()));

                //Toast.makeText(LocationActivity.this, "Size : "+locLis.size(), Toast.LENGTH_SHORT).show();

                LocationAdapter lca = new LocationAdapter(MapsActivity.this, locLis);


                Log.d("onResponse: ", String.valueOf(lca));
                //setAdapter(new LocationAdapter(MapsActivity.this, locLis));
              routeOpts = new PolylineOptions().color(Color.BLUE)
                        .width(3)
                        .geodesic(true);
                route = mMap.addPolyline(routeOpts);
                route.setVisible(drawTrack);

                        for(int i=1;i<=locLis.size();i++){
                            lat = getIntent().getDoubleExtra("lat", 0.00);
                            lng = getIntent().getDoubleExtra("langi", 0.00);
                            Log.d( "onResponse1: ", String.valueOf(lat+lng));
                           // Toast.makeText(MapsActivity.this, ""+lat+lng, Toast.LENGTH_SHORT).show();


                        }

                mMap.addPolyline(new PolylineOptions().geodesic(true)
                        .add(new LatLng(lat, lng))
                        .clickable(true)
                        .width(10)
                        .color(Color.GREEN));
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Toast.makeText(MapsActivity.this, "Check if retrieving or refresh", Toast.LENGTH_SHORT).show();
            }
        });

        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0), new LatLng(60.192059, 24.945831) )
                .width(5)
                .color(Color.RED));
        //String ids = String.valueOf(getIntent().getLongExtra("locationId", 0));
       /* locationId = getIntent().getLongExtra("locationId", 0);
        driverId = getIntent().getStringExtra("driverId");
        lat = getIntent().getDoubleExtra("lat", 12.12);
        lng = getIntent().getDoubleExtra("langi", 20.12);
        Toast.makeText(this, ""+driverId, Toast.LENGTH_SHORT).show();
      //  double lat = getIntent().getDoubleExtra("lat", 0);
        //double lng = getIntent().getDoubleExtra("langi", 0);
        date = getIntent().getStringExtra("date");

        Toast.makeText(this, ""+locationId+driverId+lat+lng+date, Toast.LENGTH_SHORT).show();*/


     /*   mMap.addMarker(new MarkerOptions()
                .position(new LatLng(60.198958, 24.932931))
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(60.172503, 24.939974))
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

        // Polylines are useful for marking paths and routes on the map.
        mMap.addPolyline(new PolylineOptions().geodesic(true)

                .add(new LatLng(60.172503, 24.939974))
                .add(new LatLng(60.198958, 24.932931))
                .add(new LatLng(60.179890, 24.960942))
                .add(new LatLng(60.172503, 24.939974))
                .clickable(true)
                .width(7)
                .color(Color.GREEN)
        );*/

        // LatLng position = new LatLng(lat, lng);

        routeOpts = new PolylineOptions().color(Color.BLUE)
                .width(3)
                .geodesic(true);
        route = mMap.addPolyline(routeOpts);
        route.setVisible(drawTrack);


     if (routeOpts!=null) {
            lat = getIntent().getDoubleExtra("lat", 0.00);
            lng = getIntent().getDoubleExtra("langi", 0.00);

            LatLng newPoint = new LatLng(lat, lng);
            List<LatLng> points = route.getPoints();
            points.add(newPoint);
            route.setPoints(points);
        }


        mMap.getCameraPosition();

       /*     mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.car))
                    .anchor(0.0f, 1.0f)
                    .title("Your current location")
                    .snippet("This is a car")
                    .position(position));
            //Toast.makeText(this, "MapReady Function called", Toast.LENGTH_SHORT).show();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 17));
*/
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(60.172503, 24.939974);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


}
