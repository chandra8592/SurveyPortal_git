package com.creedglobal.survey.surveyportal;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;


import com.creedglobal.survey.surveyportal.Database.DBHandler;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;
    DBHandler db;
    Profile profile;
    EditText edt_usr,edt_pwd;
    String username,password;
    boolean loginStatus = false;
    int id = 1;
    ViewFlipper flipper;
    int res[] = {
            R.drawable.me,
            R.drawable.me1,
            R.drawable.me3,
            R.drawable.me2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=new DBHandler(this);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);
        edt_usr= (EditText) findViewById(R.id.edt_username);
        edt_pwd= (EditText) findViewById(R.id.edt_password);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        flipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        if (loginStatus = false) {
            startActivity(new Intent(getApplicationContext(), MainScreen.class));
        } else {

            int width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
            // adding all images in flipper
            for (int i = 0; i < res.length; i++) {
                ImageView image = new ImageView(this);
                image.setImageResource(res[i]);
                flipper.addView(image);
            }
            //set in/out flipping animation
            flipper.setInAnimation(this, android.R.anim.fade_in);
//            flipper.setOutAnimation(this, android.R.anim.fade_out);
            flipper.setAutoStart(true);
            flipper.setFlipInterval(2000);

            // Facebook login integration
            loginButton.setReadPermissions("email", "user_friends");
            callbackManager = CallbackManager.Factory.create();
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.i("my_info", "Login Success");
                    Log.i("my_info", "userid =>" + loginResult.getAccessToken().getUserId());
                    profile = Profile.getCurrentProfile();
                    String name = profile.getFirstName();
                    Log.i("my_info", "Name :" + name);
                    startActivity(new Intent(getApplicationContext(), MainScreen.class));
                }
                @Override
                public void onCancel() {
                    Log.i("my_info", "Login Cancel");
                }
                @Override
                public void onError(FacebookException error) {
                    Log.i("my_info", "Login Error");
                }
            });
//            End of facebook integration.

            // to print the hash key for facebook purpose
            try {
                PackageInfo info = getPackageManager().getPackageInfo("com.facebook.samples.loginhowto", PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
            } catch (PackageManager.NameNotFoundException e) {

            } catch (NoSuchAlgorithmException e) {

            }
            // end of hash key
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    public void gotoRegister(View view) {
        startActivity(new Intent(getApplicationContext(), Registration.class));
    }

    public void forgotPassword(View view) {
        startActivity(new Intent(getApplicationContext(), Forgot.class));
    }

    public void clickLogin(View view) {
        username=edt_usr.getText().toString();
        password=edt_pwd.getText().toString();
        if (db.checkLogin(username,password)){
            Log.i("infoo","Login Success");
            startActivity(new Intent(getApplicationContext(), MainScreen.class));

        }
        else {
            Toast.makeText(this,"Wrong username/password",Toast.LENGTH_LONG).show();
            Log.i("infoo","Login Failed");
        }
    }
}





//package com.creedglobal.survey.surveyportal;

