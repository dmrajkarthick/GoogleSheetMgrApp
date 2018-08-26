package com.selflearn.timesheet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.selflearn.timesheet.activity.InsertData;
import com.selflearn.timesheet.activity.InternetConnection;
import com.selflearn.timesheet.activity.ReadAllData;

public class MainActivity extends AppCompatActivity {

    private Button btReadAll, btInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        btReadAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetConnection.checkConnection(getApplicationContext())) {
                    Intent intent=new Intent(getApplicationContext(), ReadAllData.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetConnection.checkConnection(getApplicationContext())) {
                    Intent intent=new Intent(getApplicationContext(), InsertData.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void initialize() {
        btReadAll = (Button) findViewById(R.id.read_all_btn);
        btInsert = (Button) findViewById(R.id.createButton);
    }
}
