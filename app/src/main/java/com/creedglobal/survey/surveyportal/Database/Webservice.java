package com.creedglobal.survey.surveyportal.Database;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by cp on 4/29/2016.
 */
public class Webservice {
    ArrayList<NameValuePair> namevaluepair = null;

    public JSONObject login(String Url, String email, String pswd)
    {
        namevaluepair=new ArrayList<NameValuePair>();
        namevaluepair.add(new BasicNameValuePair("email", email));
        namevaluepair.add(new BasicNameValuePair("pswd", pswd));
        JSONParser jp=new JSONParser();
        return jp.getJasonfromEntitySignup(Url, namevaluepair);
    }
    public JSONObject signup(String Url,String fullname,String email_sign,String pass_sign,String mobile_sign)
    {
        namevaluepair=new ArrayList<NameValuePair>();
        namevaluepair.add(new BasicNameValuePair("fullname", fullname));
        namevaluepair.add(new BasicNameValuePair("email", email_sign));
        namevaluepair.add(new BasicNameValuePair("password", pass_sign));
        namevaluepair.add(new BasicNameValuePair("password", mobile_sign));
        JSONParser jp=new JSONParser();
        return jp.getJasonfromEntitySignup(Url, namevaluepair);
    }
    public JSONObject login1(String Url,String email_sign,String pass_sign) {
        namevaluepair = new ArrayList<NameValuePair>();
        namevaluepair.add(new BasicNameValuePair("email", email_sign));
        namevaluepair.add(new BasicNameValuePair("password", pass_sign));
        JSONParser jp = new JSONParser();
        return jp.getJasonfromEntitySignup(Url, namevaluepair);

    }
}
