package com.selflearn.timesheet.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.selflearn.timesheet.R;
import com.selflearn.timesheet.util.Controller;
import com.selflearn.timesheet.util.ReadAllDataModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rkarthic on 09-03-2018.
 */

public class DeleteData extends AppCompatActivity{

    Button yes_btn, no_btn;
    TextView tv_confirmation, tv_content;
    String contentToBeDeleted, result;
    View view;
    ReadAllDataModel contentData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_data);

        initialize();
        contentToBeDeleted = getIntent().getExtras().getString("contentToBeDeleted");
        tv_content.setText(contentToBeDeleted);

        contentData = new ReadAllDataModel();
        contentData = new Gson().fromJson(contentToBeDeleted, ReadAllDataModel.class);

        contentToBeDeleted = "Client: " + contentData.getClient()+"\n"
                            + "Job Type: " +contentData.getJobType()+"\n"
                            + "Site Name: " +contentData.getSiteName()+"\n"
                            + "Site ID: " +contentData.getSite_id_lr_code()+"\n"
                            + "Travel To Site: " +contentData.getTravelToSite()+"\n"
                            + "Travel From Site: " +contentData.getTravelFromSite()+"\n"
                            + "Odometer Reading: " +contentData.getOdometerReading()+"\n"
                            + "Kilometers: " +contentData.getKilometers()+"\n"
                            + "StartTime: " +contentData.getStartTime()+"\n"
                            + "End Time: " +contentData.getEndTime()+"\n"
                            + "Break Time: " +contentData.getBreakTime()+"\n"
                            + "Hours On Site: " +contentData.getHoursOnSite()+"\n";

        tv_content.setText(contentToBeDeleted);

        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            new DeleteDataActiviy().execute();
            }
        });

        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReadAllData.class);
                startActivity(intent);
            }
        });
    }

    private void initialize() {
        yes_btn = (Button) findViewById(R.id.yes_btn);
        no_btn = (Button) findViewById(R.id.no_btn);
        tv_confirmation = (TextView) findViewById(R.id.tv_confirmation);
        tv_content = (TextView) findViewById(R.id.tv_content);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), ReadAllData.class);
        startActivity(intent);
    }

    class DeleteDataActiviy extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(DeleteData.this);
            dialog.setTitle("Please Wait...");
            dialog.setMessage("Fetching your values");
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            Log.i(Controller.TAG, "Content to Be Deleted: " + contentToBeDeleted);
            JSONObject jsonObject = Controller.deleteData(contentData.getJobNumber());
            Log.i(Controller.TAG, "Json obj " + jsonObject);

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {

                    //JSONObject user = jsonObject.getJSONObject("CLIENT");
                    result = jsonObject.getString("result");

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

            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), ReadAllData.class);
            startActivity(intent);
        }
    }
}
