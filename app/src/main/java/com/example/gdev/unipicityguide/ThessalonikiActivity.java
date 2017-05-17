package com.example.gdev.unipicityguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class ThessalonikiActivity extends MainActivity {
    ImageSwitcher myImageSwitcher3;
    Button nextImageButton3;
    int imageSwitcherImages[] = {R.drawable.thessaloniki_1, R.drawable.thessaloniki_2};

    int switcherImage = imageSwitcherImages.length;
    int counter = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thessaloniki);
        myImageSwitcher3 = (ImageSwitcher) findViewById(R.id.imageSwitcher3);
        nextImageButton3 = (Button) findViewById(R.id.nextImageButton3);
        myImageSwitcher3.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView switcherImageView = new ImageView(getApplicationContext());
                switcherImageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT
                ));
                switcherImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                switcherImageView.setImageResource(R.drawable.thessaloniki_2);
                switcherImageView.setMaxHeight(100);
                return switcherImageView;
            }
        });
        Animation animationOut = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        Animation animationIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);

        myImageSwitcher3.setOutAnimation(animationOut);
        myImageSwitcher3.setInAnimation(animationIn);
    }
    public void browser2 (View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Θεσσαλονίκη,+Ελλάδα"));
        startActivity(browserIntent);
    }

    public void nextImageButton3(View view) {
        counter++;
        if (counter == switcherImage)
            counter = 0;
        myImageSwitcher3.setImageResource(imageSwitcherImages[counter]);
    }
}
