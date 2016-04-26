package com.creedglobal.survey.surveyportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Forgot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
    }
    public void gotoLogin(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
