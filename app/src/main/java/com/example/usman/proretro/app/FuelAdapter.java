package com.example.usman.proretro.app;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        Button list_butt = (Button) row.findViewById(R.id.editing);

        final Fuel item = values.get(position);

        final String message = item.getFuelId() +" - "+item.getUserperson()+" - "+item.getAmount()+" - "+item.getLitres()+" - "+item.getDate();
        textView.setText(message);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+ message, Toast.LENGTH_SHORT).show();

            }
        });

        list_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Editing", Toast.LENGTH_SHORT).show();
                Fuel fuel = new Fuel(item.getFuelId(), item.getUserperson(), item.getAmount(), item.getLitres(), item.getDate());
                Toast.makeText(context, ""+fuel.getFuelId()+" / "+fuel.getUserperson()+" / "+fuel.getAmount()+" / "+fuel.getLitres()+" / "+fuel.getDate(), Toast.LENGTH_SHORT).show();

                Intent intenti = new Intent(getContext(), UpdateFuel.class);
                intenti.putExtra("fuelId", item.getFuelId());
                intenti.putExtra("userPerson", item.getUserperson());
                intenti.putExtra("amount", item.getAmount());
                intenti.putExtra("litres", item.getLitres());
                intenti.putExtra("date", item.getDate());

                context.startActivity(intenti);
                    /* UserAdapter usi = item.get(position);
                Intent intent = new Intent(activity, MainActivity.class);
                intent.putExtra("id", item.getName());

                activity.startActivity(intent);*/
            }

        });

        return row;
    }


}
