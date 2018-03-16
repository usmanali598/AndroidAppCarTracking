package com.example.usman.proretro.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.usman.proretro.R;
import com.example.usman.proretro.models.Fuel;
import com.example.usman.proretro.models.User;

import java.util.List;

/**
 * Created by usman on 15/03/2018.
 */

public class FuelAdapter extends ArrayAdapter<Fuel> {

    private Context context;
    private List<Fuel> values;

    public FuelAdapter(@NonNull Context context, List<Fuel> values) {
        super(context, R.layout.fuel_item, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.fuel_item, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.fuela_item);

        Fuel item = values.get(position);
        String message = item.getUserperson()+" - "+item.getDate();
        //int ints = Integer.parseInt(item.getAmount()+" - "+item.getLitres());
        textView.setText(message );

        return row;
    }
}
