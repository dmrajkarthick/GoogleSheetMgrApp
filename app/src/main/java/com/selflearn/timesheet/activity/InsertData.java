package com.selflearn.timesheet.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.selflearn.timesheet.MainActivity;
import com.selflearn.timesheet.R;
import com.selflearn.timesheet.util.Controller;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rkarthic on 09-03-2018.
 */

public class InsertData extends AppCompatActivity{

    private EditText et_clientName, et_jobType, et_siteID, et_siteName, et_travelToSite, et_travelFromSite, et_odometerReading,
                        et_kmsDriven, et_startTime, et_endTime, et_breakTime, et_hoursOnSite;
    private Button insertData;

    String clientName, jobType, siteID, siteName, travelToSite, travelFromSite, odometerReading, kmsDriven, startTime, endTime, breakTime, hoursOnSite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_data);

        initialize();

        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientName = et_clientName.getText().toString();
                jobType = et_jobType.getText().toString();
                siteID = et_siteID.getText().toString();
                siteName = et_siteName.getText().toString();
                travelToSite = et_travelToSite.getText().toString();
                travelFromSite = et_travelFromSite.getText().toString();
                odometerReading = et_odometerReading.getText().toString();
                kmsDriven = et_kmsDriven.getText().toString();
                startTime = et_startTime.getText().toString();
                endTime = et_endTime.getText().toString();
                breakTime = et_breakTime.getText().toString();
                hoursOnSite = et_hoursOnSite.getText().toString();

                new InsertDataActivity().execute();

            }
        });
    }

    private void initialize() {
        et_clientName = (EditText)findViewById(R.id.clientName);
        et_jobType = (EditText)findViewById(R.id.jobType);
        et_siteID = (EditText)findViewById(R.id.siteID);
        et_siteName = (EditText)findViewById(R.id.siteName);
        et_travelToSite = (EditText)findViewById(R.id.travelToSite);
        et_travelFromSite = (EditText)findViewById(R.id.travelFromSite);
        et_odometerReading = (EditText)findViewById(R.id.odometerReading);
        et_kmsDriven = (EditText)findViewById(R.id.kmsDriven);
        et_startTime = (EditText)findViewById(R.id.startTime);
        et_endTime = (EditText)findViewById(R.id.endTime);
        et_breakTime = (EditText) findViewById(R.id.breakTime);
        et_hoursOnSite = (EditText) findViewById(R.id.hoursOnSite);
        insertData = (Button)findViewById(R.id.insertData);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    class InsertDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        String result=null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(InsertData.this);
            dialog.setTitle("Inserting Data, Please wait!...");
            dialog.setMessage("Inserting your values..");
            /*if(!isFinishing())
                dialog.show();*/

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = Controller.insertData("blank", clientName, jobType, siteID, siteName, travelToSite, travelFromSite, odometerReading, kmsDriven, startTime, endTime, breakTime, hoursOnSite);
            Log.i(Controller.TAG, "Json obj ");

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {

                    result=jsonObject.getString("result");

                }
            } catch (JSONException je) {
                Log.i(Controller.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}
