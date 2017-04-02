package com.mygov.parivartan.mygovhack.employer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mygov.parivartan.mygovhack.R;
import com.mygov.parivartan.mygovhack.employee.EmployeeLogin;
import com.mygov.parivartan.mygovhack.employee.EmployeeProfile;
import com.mygov.parivartan.mygovhack.employee.EmployeeRegistration;

/**
 * Created by deepak on 01-04-2017.
 */

public class EmployerRegistration extends AppCompatActivity {
    //Firebase Variables
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;

    private EditText mEmail;
    private EditText mPassword;
    private TextView mLogin;
    private Button mRegister;
    private EditText mName;
    private EditText mAadhaar;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_reg);

        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        mEmail = (EditText) findViewById(R.id.reg_email);
        mPassword = (EditText) findViewById(R.id.reg_password);
        mRegister = (Button) findViewById(R.id.register);
        mLogin = (TextView) findViewById(R.id.goto_login);
        mAadhaar = (EditText)findViewById(R.id.emp_reg_aadhaar);

        TextView textView = (TextView)findViewById(R.id.emp_title);
        textView.setText(R.string.employer_signup);


        progressDialog = new ProgressDialog(this);

        if(mAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(EmployerRegistration.this,EmployerProfile.class));
        }

        mDatabase = FirebaseDatabase.getInstance().getReference("Employer");

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),EmployerLogin.class));
            }
        });
    }

    private void registerUser(){
        final String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        final String aadhaar = mAadhaar.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("uid",aadhaar);
        editor.apply();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Incorrect Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(aadhaar)){
            Toast.makeText(this, "Enter Aadhaar No. ", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering Please Wait");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Employer employer = new Employer(email,"","","","");
                            mDatabase.child(aadhaar).setValue(employer);
                            Toast.makeText(EmployerRegistration.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(EmployerRegistration.this,EmployerPreUpdateProfile.class));
                        }else{
                            Toast.makeText(EmployerRegistration.this, "Registration Error", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}

