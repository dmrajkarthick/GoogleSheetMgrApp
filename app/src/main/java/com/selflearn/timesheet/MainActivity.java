package com.selflearn.timesheet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.selflearn.timesheet.activity.DeleteData;
import com.selflearn.timesheet.activity.InsertData;
import com.selflearn.timesheet.activity.InternetConnection;
import com.selflearn.timesheet.activity.ReadAllData;
import com.selflearn.timesheet.activity.ReadData;
import com.selflearn.timesheet.activity.UpdateData;

public class MainActivity extends AppCompatActivity {

    private Button btRead, btReadAll, btInsert, btDelete, btUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        btRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetConnection.checkConnection(getApplicationContext())) {
                    Intent intent=new Intent(getApplicationContext(), ReadData.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });

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

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetConnection.checkConnection(getApplicationContext())) {
                    Intent intent=new Intent(getApplicationContext(), DeleteData.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetConnection.checkConnection(getApplicationContext())) {
                    Intent intent=new Intent(getApplicationContext(), UpdateData.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private void initialize() {
        btRead = (Button) findViewById(R.id.read_btn);
        btReadAll = (Button) findViewById(R.id.read_all_btn);
        btInsert = (Button) findViewById(R.id.insert_btn);
        btDelete = (Button) findViewById(R.id.delete_btn);
        btUpdate = (Button) findViewById(R.id.update_btn);
    }
}
