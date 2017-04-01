package com.mygov.parivartan.mygovhack.employer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mygov.parivartan.mygovhack.R;


/**
 * Created by deepak on 01-04-2017.
 */

public class EmployerLogin extends AppCompatActivity {

    private EditText mEtemail, mEtPass;
    private Button mLogin;
    private Button mBack;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_login);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() !=null){
            //user is logged in
            finish();
            startActivity(new Intent(getApplicationContext(),EmployerProfile.class));
        }

        mEtemail = (EditText)findViewById(R.id.login_email);
        mEtPass = (EditText)findViewById(R.id.login_pass);
        mLogin = (Button)findViewById(R.id.bt_login);
        mBack = (Button) findViewById(R.id.back);

        progressDialog = new ProgressDialog(this);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void loginUser(){
        String email = mEtemail.getText().toString();
        String password = mEtPass.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this,"Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logging in Please Wait");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    Toast.makeText(EmployerLogin.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),EmployerProfile.class));
                }else{
                    Toast.makeText(EmployerLogin.this, "Login Error", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}

