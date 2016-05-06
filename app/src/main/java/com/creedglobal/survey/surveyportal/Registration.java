package com.creedglobal.survey.surveyportal;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.creedglobal.survey.surveyportal.Database.DBHandler;

public class Registration extends AppCompatActivity {

    EditText edt_username,edt_email,edt_password,edt_mobile,edt_occupation;
    String username,email,password,mobile,occupation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        edt_username = (EditText) findViewById(R.id.edt_name);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_mobile = (EditText) findViewById(R.id.edt_mobile);
        edt_occupation = (EditText) findViewById(R.id.edt_occupation);
    }
    public void loginPage(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
    public void register(View view){
        if (validate()){

            String[] args={username,email,password,mobile,occupation};
            DBHandler db = new DBHandler(this);
            if (db.register(args)){
                Toast.makeText(this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainScreen.class));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    this.finishAffinity();
                }
                else {
                    finish();
                }
            }
            else {
                edt_email.setError("Email Already Registered");
            }
        }
    }
    public boolean validate(){
        boolean valid=true;
        username=edt_username.getText().toString();
        email=edt_email.getText().toString();
        password=edt_password.getText().toString();
        mobile=edt_mobile.getText().toString();
        occupation=edt_occupation.getText().toString();

        if (username.isEmpty() || username.length() < 4) {
            edt_username.setError("at least 4 characters");
            valid = false;
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edt_email.setError("enter a valid email address");
            valid = false;
        }

        if (password.isEmpty() || password.length() < 6) {
            edt_password.setError("enter atleast 6 alphanumeric characters");
            valid = false;
        }

        if (mobile.isEmpty() || mobile.length() < 10 || mobile.length() > 10) {
            edt_mobile.setError("Enter Valid Mobile Number");
            valid = false;
        }
        if (occupation.isEmpty() || occupation.length() < 5) {
            edt_occupation.setError("at least 5 characters");
            valid = false;
        }
        return valid;
    }
}


