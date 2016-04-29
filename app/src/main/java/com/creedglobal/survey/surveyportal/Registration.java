package com.creedglobal.survey.surveyportal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.creedglobal.survey.surveyportal.Database.CommonUtil;
import com.creedglobal.survey.surveyportal.Database.Webservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Registration extends AppCompatActivity {

    private static final String TAG = "Registration";
EditText name,sign_email,sign_pass,sign_mobile;
    String fullname,email_sign,pass_sign,mobile_sign;
    Button register;
    SharedPreferences sharedpreferences;
    TextView Already_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name=(EditText)findViewById(R.id.fullname);
        sign_email=(EditText)findViewById(R.id.email_address);
        sign_pass=(EditText)findViewById(R.id.signpass);
        sign_mobile=(EditText)findViewById(R.id.sign_mobile);
        register=(Button)findViewById(R.id.button2);
        Already_login=(TextView)findViewById(R.id.textView3);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternetConenction())
                {
                    signup();
                }
            }
        });
        Already_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void loginPage(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        register.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Registration.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        fullname = name.getText().toString();
        email_sign = sign_email.getText().toString();
        pass_sign = sign_pass.getText().toString();
        mobile_sign= sign_mobile.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onSignupSuccess() {
        register.setEnabled(true);
        setResult(RESULT_OK, null);
        new Register().execute(new String[]{CommonUtil.SERVER_URL + CommonUtil.cust_signup, fullname, email_sign, pass_sign,mobile_sign});

    }
    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        register.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String fullname = name.getText().toString();
        String email_sign = sign_email.getText().toString();
        String pass_sign = sign_pass.getText().toString();
        String mobile_sign= sign_mobile.getText().toString();

        if (fullname.isEmpty() || fullname.length() < 6) {
            name.setError("at least 3 characters");
            valid = false;
        } else {
            name.setError(null);
        }

        if (email_sign.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email_sign).matches()) {
            sign_email.setError("enter a valid email address");
            valid = false;
        } else {
            sign_email.setError(null);
        }

        if (pass_sign.isEmpty() || pass_sign.length() < 4 || pass_sign.length() > 10) {
            sign_pass.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            sign_pass.setError(null);
        }
        if (mobile_sign.isEmpty() || mobile_sign.length() < 10 || mobile_sign.length() > 10) {
            sign_mobile.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            sign_pass.setError(null);
        }

        return valid;
    }


    class Register extends AsyncTask<String, Void, JSONObject> {

        private ProgressDialog progressdialog;
        String Json_result;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressdialog = ProgressDialog.show(
                    Registration.this, "", "Please wait");
            progressdialog.setCancelable(false);
            progressdialog.setIndeterminate(true);
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            // TODO Auto-generated method stub
            Webservice ws = new Webservice();
            return ws.signup(params[0], params[1], params[2],
                    params[3],params[4]);
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            // TODO Auto-generated method stub
            if (progressdialog.isShowing()) {
                progressdialog.dismiss();
            }
            System.out.println("result::" + result);

            try {
                Json_result = result.getString("msg");
            } catch (JSONException e) {
                // TODO Auto-generated catch block

            }
            if (Json_result.equals("user_added")) {
                new login().execute(new String[]{CommonUtil.SERVER_URL + CommonUtil.confirm_login, email_sign, pass_sign});
            }
            else
                Toast.makeText(Registration.this, Json_result, Toast.LENGTH_LONG).show();
        }
    }
    private boolean checkInternetConenction() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||

                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            return true;
        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            Toast.makeText(this, " No Connection.Please Try Again. ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }


    class login extends AsyncTask<String, Void, JSONObject> {

        private ProgressDialog progressdialog;
        String Json_result;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressdialog = ProgressDialog.show(
                    Registration.this, "", "Please wait");
            progressdialog.setCancelable(false);
            progressdialog.setIndeterminate(true);
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            // TODO Auto-generated method stub
            Webservice ws = new Webservice();
            return ws.login1(params[0], params[1], params[2]);

        }

        @Override
        protected void onPostExecute(JSONObject result) {
            // TODO Auto-generated method stub
            if (progressdialog.isShowing()) {
                progressdialog.dismiss();
            }
            System.out.println("result::" + result);

            try {
                Json_result = result.getString("msg");

                if (Json_result.equals("user_found")) {
                    JSONArray jarray = result.getJSONArray("info");
                    JSONObject job = jarray.getJSONObject(0);
                    String id = job.getString("id");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("id", id);
                    editor.commit();
                    Toast.makeText(Registration.this, "Signups_successfully...", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(Registration.this, Json_result, Toast.LENGTH_LONG).show();
                }
                finish();
            } catch (JSONException e) {
                // TODO Auto-generated catch block

            }

        }
    }

}
