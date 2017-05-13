package com.example.gdev.unipicityguide;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements LocationListener {
    static final double AthLat = 23.7275388;
    static final double AthLong = 37.9838096;
    static final double ThessLat = 22.9444191;
    static final double ThessLong = 40.6400629;
    static final double GianLat = 20.8532503;
    static final double GianLong = 39.6666416;
    static final double NauLat = 22.8015531;
    static final double NauLong = 37.5673173;
    Button button;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
            alertBuilder.setCancelable(true);
            alertBuilder.setTitle("Permission is necessary");
            alertBuilder.setMessage("Location permission is necessary to begin our travel ");
            alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
                }
            });
            AlertDialog alert = alertBuilder.create();
            alert.show();
        } else if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, MainActivity.this);
        }
        locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                givemeperm();
                if (ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED)
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0,MainActivity.this);
            }
        });

    }

    private void gotoathens() {
        Intent intent = new Intent(this, AthensActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Welcome To Athens", Toast.LENGTH_LONG).show();
    }

    private void gotothessaloniki() {
        Intent intent = new Intent(this, ThessalonikiActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Welcome To Thessaloniki", Toast.LENGTH_LONG).show();
    }

    private void gotogiannena() {
        Intent intent = new Intent(this, GiannenaActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Welcome To Giannena", Toast.LENGTH_LONG).show();
    }

    private void gotonauplio() {
        Intent intent = new Intent(this, NauplioActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Welcome To Nauplio", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, MainActivity.this);
        }else{
            Toast.makeText(MainActivity.this, "Permission denied to access your location", Toast.LENGTH_SHORT).show();
        }
    }

    public void onLocationChanged(Location location) {
        float[] athDistance = new float[3];
        float[] thessDistance = new float[3];
        float[] gianDistance = new float[3];
        float[] nauDistance = new float[3];
        double lat = location.getLatitude();
        double lon = location.getLongitude();

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
