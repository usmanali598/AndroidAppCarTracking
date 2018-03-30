package com.example.usman.proretro.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usman.proretro.R;
import com.example.usman.proretro.models.User;

import java.util.List;

import retrofit2.Response;

/**
 * Created by usman on 10/03/2018.
 */

public class UserAdapter extends ArrayAdapter<User> {

    private Context context;
    private List<User> values;

    public UserAdapter(@NonNull Context context, List<User> values) {
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

        final User item = values.get(position);
        final String message = item.getName()+" - "+item.getUsername()+" - "+item.getPassword()+" - "+item.getEmail();
        textView.setText(message);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+position+ message, Toast.LENGTH_SHORT).show();

            }
        });

        list_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Editing", Toast.LENGTH_SHORT).show();
                User user = new User(item.getUsername(), item.getPassword(), item.getName(), item.getEmail());
                Toast.makeText(context, ""+getItemId(position+1)+" / "+user.getUsername()+" / "+user.getPassword()+" / "+user.getName()+" / "+user.getEmail(), Toast.LENGTH_SHORT).show();

              /* UserAdapter usi = item.get(position);
                Intent intent = new Intent(activity, MainActivity.class);
                intent.putExtra("id", item.getName());

                activity.startActivity(intent);*/
            }

        });

        return row;


    }

}
