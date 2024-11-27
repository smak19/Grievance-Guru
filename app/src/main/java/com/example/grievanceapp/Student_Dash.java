package com.example.grievanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;


public class Student_Dash extends AppCompatActivity {

    CardView C1, C2, C3, C4;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash);

        C4 = findViewById(R.id.card4);

        C4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Student_Dash.this, Complaint.class);
                startActivity(intent1);
            }
        });

        //Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Student Dashboard");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    //Toolbar methods
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Item1){
            Intent intent = new Intent(Student_Dash.this, Logout.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.Item2){
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id= " + getPackageName())));
            }
            catch (Exception ex){
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/app/details?id=" + getPackageName())));
            }
            return true;
        }
        else
        if (id == R.id.Item3){
            Intent intent2 = new Intent(Intent.ACTION_SEND);
            intent2.setType("text/plain");
            String shareBody = "This is an Grievance Guru App " + "https://play.google.com/store/app/details?id=com.example.myapplication1&h1=en";
            String shareSub = "Let's explore it together";
            intent2.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            intent2.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(intent2,"Share using"));

        }
        return true;


    }

}