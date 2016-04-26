package com.creedglobal.survey.surveyportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
    public void loginPage(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
