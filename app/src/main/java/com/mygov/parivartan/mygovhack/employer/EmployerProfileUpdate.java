package com.mygov.parivartan.mygovhack.employer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mygov.parivartan.mygovhack.R;
import com.mygov.parivartan.mygovhack.employee.EmployeeProfileUpdate;

/**
 * Created by deepak on 01-04-2017.
 */

public class EmployerProfileUpdate extends AppCompatActivity{

    EditText mIntro, mContact, mSkill1, mCity, mName;
    EditText mSkill2, mSkill3;
    Button mUpload;
    private SharedPreferences sharedPreferences;
    private String aadhaar;

    //FireBase Variables
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employer_profile_update);

        // SharedPreference
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mReference = FirebaseDatabase.getInstance().getReference("Employer");

        mIntro = (EditText)findViewById(R.id.et_intro);
        mContact = (EditText)findViewById(R.id.et_contact);
        mSkill1 = (EditText)findViewById(R.id.emp_skill_1);
        mSkill2 = (EditText)findViewById(R.id.emp_skill_2);
        mSkill3 = (EditText)findViewById(R.id.emp_skill_3);
        mCity = (EditText)findViewById(R.id.et_city);
        mName = (EditText)findViewById(R.id.et_name);
        mUpload = (Button)findViewById(R.id.upload);

        //Getting Instance of FirBase
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        aadhaar = sharedPreferences.getString("uid","");
        Log.e("Aadhaar",aadhaar);

        String sname = sharedPreferences.getString("employer_name","");
        mReference = FirebaseDatabase.getInstance().getReference("Employer");
        mName.setText(sname);
        upload();
    }
    private void upload(){

        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value_employer_name = mName.getText().toString();
                String value_intro = mIntro.getText().toString();
                String value_contact = mContact.getText().toString();
                String value_skill1 = mSkill1.getText().toString();

                String value_skill2 = mSkill2.getText().toString();
                String value_skill3 = mSkill3.getText().toString();

                String value_city = mCity.getText().toString();

                Employer employer = new Employer( value_employer_name,value_intro,value_city,
                        value_skill1,value_skill2,value_skill3,value_contact);
                mReference.child(aadhaar).setValue(employer);
                Toast.makeText(EmployerProfileUpdate.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
