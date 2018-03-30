package com.example.usman.proretro.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
 * Created by usman on 17/03/2018.
 */

public class EditActivity extends AppCompatActivity{

    private TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_box);

        tv = (TextView)findViewById(R.id.tvMsg);
        tv.setText("This is just showcase...");

        //Intent ina = getIntent();


         EditText userName = (EditText) findViewById(R.id.usera);
         EditText passworD = (EditText) findViewById(R.id.passworda);
         EditText nave = (EditText) findViewById(R.id.namea);
         EditText evail = (EditText) findViewById(R.id.emaila);


        Button updatE = (Button) findViewById(R.id.update);

        updatE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditActivity.this, "O see", Toast.LENGTH_SHORT).show();

            }
        });
                User user = new User(
                        userName.getText().toString(),
                        passworD.getText().toString(),
                        nave.getText().toString(),
                        evail.getText().toString()
                );

                sendNetworkRequest(user);
            }


  private void sendNetworkRequest(User user) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.103:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api apiService = retrofit.create(Api.class);
        Call<List<User>> call = apiService.editUsers(1, user);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Toast.makeText(EditActivity.this, "Here is:" + response.body(), Toast.LENGTH_SHORT).show();
                //Response<User> User;
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(EditActivity.this, "Something stupid happend", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
