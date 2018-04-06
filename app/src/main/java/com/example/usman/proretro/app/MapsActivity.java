package com.example.usman.proretro.app;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.usman.proretro.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Toolbar toolbar;

    private Polyline route = null;
    private PolylineOptions routeOpts = null;
    private boolean drawTrack = true;
    long locationId;
    String driverId, date;
    double lat, lng;

    //Intent ina = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //String ids = String.valueOf(getIntent().getLongExtra("locationId", 0));
        locationId = getIntent().getLongExtra("locationId", 0);
        driverId = getIntent().getStringExtra("driverId");
        lat = getIntent().getDoubleExtra("lat", 12.12);
        lng = getIntent().getDoubleExtra("langi", 20.12);
        Toast.makeText(this, ""+driverId, Toast.LENGTH_SHORT).show();
      //  double lat = getIntent().getDoubleExtra("lat", 0);
        //double lng = getIntent().getDoubleExtra("langi", 0);
        date = getIntent().getStringExtra("date");

        Toast.makeText(this, ""+locationId+driverId+lat+lng+date, Toast.LENGTH_SHORT).show();


       mMap.addMarker(new MarkerOptions()
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
        );



        // LatLng position = new LatLng(lat, lng);
       // Toast.makeText(this, ""+lat+lng, Toast.LENGTH_SHORT).show();
        routeOpts = new PolylineOptions().color(Color.BLUE)
                .width(3)
                .geodesic(true);
        route = mMap.addPolyline(routeOpts);
        route.setVisible(drawTrack);


        if (routeOpts!=null) {
            LatLng newPoint = new LatLng(lat, lng);
            List<LatLng> points = route.getPoints();
            points.add(newPoint);
            route.setPoints(points);
        }

        // Add a marker in Sydney and move the camera
       /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}
