package com.creedglobal.survey.surveyportal.launch;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.creedglobal.survey.surveyportal.Database.DBHandler;
import com.creedglobal.survey.surveyportal.R;

public class SurveyLauncher extends AppCompatActivity {

    String selectedSurvey="creed";
    int totalquestion=10;
    DBHandler db=null;
    Cursor cursor=null;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_launcher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv= (ListView) findViewById(R.id.listView);
        db = new DBHandler(this);
        cursor=db.getAllSurvey();

        if (cursor!=null){
            SimpleCursorAdapter adapter=new SimpleCursorAdapter(getApplicationContext(),
                    R.layout.surveyitem,
                    cursor,
                    new String[]{"_id"},
                    new int[]{R.id.txt_item},0);
            lv.setAdapter(adapter);
        }
        else{
            Toast.makeText(this,"you have not created any survey",Toast.LENGTH_SHORT).show();
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c= (Cursor) lv.getItemAtPosition(position);
                String selected= c.toString();
                Log.i("infoo","selected item is : "+selected);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void startSurvey(View view){
        Intent i=new Intent(this,Question1.class);
        i.putExtra("TAG_selectedSurvey",selectedSurvey);
        i.putExtra("TAG_totalquestion",totalquestion);
        startActivity(i);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
