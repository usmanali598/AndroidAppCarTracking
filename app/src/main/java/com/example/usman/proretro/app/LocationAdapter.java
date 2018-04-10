package com.example.usman.proretro.app;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usman.proretro.R;
import com.example.usman.proretro.models.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usman on 04/04/2018.
 */

public class LocationAdapter extends ArrayAdapter<Location> {

    private Context context;
    private List<Location> values;

    public LocationAdapter(@NonNull Context context, List<Location> values) {
        super(context, R.layout.location_cutom_list, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.location_cutom_list, parent, false);
        }

        final TextView tvLis = (TextView) row.findViewById(R.id.list_item_location_text);
        ImageButton list_butt = (ImageButton) row.findViewById(R.id.editingLocation);
        ImageButton delete_butt = (ImageButton) row.findViewById(R.id.deletingLocation);


        final Location item = values.get(position);

        //final String message = item.getLocationId() + " - " + item.getDriverId() + " - " + item.getLat() + " - " + item.getLangi() + " - " + item.getDate();
        final String message = item.getLocationId() + " - " + item.getLat() + " - " + item.getLangi();

        tvLis.setText(message);
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
      /*  Intent intenti = new Intent(getContext(), MapsActivity.class);

        context.startActivity(intenti);*/

        Location location = new Location(item.getLocationId(), item.getDriverId(), item.getLat(), item.getLangi(), item.getDate());
      //  Toast.makeText(context, "" + location.getLocationId() + " / " + location.getDriverId() + " / " + location.getLat() + " / " + location.getLangi() + " / " + location.getDate(), Toast.LENGTH_SHORT).show();

        tvLis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();

            }
        });

        delete_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Del button", Toast.LENGTH_SHORT).show();
                /*  Location location = new Location(item.getLocationId(), item.getDriverId(), item.getLat(), item.getLangi(), item.getDate());
                Intent intenti = new Intent(getContext(), DeleteFuelActivity.class);
                intenti.putExtra("locationId", item.getLocationId());
                context.startActivity(intenti);*/
            }
        });

        list_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Edit Button", Toast.LENGTH_SHORT).show();
                Intent intenti = new Intent(getContext(), MapsActivity.class);
       /* ArrayList<Double> listDouble = new ArrayList<Double>();
        listDouble.add(item.getLat());
        listDouble.add(item.getLangi());
        intenti.putExtra("arraylist", listDouble);
        context.startActivity(intenti);*/
               // intenti.putExtra("coordinates",location.getLat()+" "+location.getLangi());

                intenti.putExtra("locationId", item.getLocationId());
                intenti.putExtra("driverId", item.getDriverId());
                intenti.putExtra("lat", item.getLat());
                intenti.putExtra("langi", item.getLangi());
                intenti.putExtra("date", item.getDate());

                context.startActivity(intenti);

            }

        });

        return row;
    }
}