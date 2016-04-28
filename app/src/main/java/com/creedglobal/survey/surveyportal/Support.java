package com.creedglobal.survey.surveyportal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Pattern;

public class Support extends AppCompatActivity {
    private static final String phoneRegex = "08088484807";//you can just place your support phone here
    private static final Pattern phoneMatcher = Pattern.compile(phoneRegex);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView twitter =(ImageView)findViewById(R.id.twitter);
        ImageView linkedin =(ImageView)findViewById(R.id.linkedin);
        ImageView facebook =(ImageView)findViewById(R.id.facebook);
        TextView Corporate_office = (TextView)findViewById(R.id.txt_corporate);
        final TextView Contact = (TextView)findViewById(R.id.txt_contact);
//        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Contact");
        SpannableString spanStr = new SpannableString(getString(R.string.head_add));
        spanStr.setSpan(new UnderlineSpan(), 0, spanStr.length(), 0);
        Corporate_office.setText(spanStr);
        Corporate_office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent geoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="
                        +"Creed Technologies Pvt. Ltd.Level 9th, Delta Block,Sigma Tech-Park,Whitefield Main Road, Whitefield Bangalore 560 066"));
                startActivity(geoIntent);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "https://twitter.com/creedglobal";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
        linkedin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "https://www.linkedin.com/company/creed-global-technologies";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "https://www.facebook.com/creedglobal/?fref=ts";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (getApplicationContext() instanceof MainActivity) {
//                    ((MainActivity) getApplicationContext()).onBackPressed();
//                }
//            }
//        });
        linkify(Contact);

        // Inflate the layout for this fragment

    }
    public static void linkify(TextView text) {
        Linkify.addLinks(text, phoneMatcher, "tel:");
    }

}
