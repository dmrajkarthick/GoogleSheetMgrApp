package com.selflearn.timesheet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.selflearn.timesheet.util.ReadAllDataModel;

/**
 * Created by rkarthic on 22/03/18.
 */

public class ShareContentActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String contentToBeShared = getIntent().getExtras().getString("contentToBeShared");

       /* ReadAllDataModel contentData;
        contentData = new ReadAllDataModel();
        contentData = new Gson().fromJson(contentToBeShared, ReadAllDataModel.class);*/

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, contentToBeShared);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
        finish();

    }
}
