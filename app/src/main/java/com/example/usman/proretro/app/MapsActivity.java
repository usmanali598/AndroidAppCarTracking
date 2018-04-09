package com.example.usman.proretro.app;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
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

import java.util.ArrayList;
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

      String respo = getIntent().getStringExtra("respo");

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

//                List<Location> Latlng = (List<Location>) getIntent().getExtras().get("lat");
              List<Location> locLis = response.body();

                lat = getIntent().getDoubleExtra("lat", 0);
                lng = getIntent().getDoubleExtra("langi", 0);

             /*   routeOpts = new PolylineOptions().color(Color.BLUE)
                        .width(3)
                        .geodesic(true);
                route = mMap.addPolyline(routeOpts);
                route.setVisible(drawTrack);*/

                //Put in a method with params (id,  colour) or date as well;
                //If (Id.equalsIgnoreCase('id') && date.equalsIgnoreCase('date'))

                /*
                response.body().get(1).getDriverId();
                response.body().get(1).getDate();

                List<LatLng> latlngs = new ArrayList<>();
                List<Location> polyTry = new ArrayList<>();
                for(int i=0; i<locLis.size(); i++){

                   driverId = getIntent().getStringExtra("driverId");
                   double latu = locLis.get(i).getLat();
                   double langu = locLis.get(i).getLangi();
                   date = getIntent().getStringExtra("date");

                   Location loc = new Location(driverId, latu, langu, date);
                    polyTry.add(loc);
                   latlngs.add(new LatLng(latu, langu));
                }
                PolylineOptions rectOptions = new PolylineOptions().addAll(latlngs);

                rectOptions.color(Color.BLUE).width(5)
                        .geodesic(true);
                mMap.addPolyline(rectOptions);*/


                filterPolyLi("1",  "08/04/2018", locLis, getResources().getColor(R.color.colorAccent));
                filterPolyLi("2",  "09/04/2018", locLis, getResources().getColor(R.color.colorPrimaryDark));


               /* List<LatLng> latlngs = new ArrayList<>();
                for(int i=0; i<locLis.size(); i++){
                    if ((locLis.get(i).getDriverId().equalsIgnoreCase("1")) && (locLis.get(i).getDate().equalsIgnoreCase("08/04/2018")) ) {
                        latlngs.add(new LatLng(locLis.get(i).getLat(), locLis.get(i).getLangi()));
                    }
                }
                PolylineOptions rectOptions = new PolylineOptions().addAll(latlngs);

                rectOptions.color(Color.WHITE).width(5)
                        .geodesic(true);
                mMap.addPolyline(rectOptions);*/


                   //correct one
              /*  List<LatLng> latlngs = new ArrayList<>();
                for(int i=0; i<locLis.size(); i++){
                   latlngs.add(new LatLng(locLis.get(i).getLat(), locLis.get(i).getLangi()));
                }
                PolylineOptions rectOptions = new PolylineOptions().addAll(latlngs);

                rectOptions.color(Color.BLUE).width(5)
                        .geodesic(true);
                mMap.addPolyline(rectOptions);*/


                //Randomly select color for different Ids
                //get Lat Lang from different dates
                //today date, yesterday is to0day-1;
           /*     for(int i=0;i<locLis.size();i++) {
                    Polyline line = mMap.addPolyline(new PolylineOptions()
                            .add(new LatLng(locLis.get(i).getLat(), locLis.get(i).getLangi()))
                            .width(9)
                            .color(Color.RED));
                }*/

             /*   Polyline line = mMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(locLis.get(0).getLat(), locLis.get(0).getLangi())
                                , new LatLng(locLis.get(1).getLat(), locLis.get(1).getLangi())
                                , new LatLng(locLis.get(2).getLat(), locLis.get(2).getLangi())
                                ,new LatLng(locLis.get(3).getLat(), locLis.get(3).getLangi()))
                        .width(5)
                        .color(Color.RED));*/



              /*  for(int i=0;i<=locLis.size();i=i+1) {
                    //Toast.makeText(MapsActivity.this, "" + lat + "- " + lng + " - ", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(MapsActivity.this, "again"+locLis.listIterator().hasNext(), Toast.LENGTH_SHORT).show(); //True
                 Toast.makeText(MapsActivity.this, ""+locLis.get(0).getLat()+" + "+locLis.get(0).getLangi(), Toast.LENGTH_LONG).show();
                 Toast.makeText(MapsActivity.this, ""+locLis.get(1).getLat()+" + "+locLis.get(1).getLangi(), Toast.LENGTH_LONG).show();
                 Toast.makeText(MapsActivity.this, ""+locLis.get(2).getLat()+" + "+locLis.get(2).getLangi(), Toast.LENGTH_LONG).show();
                 Toast.makeText(MapsActivity.this, ""+locLis.get(3).getLat()+" + "+locLis.get(3).getLangi(), Toast.LENGTH_LONG).show();

                //setAdapter(new LocationAdapter(MapsActivity.this, locLis));
              routeOpts = new PolylineOptions().color(Color.BLUE)
                        .width(3)
                        .geodesic(true);
                route = mMap.addPolyline(routeOpts);
                route.setVisible(drawTrack);

                       /* for(int i=1;i<=locLis.size();i++){
                            lat = getIntent().getDoubleExtra("lat", 0);
                            lng = getIntent().getDoubleExtra("langi", 0);
                            Log.d( "onResponse1: ", String.valueOf(lat+lng));
                           // Toast.makeText(MapsActivity.this, ""+lat+lng, Toast.LENGTH_SHORT).show();
                        }*/
        }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Toast.makeText(MapsActivity.this, "Check if retrieving or refresh", Toast.LENGTH_SHORT).show();
            }
        });


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
        LatLng Helsinki = new LatLng(60.172503, 24.939974);
        //mMap.addMarker(new MarkerOptions().position(Helsinki).title("Marker in Helsinki"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Helsinki));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Helsinki, 8));
    }

    public void filterPolyLi(String id, String date, List<Location> locLis, int colr){

        LatLng starting = new LatLng(locLis.get(0).getLat(), locLis.get(0).getLangi());

        int end = locLis.size()-1;

        LatLng ending = new LatLng(locLis.get(end).getLat(), locLis.get(end).getLangi());

            List<LatLng> latlngs = new ArrayList<>();

            for(int i=0; i<locLis.size(); i++){
                if ((locLis.get(i).getDriverId().equalsIgnoreCase(id)) && (locLis.get(i).getDate().equalsIgnoreCase(date)) ) {
                    latlngs.add(new LatLng(locLis.get(i).getLat(), locLis.get(i).getLangi()));
                    mMap.addMarker(new MarkerOptions().position(new LatLng(locLis.get(i).getLat(), locLis.get(i).getLangi()))
                            .title("Marker of "+id)
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
