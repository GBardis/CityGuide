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


public class GiannenaActivity extends MainActivity {
    ImageSwitcher myImageSwitcher1;
    Button nextImageButton1;
    int imageSwitcherImages[] = {R.drawable.giannena_1, R.drawable.giannena};
    int switcherImage = imageSwitcherImages.length;
    int counter = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giannena);
        myImageSwitcher1 = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
        nextImageButton1 = (Button) findViewById(R.id.nextImageButton1);
        myImageSwitcher1.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView switcherImageView = new ImageView(getApplicationContext());
                switcherImageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT
                ));
                switcherImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                switcherImageView.setImageResource(R.drawable.giannena);
                switcherImageView.setMaxHeight(100);
                return switcherImageView;
            }
        });
        Animation animationOut = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        Animation animationIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);

        myImageSwitcher1.setOutAnimation(animationOut);
        myImageSwitcher1.setInAnimation(animationIn);
    }

    public void browser3 (View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Ιωάννινα,+Ελλάδα"));
        startActivity(browserIntent);
    }

    public void nextImageButton1(View view) {
        counter++;
        if (counter == switcherImage)
            counter = 0;
        myImageSwitcher1.setImageResource(imageSwitcherImages[counter]);
    }
}
