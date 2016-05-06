package com.creedglobal.survey.surveyportal.launch;

import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.creedglobal.survey.surveyportal.Database.DBHandler;
import com.creedglobal.survey.surveyportal.Info.Constraints;
import com.creedglobal.survey.surveyportal.Info.Result;
import com.creedglobal.survey.surveyportal.R;

public class Question10 extends AppCompatActivity {

    TextView qno, question, opt1, opt2, opt3, opt4, pmsg;
    int qid = 10, totalquestion,selectedOption;
    String selectedSurvey = null;
    boolean selected = false;
    private DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        selectedSurvey = getIntent().getStringExtra("TAG_selectedSurvey");
        totalquestion = getIntent().getIntExtra("TAG_totalquestion", 0);

        qno = (TextView) findViewById(R.id.qid);
        question = (TextView) findViewById(R.id.question);
        opt1 = (TextView) findViewById(R.id.option1);
        opt2 = (TextView) findViewById(R.id.option2);
        opt3 = (TextView) findViewById(R.id.option3);
        opt4 = (TextView) findViewById(R.id.option4);
        pmsg = (TextView) findViewById(R.id.pmsg);
        qno.setText("Q " + qid + ". ");
        pmsg.setText(qid + "/" + totalquestion);

        db = new DBHandler(this);
        // retreiving Details
        Cursor c=db.getQdata(selectedSurvey);
        Log.d("Retreive: ", "Retreiving ...qid "+qid);

        if (c.moveToPosition(qid-1)){
            question.setText(c.getString(2));
            opt1.setText(c.getString(3));
            opt2.setText(c.getString(4));
            opt3.setText(c.getString(5));
            opt4.setText(c.getString(6));
            if (c!=null){
                c.close();
                db.close();
            }
        }
        else {
            Toast.makeText(this,"problem in fetching question. Please restart the App",Toast.LENGTH_SHORT);
        }
    }
    public void saveAndNext(View view) {
        if (view.getId() == R.id.option1) {
            captureSelectedData(view,1);
            onSelect(opt1);
        }
        if (view.getId() == R.id.option2) {
            captureSelectedData(view,2);
            onSelect(opt2);
        }
        if (view.getId() == R.id.option3) {
            captureSelectedData(view,3);
            onSelect(opt3);
        }
        if (view.getId() == R.id.option4) {
            captureSelectedData(view,4);
            onSelect(opt4);
        }
    }

    private void captureSelectedData(View viewid,int selectedOption){
        this.selectedOption=selectedOption;
        Result.selectedOption[qid]=((TextView)viewid).getText().toString();
        Result.selectedOptionNumber[qid]=selectedOption;
    }


    public void onSelect(TextView selectedView) {

        opt1.setBackgroundResource(R.drawable.unselected);
        opt2.setBackgroundResource(R.drawable.unselected);
        opt3.setBackgroundResource(R.drawable.unselected);
        opt4.setBackgroundResource(R.drawable.unselected);
        selectedView.setBackgroundResource(R.drawable.selected);
        Log.i("my_info", selectedView.getText().toString());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (qid == totalquestion) {
                    intent = new Intent(getApplicationContext(), Submit.class);
                } else {
                    intent = new Intent(getApplicationContext(), Submit.class);
                }
                intent.putExtra("TAG_selectedSurvey", selectedSurvey);
                intent.putExtra("TAG_totalquestion", totalquestion);
                startActivity(intent);
            }
        }, Constraints.delayTimeOut);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}