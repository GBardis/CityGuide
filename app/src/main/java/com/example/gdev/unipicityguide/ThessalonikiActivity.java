package com.example.gdev.unipicityguide;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ThessalonikiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thessaloniki);
    }
    public void browser2 (View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Θεσσαλονίκη,+Ελλάδα"));
        startActivity(browserIntent);
    }

}
