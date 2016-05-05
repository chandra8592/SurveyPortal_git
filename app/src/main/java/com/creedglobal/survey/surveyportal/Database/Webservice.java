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

    public JSONObject login(String Url, String email, String password)
    {
        namevaluepair=new ArrayList<NameValuePair>();
        namevaluepair.add(new BasicNameValuePair("email", email));
        namevaluepair.add(new BasicNameValuePair("password", password));
        JSONParser jp=new JSONParser();
        return jp.getJasonfromEntitySignup(Url, namevaluepair);
    }
    public JSONObject signup(String Url,String name,String email,String password,String mobile,String occupation)
    {
        namevaluepair=new ArrayList<NameValuePair>();
        namevaluepair.add(new BasicNameValuePair("fullname", name));
        namevaluepair.add(new BasicNameValuePair("email", email));
        namevaluepair.add(new BasicNameValuePair("password", password));
        namevaluepair.add(new BasicNameValuePair("mobile", mobile));
        namevaluepair.add(new BasicNameValuePair("occupation", occupation));
        JSONParser jp=new JSONParser();
        return jp.getJasonfromEntitySignup(Url, namevaluepair);
    }
    public JSONObject login1(String Url,String email,String password) {
        namevaluepair = new ArrayList<NameValuePair>();
        namevaluepair.add(new BasicNameValuePair("email", email));
        namevaluepair.add(new BasicNameValuePair("password", password));
        JSONParser jp = new JSONParser();
        return jp.getJasonfromEntitySignup(Url, namevaluepair);

    }
    public JSONObject info(String Url)
    {
        JSONParser jp=new JSONParser();
        return jp.getJasonfromEntityInfo(Url);
    }
    public JSONObject profile_entry(String Url,String occupation,String address)
    {
        namevaluepair=new ArrayList<NameValuePair>();
        namevaluepair.add(new BasicNameValuePair("occupation", occupation));
        namevaluepair.add(new BasicNameValuePair("address", address));
//        namevaluepair.add(new BasicNameValuePair("id", id));
        JSONParser jp=new JSONParser();
        return jp.getJasonfromEntitySignup(Url, namevaluepair);
    }
}
