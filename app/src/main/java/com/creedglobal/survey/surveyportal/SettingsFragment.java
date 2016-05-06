package com.creedglobal.survey.surveyportal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.creedglobal.survey.surveyportal.Database.CommonUtil;
import com.creedglobal.survey.surveyportal.Database.Webservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SettingsFragment extends Fragment {
    Context context;
    TextView txtName, txtEmail, edtphone;
    EditText edtoccupation, edt_address;
    Button btn_profile_submit;
    String idmain;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View getView = inflater.inflate(R.layout.fragment_settings, container, false);
        Toolbar toolbar = (Toolbar) getView.findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");

        Intent in = getActivity().getIntent();
        idmain = in.getStringExtra("id");
        txtName = (TextView) getView.findViewById(R.id.txt_fullname);
        txtEmail = (TextView) getView.findViewById(R.id.txt_email);
        edtphone = (TextView) getView.findViewById(R.id.edt_Phone);
        edtoccupation = (EditText) getView.findViewById(R.id.edt_occupation);
        edt_address = (EditText) getView.findViewById(R.id.edt_address);
        btn_profile_submit = (Button) getView.findViewById(R.id.btn_submit);
        btn_profile_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String occupation = edtoccupation.getText().toString();
                String address = edt_address.getText().toString();

                new Register().execute(new String[]{CommonUtil.SERVER_URL + CommonUtil.profile_entry, occupation, address, idmain});
            }
        });
        if (checkInternetConenction())
            new Info().execute(new String[]{CommonUtil.SERVER_URL + CommonUtil.profile_info});

        return getView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    class Info extends AsyncTask<String, Void, JSONObject> {

        private ProgressDialog progressdialog;
        String Json_result;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressdialog = ProgressDialog.show(
                    getActivity(), "", "Please wait");
            progressdialog.setCancelable(false);
            progressdialog.setIndeterminate(true);
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            // TODO Auto-generated method stub
            Webservice ws = new Webservice();
            return ws.info(params[0]);
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            // TODO Auto-generated method stub
            if (progressdialog.isShowing()) {
                progressdialog.dismiss();
            }
            System.out.println("result::" + result);

            try {
                JSONArray info = result.getJSONArray("info");
                for (int i = 0; i < info.length(); i++) {
                    JSONObject c = info.getJSONObject(i);

                    String id = c.getString("id");
                    int id1 = Integer.valueOf(id);
                    if (id.equals(idmain)) {

                        String name = c.getString("name");
                        String email = c.getString("email");
                        String phone = c.getString("phone");
                        String occupation = c.getString("occupation");
                        String address = c.getString("address");

                        txtName.setText(name);
                        txtEmail.setText(email);
                        edtphone.setText(phone);
                        if (occupation.equals("null"))
                            edtoccupation.setHint("Enter your occupation");
                        else
                            edtoccupation.setText(occupation);
                        if (address.equals("null"))
                            edt_address.setHint("Enter Address Here");
                        else
                            edt_address.setText(address);
                    }
                }

                // Phone node is JSON Object

            } catch (JSONException e) {
                // TODO Auto-generated catch block

            }
        }
    }

    class Register extends AsyncTask<String, Void, JSONObject> {

        private ProgressDialog progressdialog;
        String Json_result;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressdialog = ProgressDialog.show(
                    getActivity(), "", "Please wait");
            progressdialog.setCancelable(false);
            progressdialog.setIndeterminate(true);
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            // TODO Auto-generated method stub
            Webservice ws = new Webservice();
            return ws.profile_entry(params[0], params[1], params[2]);

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
                getActivity().finish();
            } catch (JSONException e) {
                // TODO Auto-generated catch block

            }
        }

    }

    private boolean checkInternetConenction() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||

                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            return true;
        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            Toast.makeText(getActivity(), " No Connection.Please Try Again. ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

    }

