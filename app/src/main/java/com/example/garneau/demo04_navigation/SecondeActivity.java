package com.example.garneau.demo04_navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondeActivity extends AppCompatActivity {
    Button btFinir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);

        btFinir = findViewById(R.id.bt_finir);

        btFinir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Activité terminée", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}