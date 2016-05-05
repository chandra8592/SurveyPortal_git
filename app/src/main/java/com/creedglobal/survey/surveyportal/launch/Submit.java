package com.creedglobal.survey.surveyportal.launch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.creedglobal.survey.surveyportal.Database.DBHandler;
import com.creedglobal.survey.surveyportal.Info.Result;
import com.creedglobal.survey.surveyportal.R;

public class Submit extends AppCompatActivity implements View.OnClickListener {
    EditText name,email,mobile,msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        name= (EditText) findViewById(R.id.edt_customer_name);
        email=(EditText) findViewById(R.id.edt_customer_email);
        mobile=(EditText) findViewById(R.id.edt_customer_mobile);
        msg=(EditText) findViewById(R.id.edt_customer_msg);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_submit){
            Result.selectedOption[10]=name.getText().toString();
            Result.selectedOption[11]=email.getText().toString();
            Result.selectedOption[12]=mobile.getText().toString();
            Result.selectedOption[13]=msg.getText().toString();
            DBHandler db=new DBHandler(this);
            db.submitResult(Result.selectedOption);
            if (db.submitResult(Result.selectedOption))
            {
                Toast.makeText(getApplicationContext(),"Your result is submitted Successfully",Toast.LENGTH_SHORT).show();
                db.close();
            }
            else {
                Toast.makeText(getApplicationContext(),"ops...! something went wrong",Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void submit(View view){
        Result.selectedOption[10]=name.getText().toString();
        Result.selectedOption[11]=email.getText().toString();
        Result.selectedOption[12]=mobile.getText().toString();
        Result.selectedOption[13]=msg.getText().toString();
        Log.i("infoo","value in array is :"+Result.selectedOption);
        DBHandler db=new DBHandler(this);
        if (db.submitResult(Result.selectedOption))
        {
            Toast.makeText(getApplicationContext(),"Your result is submitted Successfully",Toast.LENGTH_SHORT).show();
            db.close();
        }
        else {
            Toast.makeText(getApplicationContext(),"ops...! something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
}
//    boolean ph,em;
//ph=Patterns.PHONE.matcher(mobileedt.getText().toString()).matches();
//        em=Patterns.EMAIL_ADDRESS.matcher(emailedt.getText().toString()).matches();
//        if (ph&&em) {