//package com.creedglobal.survey.surveyportal;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.ConnectivityManager;
//import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.creedglobal.survey.surveyportal.Database.CommonUtil;
//import com.creedglobal.survey.surveyportal.Database.Webservice;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class Registration extends AppCompatActivity {
//
//    private static final String TAG = "Registration";
//EditText nametext,emailtext,passwordtext,mobiletext,occupationtext;
//    String name,email,password,mobile,occupation;
//    Button register;
//
//    SharedPreferences sharedpreferences;
//    TextView Already_login;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_registration);
//
//        nametext=(EditText)findViewById(R.id.fullname);
//        emailtext=(EditText)findViewById(R.id.email_address);
//        passwordtext=(EditText)findViewById(R.id.signpass);
//        mobiletext=(EditText)findViewById(R.id.sign_mobile);
//        occupationtext=(EditText) findViewById(R.id.occupation);
//        register=(Button)findViewById(R.id.button2);
//        Already_login=(TextView)findViewById(R.id.textView3);
//
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(checkInternetConenction())
//                {
//                    signup();
//                }
//            }
//        });
//        Already_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Finish the registration screen and return to the Login activity
//                finish();
//            }
//        });
//    }
//
//
//    public void signup() {
//        Log.d(TAG, "Signup");
//
//        if (!validate()) {
//            onSignupFailed();
//            return;
//        }
//
//        register.setEnabled(false);
//
//        final ProgressDialog progressDialog = new ProgressDialog(Registration.this);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Creating Account...");
//        progressDialog.show();
//
//        name = nametext.getText().toString();
//        email = emailtext.getText().toString();
//        password = passwordtext.getText().toString();
//        mobile= mobiletext.getText().toString();
//        occupation=occupationtext.getText().toString();
//        // TODO: Implement your own signup logic here.
//
//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onSignupSuccess or onSignupFailed
//                        // depending on success
//                        onSignupSuccess();
//                        // onSignupFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);
//    }
//
//    public void onSignupSuccess() {
//        register.setEnabled(true);
//        setResult(RESULT_OK, null);
//        new Register().execute(new String[]{CommonUtil.SERVER_URL + CommonUtil.cust_signup, name, email, password, mobile, occupation});
//
//    }
//    public void onSignupFailed() {
//        Toast.makeText(getBaseContext(), "signup failed", Toast.LENGTH_LONG).show();
//
//        register.setEnabled(true);
//    }
//
//    public boolean validate() {
//        boolean valid = true;
//
//        String name = nametext.getText().toString();
//        String email = emailtext.getText().toString();
//        String password = passwordtext.getText().toString();
//        String mobile= mobiletext.getText().toString();
//        String occupation= occupationtext.getText().toString();
//        if (name.isEmpty() || name.length() < 4) {
//            nametext.setError("at least 4 characters");
//            valid = false;
//        } else {
//            nametext.setError(null);
//        }
//
//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            emailtext.setError("enter a valid email address");
//            valid = false;
//        } else {
//            emailtext.setError(null);
//        }
//
//        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
//            passwordtext.setError("between 4 and 10 alphanumeric characters");
//            valid = false;
//        } else {
//            passwordtext.setError(null);
//        }
//        if (mobile.isEmpty() || mobile.length() < 10 || mobile.length() > 10) {
//            mobiletext.setError("Enter Valid Mobile Number");
//            valid = false;
//        } else {
//            mobiletext.setError(null);
//        }
//        if (occupation.isEmpty() || occupation.length() < 5) {
//            occupationtext.setError("at least 5 characters");
//            valid = false;
//        } else {
//            occupationtext.setError(null);
//        }
//        return valid;
//    }
//
//
//    class Register extends AsyncTask<String, Void, JSONObject> {
//
//        private ProgressDialog progressdialog;
//        String Json_result;
//
//        @Override
//        protected void onPreExecute() {
//            // TODO Auto-generated method stub
//            super.onPreExecute();
//            progressdialog = ProgressDialog.show(
//                    Registration.this, "", "Please wait");
//            progressdialog.setCancelable(false);
//            progressdialog.setIndeterminate(true);
//        }
//
//        @Override
//        protected JSONObject doInBackground(String... params) {
//            // TODO Auto-generated method stub
//            Webservice ws = new Webservice();
//            return ws.signup(params[0], params[1], params[2],
//                    params[3],params[4],params[5]);
//        }
//
//        @Override
//        protected void onPostExecute(JSONObject result) {
//            // TODO Auto-generated method stub
//            if (progressdialog.isShowing()) {
//                progressdialog.dismiss();
//            }
//            System.out.println("result::" + result);
//
//            try {
//                Json_result = result.getString("msg");
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//
//            }
//            if (Json_result.equals("user_added")) {
//                new login().execute(new String[]{CommonUtil.SERVER_URL + CommonUtil.confirm_login, email, password});
//            }
//            else
//                Toast.makeText(Registration.this, Json_result, Toast.LENGTH_LONG).show();
//        }
//    }
//    private boolean checkInternetConenction() {
//        // get Connectivity Manager object to check connection
//        ConnectivityManager connec = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
//
//        // Check for network connections
//         if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
//
//                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
//                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
//                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
//            return true;
//        } else if (
//                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
//                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
//            Toast.makeText(this, " No Connection.Please Try Again. ", Toast.LENGTH_LONG).show();
//            return false;
//        }
//        return false;
//    }
//
//
//    class login extends AsyncTask<String, Void, JSONObject> {
//
//        private ProgressDialog progressdialog;
//        String Json_result;
//
//        @Override
//        protected void onPreExecute() {
//            // TODO Auto-generated method stub
//            super.onPreExecute();
//            progressdialog = ProgressDialog.show(
//                    Registration.this, "", "Please wait");
//            progressdialog.setCancelable(false);
//            progressdialog.setIndeterminate(true);
//        }
//
//        @Override
//        protected JSONObject doInBackground(String... params) {
//            // TODO Auto-generated method stub
//            Webservice ws = new Webservice();
//            return ws.login1(params[0], params[1], params[2]);
//
//        }
//
//        @Override
//        protected void onPostExecute(JSONObject result) {
//            // TODO Auto-generated method stub
//            if (progressdialog.isShowing()) {
//                progressdialog.dismiss();
//            }
//            System.out.println("result::" + result);
//
//            try {
//                Json_result = result.getString("msg");
//
//                if (Json_result.equals("user_found")) {
//                    JSONArray jarray = result.getJSONArray("info");
//                    JSONObject job = jarray.getJSONObject(0);
//                    String id = job.getString("id");
//                    SharedPreferences.Editor editor = sharedpreferences.edit();
//                    editor.putString("id", id);
//                    editor.commit();
//                    Toast.makeText(Registration.this, "Signups_successfully...", Toast.LENGTH_LONG).show();
//
//                } else {
//                    Toast.makeText(Registration.this, Json_result, Toast.LENGTH_LONG).show();
//                }
//                finish();
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//
//            }
//
//        }
//    }
//
//}
