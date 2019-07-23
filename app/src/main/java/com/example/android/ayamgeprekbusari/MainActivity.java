package com.example.android.ayamgeprekbusari;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button pesanan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pesanan = (Button) findViewById(R.id.btn_pesan1);

        pesanan.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, InputPesanan.class);
                startActivity(i);
            }
        });

    }
    }

