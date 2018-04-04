package com.example.usman.proretro.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usman.proretro.R;
import com.example.usman.proretro.interfaces.Api;
import com.example.usman.proretro.models.User;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        final EditText userName = (EditText) findViewById(R.id.username);
        final EditText passworD = (EditText) findViewById(R.id.password);
        final EditText nave = (EditText) findViewById(R.id.name);
        final EditText evail = (EditText) findViewById(R.id.email);

        Button editin = (Button) findViewById(R.id.btEdt);
        Button addAdd = (Button) findViewById(R.id.add);
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
    }
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
}
