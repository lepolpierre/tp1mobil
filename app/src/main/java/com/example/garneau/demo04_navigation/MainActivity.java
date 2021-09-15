package com.example.garneau.demo04_navigation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final int REQUEST_CODE = 1234;
    private Button btActivity2;
    private Button btSActivity;
    private Button btWeb;
    private Button btContact;
    private Button btMap;
    //private Button btPhone;
    private TextView tvResultat;

    // Déclaration d'un objet Launcher pour activité avec résultats
    ActivityResultLauncher<Intent> activiteResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instanciation des widgets
        btActivity2 = findViewById(R.id.bt_activity2);
        btSActivity = findViewById(R.id.bt_sactivity);
        btWeb = findViewById(R.id.bt_web);
        btContact = findViewById(R.id.bt_contact);
        btMap = findViewById(R.id.bt_map);
        tvResultat = findViewById(R.id.tv_message);

        // Passage de gestion de clics aux boutons
        btActivity2.setOnClickListener(this);
        btSActivity.setOnClickListener(this);
        btWeb.setOnClickListener(this);
        btContact.setOnClickListener(this);
        btMap.setOnClickListener(this);

        activiteResultat = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {

                            Intent data = result.getData();
                            String message = data.getStringExtra(SousActivity.MESSAGE);
                            tvResultat.setText(message);
                        }
                        else {
                            tvResultat.setText(R.string.erreur);
                        }
                    }
                });
    }

    // Gestionnaire de clics sur les différents boutons du layout
    public void onClick(View v) {
        // déclaration d'Intent pour la navigation
        Intent intent;
        // switch sur l'Id du bouton, instanciation de l'intention et navigation vers l'activité
        switch (v.getId()) {
            // Explicite et interne
            case R.id.bt_activity2:
                intent = new Intent(MainActivity.this, SecondeActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_sactivity:
                intent = new Intent(getApplicationContext(), SousActivity.class);
                // Ancienne méthode : méthode de classe Activity
                //startActivityForResult(intent, REQUEST_CODE);

                // Nouvelle méthode : méthode de classe Launcher
                activiteResultat.launch(intent);

                break;
            // Implicite et Externe
            case R.id.bt_web:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cegepgarneau.ca"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(intent, "Choose Application"));
                }
                break;
            case R.id.bt_contact:
                intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "Aucune application", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_map:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:46.793531,-71.262906"));
                //intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?q=loc:46.793531,-71.262906"));
                intent.setPackage("com.google.android.apps.maps");
                //if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                /*}
                else {
                    Toast.makeText(this, "Aucune application", Toast.LENGTH_SHORT).show();
                }*/
                break;
            default:
                break;
        }
    }


    // Ancienne méthode : Fonction de rappel pour SousActivity.class
    /*@Override
    protected void onActivityResult(int req, int res, Intent data) {
        super.onActivityResult(req, res, data);
        if (req == REQUEST_CODE) {
            if (res == RESULT_OK) {
                String message = data.getStringExtra(SousActivity.MESSAGE);
                tvResultat.setText(message);
            } else {
                tvResultat.setText(R.string.erreur);
            }
        }
    }*/

}
