package com.creedglobal.survey.surveyportal;

import android.app.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;


public class SplashScreen extends Activity {

    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        FacebookSdk.sdkInitialize(getApplicationContext());
//        Log.i("my_info","access token"+AccessToken.getCurrentAccessToken());
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */


            @Override
            public void run() {

                if (AccessToken.getCurrentAccessToken()!=null) {
                    Profile profile = Profile.getCurrentProfile();
                    Log.i("my_info", "user name" + profile.getName());

                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(getApplicationContext(), MainScreen.class);
                    startActivity(i);

                }
                else {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
                finish();

            }
        }, SPLASH_TIME_OUT);
    }
    }
