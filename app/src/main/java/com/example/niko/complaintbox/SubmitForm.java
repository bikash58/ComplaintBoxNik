package com.example.niko.complaintbox;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class SubmitForm extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submitform);
        Bundle bundle=getIntent().getExtras();
        String value=bundle.getString("deparment");
        TextView department= (TextView) findViewById(R.id.department_name);
        department.setText(value);

    }


}