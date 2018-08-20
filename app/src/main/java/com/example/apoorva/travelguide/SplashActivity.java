package com.example.apoorva.travelguide;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    ImageView splash_plane;
    Animation in_from_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash_plane = findViewById(R.id.splash_plane);
        in_from_right = AnimationUtils.loadAnimation(this,R.anim.in_from_right);
        splash_plane.setAnimation(in_from_right);

        // Splash screen timer
         int SPLASH_TIME_OUT = 5500;


            new Handler().postDelayed(new Runnable() {

                /** Display splash screen with a timer */
                @Override
                public void run() {
                    // This method executes once the timer is over
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));

                    // Close splash activity
                    finish();
                }
            }, SPLASH_TIME_OUT);

        }
    }

