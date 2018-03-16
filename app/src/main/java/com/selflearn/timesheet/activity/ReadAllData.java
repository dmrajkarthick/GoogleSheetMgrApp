package com.selflearn.timesheet.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.selflearn.timesheet.R;
import com.selflearn.timesheet.util.Controller;
import com.selflearn.timesheet.util.ReadAllDataModel;
import com.selflearn.timesheet.util.ReadAllDataAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rkarthic on 09-03-2018.
 */

public class ReadAllData extends AppCompatActivity{

    private ListView listView;
    private ArrayList<ReadAllDataModel> list;
    private ReadAllDataAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_all_data);
    }


    class ReadData1 extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */

            x = list.size();

            if (x == 0)
                jIndex = 0;
            else
                jIndex = x;

            dialog = new ProgressDialog(ReadAllData.this);
            dialog.setTitle("Hey Wait Please..." + x);
            dialog.setMessage("Fetching all the Values");
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = Controller.readAllData();
            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {
                    /**
                     * Check Length...
                     */
                    if (jsonObject.length() > 0) {
                        /**
                         * Getting Array named "contacts" From MAIN Json Object
                         */
                        JSONArray array = jsonObject.getJSONArray("records");

                        /**
                         * Check Length of Array...
                         */


                        int lenArray = array.length();
                        if (lenArray > 0) {
                            for (; jIndex < lenArray; jIndex++) {

                                /**
                                 * Creating Every time New Object
                                 * and
                                 * Adding into List
                                 */
                                ReadAllDataModel model = new ReadAllDataModel();

                                /**
                                 * Getting Inner Object from contacts array...
                                 * and
                                 * From that We will get Name of that Contact
                                 *
                                 */
                                JSONObject innerObject = array.getJSONObject(jIndex);

                                String job_number = innerObject.getString("JOB_NUMBER");
                                String date_time = innerObject.getString("DATE/TIME");
                                String client = innerObject.getString("CLIENT");
                                String job_type = innerObject.getString("JOB_TYPE");
                                String site_id_lr_code = innerObject.getString("SITE_ID/LRD_CODE");
                                String site_name = innerObject.getString("SITE_NAME");
                                String travel_to_site = innerObject.getString("TRAVEL_TO_SITE");
                                String travel_from_site = innerObject.getString("TRAVEL_FROM_SITE");
                                String odometer_reading = innerObject.getString("ODOMETER_READING");
                                String kilometers = innerObject.getString("KILOMETERS");
                                String start_time = innerObject.getString("START_TIME");
                                String end_time = innerObject.getString("END_TIME");
                                String break_time = innerObject.getString("BREAK");
                                String hours_on_site = innerObject.getString("HOURS_ON_SITE");

//  String image = innerObject.getString(Keys.KEY_IMAGE);
                                /**
                                 * Getting Object from Object "phone"
                                 */
                                //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                                //String phone = phoneObject.getString(Keys.KEY_MOBILE);

                                model.setJob_number(job_number);
                                model.setDate_time(date_time);
                                model.setClient(client);
                                model.setJob_type(job_type);
                                model.setSite_id_lr_code(site_id_lr_code);
                                model.setSite_name(site_name);
                                model.setTravel_to_site(travel_to_site);
                                model.setTravel_from_site(travel_from_site);
                                model.setOdometer_reading(odometer_reading);
                                model.setKilometers(kilometers);
                                model.setStart_time(start_time);
                                model.setEnd_time(end_time);
                                model.setBreak_time(break_time);
                                model.setHours_on_site(hours_on_site);
                                //                              model.setImage(image);

                                /**
                                 * Adding name and phone concatenation in List...
                                 */
                                list.add(model);
                            }
                        }
                    }
                } else {

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
            /**
             * Checking if List size if more than zero then
             * Update ListView
             */
            if (list.size() > 0) {
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
            }
        }
    }
}
