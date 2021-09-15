package com.example.garneau.demo04_navigation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SousActivity extends AppCompatActivity {

    public static final String MESSAGE = "MESSAGE";
    private EditText etMessage;
    private TextView tvTitle;
    private Button btFinir;
    private Button btShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sous);

        etMessage = findViewById(R.id.et_message);
        btFinir = findViewById(R.id.bt_finir);
        btShare = findViewById(R.id.bt_share);
        tvTitle = findViewById(R.id.tv_title);

        btFinir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = etMessage.getText().toString();
                // nouvelle intention pour retour
                Intent intent = new Intent();
                // passage de valeur
                intent.putExtra(MESSAGE, message);
                // Active la navigation retour
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareMessage();
            }
        });

        // Si l'activité est lancée par le intent-filter, affiche l'url dans le TextView
        Uri uri = getIntent().getData();
        if (uri != null) {
            tvTitle.setText(uri.toString());
        }
    }

    private void shareMessage() {
        String message = etMessage.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Mon message");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(intent);
    }
}

