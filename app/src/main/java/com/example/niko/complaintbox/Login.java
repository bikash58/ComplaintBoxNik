package com.example.niko.complaintbox;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by niko on 7/25/17.
 */

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText username,password;
    private Button login, signup;
    public FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        login= (Button) findViewById(R.id.login);
        signup= (Button) findViewById(R.id.signup);

        firebaseAuth= FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(this);
        signup.setOnClickListener(this);

        progressBar= new ProgressBar(this);




    }

    @Override
    public void onClick(View v) {
        if (v == login)
        {
            userLogin();
        }


        if (v == signup)
            {
                signUp();
            }
        }

    private void signUp() {

        String uname=username.getText().toString().trim();
        String psw = password.getText().toString().trim();


        if (TextUtils.isEmpty(uname)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(psw)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (psw.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(uname, psw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Login.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        if (!task.isSuccessful()) {
                            Toast.makeText(Login.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(Login.this, Dashboard.class));
                            finish();
                        }
                    }
                });


    }


    private void userLogin() {
        String uname=username.getText().toString().trim();
        String psw = password.getText().toString().trim();

        if(TextUtils.isEmpty(uname))
        {
            Toast.makeText(this,"Email required",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(psw))
        {
            Toast.makeText(this,"Password required",Toast.LENGTH_LONG).show();
            return;

        }


        firebaseAuth.signInWithEmailAndPassword(uname,psw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            finish();
                            Intent intent= new Intent(getApplicationContext(),Dashboard.class);
                            startActivity(intent);
                        }
                        else
                        {
                            AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
                            alertDialog.setTitle("Notification");
                            alertDialog.setMessage("Login Invalid");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();

                        }

                    }
                });


    }

}