//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.content.pm.Signature;
//import android.net.ConnectivityManager;
//import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Base64;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//import android.widget.ViewFlipper;
//
////
////import com.creedglobal.survey.surveyportal.infox.Teams;
//import com.creedglobal.survey.surveyportal.Create.CreateSurvey;
//import com.creedglobal.survey.surveyportal.Database.CommonUtil;
//import com.creedglobal.survey.surveyportal.Database.Webservice;
//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.FacebookSdk;
//import com.facebook.Profile;
//import com.facebook.appevents.AppEventsLogger;
//import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//public class MainActivity extends AppCompatActivity {
//    private static final int REQUEST_SIGNUP = 0;
//    private static final String TAG = "MainActivity";
//    EditText emailtext, passwordtext;
//    String email, password;
//    CallbackManager callbackManager;
//    Button loginButton,SignButton;
//    SharedPreferences sharedpreferences;
//    Profile profile;
//    boolean loginStatus = false;
//    int id = 1;
//    ViewFlipper flipper;
//    int res[] = {
//            R.drawable.me,
//            R.drawable.me1,
//            R.drawable.me3,
//            R.drawable.me2
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(this.getApplicationContext());
//        AppEventsLogger.activateApp(this);
//        setContentView(R.layout.activity_main);
//        sharedpreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
//        emailtext = (EditText) findViewById(R.id.email);
//        passwordtext = (EditText) findViewById(R.id.pswd);
//        SignButton=(Button)findViewById(R.id.button3);
//        loginButton = (Button) findViewById(R.id.button);
//        flipper = (ViewFlipper) findViewById(R.id.viewFlipper);
//
//        if (loginStatus = false) {
//            startActivity(new Intent(getApplicationContext(), MainScreen.class));
//        } else {
//
//            int width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
//            // adding all images in flipper
//            for (int i = 0; i < res.length; i++) {
//                ImageView image = new ImageView(this);
//                image.setImageResource(res[i]);
//                flipper.addView(image);
//            }
//            //set in/out flipping animation
//            flipper.setInAnimation(this, android.R.anim.fade_in);
////            flipper.setOutAnimation(this, android.R.anim.fade_out);
//            flipper.setAutoStart(true);
//            flipper.setFlipInterval(2000);
//
//            // Facebook login integration
////            loginButton.setReadPermissions("email", "user_friends");
////            callbackManager = CallbackManager.Factory.create();
////            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
////                @Override
////                public void onSuccess(LoginResult loginResult) {
////                    Log.i("my_info", "Login Success");
////                    Log.i("my_info", "userid =>" + loginResult.getAccessToken().getUserId());
////                    profile = Profile.getCurrentProfile();
////                    String name = profile.getFirstName();
////                    Log.i("my_info", "Name :" + name);
////                    startActivity(new Intent(getApplicationContext(), MainScreen.class));
////                }
////                @Override
////                public void onCancel() {
////                    Log.i("my_info", "Login Cancel");
////                }
////                @Override
////                public void onError(FacebookException error) {
////                    Log.i("my_info", "Login Error");
////                }
////            });
////            End of facebook integration.
//
//            // to print the hash key for facebook purpose
////            try {
////                PackageInfo info = getPackageManager().getPackageInfo("com.facebook.samples.loginhowto", PackageManager.GET_SIGNATURES);
////                for (Signature signature : info.signatures) {
////                    MessageDigest md = MessageDigest.getInstance("SHA");
////                    md.update(signature.toByteArray());
////                    Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
////                }
////            } catch (PackageManager.NameNotFoundException e) {
////
////            } catch (NoSuchAlgorithmException e) {
////
////            }
//            // end of hash key
//        }
//        loginButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                Intent i= new Intent(MainActivity.this,MainScreen.class);
//                startActivity(i);
////                if (checkInternetConenction()) {
////                    login();
////                }
//            }
//        });
//
//        SignButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                Intent i= new Intent(MainActivity.this,Registration.class);
//                startActivity(i);
//
//
//            }
//        });
//    }
//
////    @Override
////    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////        callbackManager.onActivityResult(requestCode, resultCode, data);
////    }
////
////
//////    public void gotoRegister(View view) {
//////        startActivity(new Intent(getApplicationContext(), Registration.class));
//////    }
//////
//////    public void forgotPassword(View view) {
//////        startActivity(new Intent(getApplicationContext(), Forgot.class));
//////    }
////
////    public void gotoHome(View view) {
////        startActivity(new Intent(getApplicationContext(), MainScreen.class));
////    }
//////    public void gotoInfox(View view){
////        startActivity(new Intent(getApplicationContext(), Teams.class));
////    }
//
//    public void login() {
//        Log.d(TAG, "Login");
//
//        if (!validate()) {
//            onLoginFailed();
//            return;
//        }
//
//        loginButton.setEnabled(false);
//
//        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Authenticating...");
//        progressDialog.show();
//
//        email = emailtext.getText().toString();
//        password = passwordtext.getText().toString();
//
//        // TODO: Implement your own authentication logic here.
//
//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onLoginSuccess or onLoginFailed
//                        onLoginSuccess();
//                        // onLoginFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);
//    }
//
//    public boolean validate() {
//        boolean valid = true;
//
//        String email = emailtext.getText().toString();
//        String password = passwordtext.getText().toString();
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
//        return valid;
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_SIGNUP) {
//            if (resultCode == RESULT_OK) {
//
//                // TODO: Implement successful signup logic here
//                // By default we just finish the Activity and log them in automatically
//                this.finish();
//            }
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        // disable going back to the MainActivity
//        finish();
//    }
//    public void onLoginSuccess() {
//        loginButton.setEnabled(true);
//        new login().execute(new String[]{CommonUtil.SERVER_URL + CommonUtil.login, email, password});
//    }
//
//    public void onLoginFailed() {
//        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
//
//        loginButton.setEnabled(true);
//    }
//
//    private boolean checkInternetConenction() {
//        // get Connectivity Manager object to check connection
//        ConnectivityManager connec =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
//
//        // Check for network connections
//        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
//
//                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
//                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
//                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
//            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
//            return true;
//        }else if (
//                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
//                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
//            Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
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
//                    MainActivity.this, "", "Please wait");
//            progressdialog.setCancelable(false);
//            progressdialog.setIndeterminate(true);
//        }
//
//        @Override
//        protected JSONObject doInBackground(String... params) {
//            // TODO Auto-generated method stub
//            Webservice ws = new Webservice();
//            return ws.login(params[0], params[1], params[2]);
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
//                    Toast.makeText(MainActivity.this, "login_successfully...", Toast.LENGTH_LONG).show();
//                    Intent in = new Intent(MainActivity.this, MainScreen.class);
//                    startActivity(in);
//
//                } else {
//                    Toast.makeText(MainActivity.this, Json_result, Toast.LENGTH_LONG).show();
//                }
//                finish();
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//
//            }
//
//        }
//    }
//}
