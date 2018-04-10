package com.example.usman.proretro.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.usman.proretro.R;

/**
 * Created by usman on 10/04/2018.
 */

public class IdFieldActivity extends AppCompatActivity {

    private EditText forId;
    private Button addId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idfield);

        Intent in = getIntent();

        forId = (EditText) findViewById(R.id.etIdField);
        addId = (Button)findViewById(R.id.btIdField);

        final String idTex = forId.getText().toString();

        addId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ini = new Intent(IdFieldActivity.this, FirstActivity.class);
                ini.putExtra("idForMap", idTex);
                startActivity(ini);
            }
        });


    }
}
