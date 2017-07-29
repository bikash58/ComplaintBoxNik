package com.example.niko.complaintbox;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static android.R.attr.width;
import static com.example.niko.complaintbox.R.attr.height;


public class Dashboard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Activity activity=this;

                IntentIntegrator integrator= new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);

//        PlanarYUVLuminanceSource source = activity.getCameraManager().buildLuminanceSource(rotatedData, width, height);
                integrator.initiateScan();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null){
            if (result.getContents()==null)
            {
                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
            }
            else {

                Intent intent = new Intent(getBaseContext(), SubmitForm.class);
                String value= result.getContents();
                intent.putExtra("deparment",value);
                startActivity(intent);
            }

        }
        else {


            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}