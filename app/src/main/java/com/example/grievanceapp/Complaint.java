package com.example.grievanceapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

public class Complaint extends AppCompatActivity {

    Spinner spinner;
    String [] complaints = {"Room allocation concerns","Maintenance issues","Hostel issues"};
    ImageView img;
    Button btnCam;
    private final int GALLERY_REQ_CODE = 5000;

    // Define ActivityResultLauncher
    private ActivityResultLauncher<Intent> galleryLauncher;
    Toolbar toolbar;
    CardView C1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        C1 = findViewById(R.id.card1);

        //Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Complaint Dashboard");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        img = findViewById(R.id.img);
        btnCam = findViewById(R.id.butCam);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Complaint.this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                String value = parent.getItemAtPosition(position).toString();
                // Show a Toast message with the selected value
                Toast.makeText(Complaint.this, value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action required when nothing is selected
            }
        });

        // Initialize the ActivityResultLauncher
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        Glide.with(this)
                                .load(imageUri)
                                .override(1024, 1024) // Resize to fit within supported texture size
                                .into(img);
                    }
                }
        );

        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryLauncher.launch(iGallery);
            }
        });
    }
    }