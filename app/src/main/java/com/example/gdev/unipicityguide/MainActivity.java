package com.example.gdev.unipicityguide;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements LocationListener {
    Button button;
    TextView textView3;
    TextView textView8;
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView8 = (TextView) findViewById(R.id.textView8);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoathens();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                givemeperm();
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED)
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            0, 0, MainActivity.this);
            }
        });
    }

    private void gotoathens() {
        Intent intent = new Intent(this, AthensActivity.class);
        startActivity(intent);
    }

    private void gotothessaloniki() {
        Intent intent = new Intent(this, ThessalonikiActivity.class);
        startActivity(intent);
    }

    private void gotogiannena() {
        Intent intent = new Intent(this, GiannenaActivity.class);
        startActivity(intent);
    }

    private void gotonauplio() {
        Intent intent = new Intent(this, NauplioActivity.class);
        startActivity(intent);
    }

    static final double AthLat = 23.7275388;
    static final double AthLong = 37.9838096;
    static final double ThessLat = 22.9444191;
    static final double ThessLong = 40.6400629;
    static final double GianLat = 20.8532503;
    static final double GianLong = 39.6666416;
    static final double NauLat = 22.8015531;
    static final double NauLong = 37.5673173;

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();

        float[] athDistance = new float[3];
        float[] thessDistance = new float[3];
        float[] gianDistance = new float[3];
        float[] nauDistance = new float[3];

        Location.distanceBetween(lat, lon, AthLat, AthLong, athDistance);
        Location.distanceBetween(lat, lon, ThessLat, ThessLong, thessDistance);
        Location.distanceBetween(lat, lon, GianLat, GianLong, gianDistance);
        Location.distanceBetween(lat, lon, NauLat, NauLong, nauDistance);
        float distanceInMetersath = athDistance[0];
        float distanceInMetersThess = thessDistance[0];
        float distanceInMeterGian = gianDistance[0];
        float distanceInMetersNau = nauDistance[0];

        if (distanceInMetersath < 10000)
            gotoathens();
        else if (distanceInMetersThess < 10000)
            gotothessaloniki();
        else if (distanceInMeterGian < 10000)
            gotogiannena();
        else if (distanceInMetersNau < 10000)
            gotonauplio();
        else
            Toast.makeText(this, "EISTAI MAKRIA", Toast.LENGTH_LONG).show();
       /* float distanceInMeters = results[0];
        boolean isWithin10km = distanceInMeters < 10000;
        if (isWithin10km == true)
            gotoathens();
        else if (isWithin10km == false)
            Toast.makeText(this,"EISTAI MAKRIA",Toast.LENGTH_LONG).show();*/
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void givemeperm() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
    }
}
