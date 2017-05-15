package com.example.gdev.unipicityguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class NauplioActivity extends AppCompatActivity {

    ImageSwitcher myImageSwitcher2;
    Button nextImageButton2;

    int imageSwitcherImages[] = {R.drawable.nauplio_1, R.drawable.nauplio};

    int switcherImage = imageSwitcherImages.length;
    int counter = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nauplio);
        myImageSwitcher2 = (ImageSwitcher) findViewById(R.id.imageSwitcher2);
        nextImageButton2 = (Button) findViewById(R.id.nextImageButton2);
        myImageSwitcher2.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView switcherImageView = new ImageView(getApplicationContext());
                switcherImageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT
                ));
                switcherImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                switcherImageView.setImageResource(R.drawable.nauplio);
                switcherImageView.setMaxHeight(100);
                return switcherImageView;
            }
        });
        Animation animationOut = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        Animation animationIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);

        myImageSwitcher2.setOutAnimation(animationOut);
        myImageSwitcher2.setInAnimation(animationIn);
    }

    public void browser5(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Ναύπλιο,+Ελλάδα"));
        startActivity(browserIntent);
    }

    public void nextImageButton2(View view) {
        counter++;
        if (counter == switcherImage)
            counter = 0;
        myImageSwitcher2.setImageResource(imageSwitcherImages[counter]);
    }
}
