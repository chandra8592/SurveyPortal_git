package com.creedglobal.survey.surveyportal.launch;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.creedglobal.survey.surveyportal.Database.DBHandler;
import com.creedglobal.survey.surveyportal.Database.Details_db;
import com.creedglobal.survey.surveyportal.Info.Data;
import com.creedglobal.survey.surveyportal.R;

public class Question4 extends AppCompatActivity {

    TextView qno,question,opt1,opt2,opt3,opt4,pmsg;
    int qid,totalquestion;
    long delayTime=2000;
    String selectedSurvey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);
        qno=(TextView)findViewById(R.id.qid);
        question=(TextView)findViewById(R.id.question);
        opt1=(TextView)findViewById(R.id.option1);
        opt2=(TextView)findViewById(R.id.option2);
        opt3=(TextView)findViewById(R.id.option3);
        opt4=(TextView)findViewById(R.id.option4);
        pmsg=(TextView)findViewById(R.id.pmsg);
        selectedSurvey=getIntent().getStringExtra("selectedSurvey");
        totalquestion=Data.total;
        qno.setText("Q "+qid+". ");
        pmsg.setText(qid+"/"+totalquestion);
        DBHandler get= new DBHandler(this);
        // retreiving Details


        Log.d("Retreive: ", "Retreiving ..");
        Details_db data= get.getQuestion("creed",4);
        // get.close();
//        tv.setText(data);
        question.setText(data.getQuestion());
        opt1.setText(data.getResponse_1());
        opt2.setText(data.getResponse_2());
        opt3.setText(data.getResponse_3());
        opt4.setText(data.getResponse_4());

    }
    public void saveAndNext(View view){
        if (view.getId()==R.id.option1){
            onSelect(opt1);
        }
        if (view.getId()==R.id.option2){
            onSelect(opt2);
        }
        if (view.getId()==R.id.option3){
            onSelect(opt3);
        }
        if (view.getId()==R.id.option4) {
            onSelect(opt4);
        }
    }
    public void onSelect(TextView selectedView){

        opt1.setBackgroundColor(getResources().getColor(R.color.unselected));
        opt2.setBackgroundColor(getResources().getColor(R.color.unselected));
        opt3.setBackgroundColor(getResources().getColor(R.color.unselected));
        opt4.setBackgroundColor(getResources().getColor(R.color.unselected));
        selectedView.setBackgroundColor(getResources().getColor(R.color.selected));
        Log.i("my_info",selectedView.getText().toString());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Question5.class));
            }
        },delayTime);
    }
}