package com.example.usman.proretro.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usman.proretro.R;
import com.example.usman.proretro.models.User;
import com.example.usman.proretro.interfaces.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent main = getIntent();

        listView = (ListView) findViewById(R.id.pagination_list);

        butt = (Button)findViewById(R.id.btn);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.65:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api apiService = retrofit.create(Api.class);

        Call<List<User>> call = apiService.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                List<User> userLis = response.body();


                listView.setAdapter(new UserAdapter(MainActivity.this, userLis));
                listView.getAdapter().getItemId(2);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "error :( "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, FuelActivity.class));

            }
        });
    }
}
