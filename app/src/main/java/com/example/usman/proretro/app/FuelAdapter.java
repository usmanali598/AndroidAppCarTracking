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

import com.example.usman.proretro.models.Fuel;

import java.util.List;

/**
 * Created by usman on 15/03/2018.
 */

public class FuelAdapter extends ArrayAdapter<Fuel> {

    private Context context;
    private List<Fuel> values;

    public FuelAdapter(@NonNull Context context, List<Fuel> values) {
        super(context, R.layout.list_item_pagination, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_pagination, parent, false);
        }

        final TextView textView = (TextView) row.findViewById(R.id.list_item_pagination_text);
        ImageButton list_butt = (ImageButton) row.findViewById(R.id.editing);
        ImageButton delete_butt = (ImageButton) row.findViewById(R.id.deleting);


        final Fuel item = values.get(position);

        final String message = item.getFuelId() +" - "+item.getUserperson()+" - "+item.getAmount()+"€"+" - "+item.getLitres()+"Ltr"+" - "+item.getDate();
        textView.setText(message);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+ message, Toast.LENGTH_SHORT).show();

            }
        });

        delete_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fuel fuel = new Fuel(item.getFuelId(), item.getUserperson(), item.getAmount(), item.getLitres(), item.getDate());
                Intent intenti = new Intent(getContext(), DeleteFuelActivity.class);
                intenti.putExtra("fuelId", item.getFuelId());
                context.startActivity(intenti);
            }
        });

        list_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fuel fuel = new Fuel(item.getFuelId(), item.getUserperson(), item.getAmount(), item.getLitres(), item.getDate());
                Toast.makeText(context, ""+fuel.getFuelId()+" / "+fuel.getUserperson()+" / "+fuel.getAmount()+" / "+fuel.getLitres()+" / "+fuel.getDate(), Toast.LENGTH_SHORT).show();

                Intent intenti = new Intent(getContext(), UpdateFuel.class);
                intenti.putExtra("fuelId", item.getFuelId());
                intenti.putExtra("userPerson", item.getUserperson());
                intenti.putExtra("amount", item.getAmount());
                intenti.putExtra("litres", item.getLitres());
                intenti.putExtra("date", item.getDate());

                context.startActivity(intenti);

            }

        });

        return row;
    }


}
