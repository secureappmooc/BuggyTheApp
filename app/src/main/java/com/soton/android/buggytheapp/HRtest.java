package com.soton.android.buggytheapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by AndroidHPE on 12/6/2016.
 */

public class HRtest extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httprequest_layout);
    }


    public void httprequestsubmit (View v)
    {
        if (v.getId()==R.id.btnHRSubmit)
        {
            EditText HRuserName= (EditText)findViewById(R.id.etHRUsername);
            EditText HRpassWord= (EditText)findViewById(R.id.etHRPassword);
            //TextView HRresponse= (TextView) findViewById(R.id.tvHRRespond);

            String HRusernamestr = HRuserName.getText().toString();
            String HRpasswordstr = HRpassWord.getText().toString();
            String serverURL= "10.0.0.2/login.php?username="+HRusernamestr+" &password="+HRpasswordstr;

            new MyAsyncTask().execute(serverURL,"login");
            //HRresponse.setText(MyAsyncTask.Status.values().toString());
        }
    }


    public class MyAsyncTask extends AsyncTask< String, String, String>
    {

        @Override
        protected String doInBackground(String... params) {
            try {
                String loginrepond="10.0.0.2/login";
                URL serverurl = new URL(params[0]);
                HttpURLConnection serverurlConnection = (HttpURLConnection) serverurl.openConnection();
                serverurlConnection.setConnectTimeout(3000);
                try {
                    InputStream in = new BufferedInputStream(serverurlConnection.getInputStream());
                    BufferedReader br=new BufferedReader( new InputStreamReader(in));
                    String tempstr;
                    try
                    {while ((tempstr=br.readLine())!=null)
                    {
                        loginrepond+=tempstr;
                    }
                        in.close();
                    }catch (Exception ex){}
                    publishProgress(loginrepond);
                } finally {
                    serverurlConnection.disconnect();
                }

            }catch (Exception ex){}
            return null;
        }

        protected void onProgressUpdate(String... progress)
        {
            try {
                Toast.makeText(getApplicationContext(),progress[0],Toast.LENGTH_LONG).show();

            } catch (Exception ex) {
            }
        }



    }




}