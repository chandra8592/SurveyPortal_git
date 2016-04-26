package com.creedglobal.survey.surveyportal.infox;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.creedglobal.survey.surveyportal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Teams extends ListActivity {
    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String url = "http://zinee.in/demo/infox/teams.php";

    // JSON Node names

    private static final String TAG_ID = "rank";
    private static final String TAG_NAME = "team_name";
    private static final String TAG_FOR = "for_points";
    private static final String TAG_TOTAL = "total_points";

    // contacts JSONArray
    JSONArray contacts = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        // Calling async task to get json
        new GetContacts().execute();
        contactList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();
    }
    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Teams.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    contacts = jsonObj.getJSONArray("info");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String rank = c.getString(TAG_ID);
                        String name = c.getString(TAG_NAME);
                        String forp = c.getString(TAG_FOR);
                        String total = c.getString(TAG_TOTAL);


                        // Phone node is JSON Object
//                            JSONObject phone = c.getJSONObject(TAG_PHONE);
//                            String mobile = phone.getString(TAG_PHONE_MOBILE);
//                            String home = phone.getString(TAG_PHONE_HOME);
//                            String office = phone.getString(TAG_PHONE_OFFICE);

                        // tmp hashmap for single contact
                        HashMap<String, String> contact = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        contact.put(TAG_ID, rank);
                        contact.put(TAG_NAME, name);
                        contact.put(TAG_FOR, forp);
                        contact.put(TAG_TOTAL, total);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(Teams.this, contactList,R.layout.team_info, new String[] {
                    TAG_ID, TAG_NAME, TAG_FOR,TAG_TOTAL }, new int[] {
                    R.id.rank,
                    R.id.team, R.id.forp,R.id.total });

            setListAdapter(adapter);
        }

    }
}
