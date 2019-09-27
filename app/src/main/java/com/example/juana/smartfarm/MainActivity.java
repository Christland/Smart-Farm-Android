package com.example.juana.smartfarm;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //PROGRESS BARS
    private ProgressBar fire;
    private DonutProgress soil_moisturebar;
    private DonutProgress temperaturebar;
    private DonutProgress humiditybar;
    Button stopAlarm;
    private TextView tempTxt;
    private TextView humidityTxt;
    private TextView soil_moistureTxt;

    private DatabaseReference myRef;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initializing progress bars
            temperaturebar =(DonutProgress) findViewById(R.id.tempBar);
            humiditybar = (DonutProgress) findViewById(R.id.humidityBar);
            soil_moisturebar =(DonutProgress) findViewById(R.id.soil_moistureBar);


        //Initializing text views
            tempTxt = findViewById(R.id.tempVal);
            humidityTxt = findViewById(R.id.humidityVal);
            soil_moistureTxt = findViewById(R.id.soil_moistureVal);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();




        myRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                String humidity = dataSnapshot.child("humidity").getValue().toString();
                String temperature = dataSnapshot.child("temperature").getValue().toString();
                String soil_moisture = dataSnapshot.child("moisture").getValue().toString();
                //Toast.makeText(getApplicationContext(), humidity, Toast.LENGTH_LONG).show();

                //  humidity
                humiditybar.setProgress(Float.valueOf(humidity));// Sets progress bar to humidity value
                humidityTxt.setText(humidity); // Displays humidity to Textview

                //temperature
                temperaturebar.setProgress(Float.valueOf(temperature));// Sets progress bar to  temperature  value
                tempTxt.setText(temperature);//Displays temperature to Textview

                //soil
                soil_moisturebar.setProgress(Float.valueOf(soil_moisture)); //Sets progress bar to moisture value
                soil_moistureTxt.setText(soil_moisture); //Sets textview to soil moisture value
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
