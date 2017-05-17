package com.example.gdev.unipicityguide;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity implements LocationListener {
    static final double AthLat = 23.7275388;
    static final double AthLong = 37.9838096;
    static final double ThesLat = 22.9444191;
    static final double ThesLong = 40.6400629;
    static final double GianLat = 20.8532503;
    static final double GianLong = 39.6666416;
    static final double NauLat = 22.8015531;
    static final double NauLong = 37.5673173;
    static LocationManager locationManager;
    Button button;
    ToggleButton toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        giveMePerm();
        button = (Button) findViewById(R.id.button);
        toggle = (ToggleButton) findViewById(R.id.toggBtn);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    stopUsingGPS();
                } else {
                    giveMePerm();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveMePerm();
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED)
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, MainActivity.this);
            }
        });
    }

    public void stopUsingGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        } else {
            giveMePerm();
        }
    }

    protected void onStop() {
        super.onStop();
        stopUsingGPS();

    }

    protected void onPause() {
        super.onPause();
        stopUsingGPS();
    }
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, MainActivity.this);

    }

    private void goToAthens() {
        Intent intent = new Intent(this, AthensActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Welcome To Athens", Toast.LENGTH_LONG).show();
    }

    private void goToThessaloniki() {
        Intent intent = new Intent(this, ThessalonikiActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Welcome To Thessaloniki", Toast.LENGTH_LONG).show();
    }

    private void goToGiannena() {
        Intent intent = new Intent(this, GiannenaActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Welcome To Giannena", Toast.LENGTH_LONG).show();
    }

    private void goToNauplio() {
        Intent intent = new Intent(this, NauplioActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Welcome To Nauplio", Toast.LENGTH_LONG).show();
    }

    private void gotoMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Too Far", Toast.LENGTH_LONG).show();
        stopUsingGPS();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, MainActivity.this);
        } else {
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
        Location.distanceBetween(lat, lon, ThesLat, ThesLong, thessDistance);
        Location.distanceBetween(lat, lon, GianLat, GianLong, gianDistance);
        Location.distanceBetween(lat, lon, NauLat, NauLong, nauDistance);
        float distanceInMetersath = athDistance[0];
        float distanceInMetersThess = thessDistance[0];
        float distanceInMeterGian = gianDistance[0];
        float distanceInMetersNau = nauDistance[0];

        if (distanceInMetersath < 10000)
            goToAthens();
        else if (distanceInMetersThess < 10000)
            goToThessaloniki();
        else if (distanceInMeterGian < 10000)
            goToGiannena();
        else if (distanceInMetersNau < 10000)
            goToNauplio();
        else
            gotoMain();
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

    public void giveMePerm() {
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
    }
}