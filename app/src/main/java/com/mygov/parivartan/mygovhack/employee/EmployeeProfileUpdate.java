package com.mygov.parivartan.mygovhack.employee;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mygov.parivartan.mygovhack.R;

/**
 * Created by deepak on 01-04-2017.
 */

public class EmployeeProfileUpdate extends AppCompatActivity {

    EditText etAadhar, etContact, etCity, etName, etAge;
    EditText etSalary, etExperience;
    EditText skill1, skill2, skill3;
    Spinner mGender, mQualification,mState ;
    String value_gender = null;
    String value_qualification = null;
    Button btUpload;
    SharedPreferences sharedPreferences;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private String mUid;
    //FireBase Variables
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emp_profile_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //FireBase Instance
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        mDatabase = FirebaseDatabase.getInstance().getReference("Employee");

        // Variable declaration Et data
        etName = (EditText) findViewById(R.id.et_name);
        etAge = (EditText) findViewById(R.id.et_age);
        etAadhar = (EditText) findViewById(R.id.et_aadhaar);
        etContact = (EditText) findViewById(R.id.et_contact);
        etCity = (EditText) findViewById(R.id.et_city);
        mGender = (Spinner) findViewById(R.id.gender_spinner);
        mQualification = (Spinner) findViewById(R.id.qualification_spinner);
        mState = (Spinner)findViewById(R.id.spinner_state);
        skill1 = (EditText)findViewById(R.id.emp_skill_1);
        skill2 = (EditText)findViewById(R.id.emp_skill_2);
        skill3 = (EditText)findViewById(R.id.emp_skill_3);

        //Set the Value of EditText that is entered earlier

        etSalary = (EditText)findViewById(R.id.salary);
        etExperience = (EditText)findViewById(R.id.experince);

        //Getting Aadhaar from sharedPreferences
        String sname = sharedPreferences.getString("name","");
        mUid = sharedPreferences.getString("uid","");
        etName.setText(sname);
        etAadhar.setText(mUid);


        //Setup Spinner of Gender and Qualification
        setupGenderSpinner();
        setupQualificationSpinner();
        setupStateSpinner();
        btUpload = (Button) findViewById(R.id.upload);
        uploadData();
    }

    private void setupGenderSpinner() {
        ArrayAdapter genderAdapter = ArrayAdapter.createFromResource(this, R.array.array_gender_option,
                android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mGender.setAdapter(genderAdapter);
        mGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value_gender = (String) adapterView.getItemAtPosition(i);
                if (!TextUtils.isEmpty(value_gender)) {
                    if (value_gender.equals(getString(R.string.male))) {
                        value_gender = getString(R.string.male);
                    } else if (value_gender.equals(getString(R.string.female))) {
                        value_gender = getString(R.string.female);
                    } else {
                        value_gender = getString(R.string.other);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                value_gender = getString(R.string.male);
            }
        });
    }

    private void setupQualificationSpinner() {

        ArrayAdapter qualificationAdapter = ArrayAdapter.createFromResource(this, R.array.array_list_qualification,
                android.R.layout.simple_spinner_item);
        qualificationAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mQualification.setAdapter(qualificationAdapter);
        mQualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value_qualification = (String) adapterView.getItemAtPosition(i);
                if (!TextUtils.isEmpty(value_qualification)) {
                    if (value_qualification.equals(getString(R.string.qual_5))) {
                        value_qualification = getString(R.string.qual_5);
                    } else if (value_qualification.equals(getString(R.string.qual_6))) {
                        value_qualification = getString(R.string.qual_6);
                    } else if (value_qualification.equals(getString(R.string.qual_7))) {
                        value_qualification = getString(R.string.qual_7);
                    } else if (value_qualification.equals(getString(R.string.qual_8))) {
                        value_qualification = getString(R.string.qual_8);
                    } else if (value_qualification.equals(getString(R.string.qual_9))) {
                        value_qualification = getString(R.string.qual_9);
                    } else if (value_qualification.equals(getString(R.string.qual_10))) {
                        value_qualification = getString(R.string.qual_10);
                    } else if (value_qualification.equals(getString(R.string.qual_12))) {
                        value_qualification = getString(R.string.qual_12);
                    } else if (value_qualification.equals(getString(R.string.qual_13))) {
                        value_qualification = getString(R.string.qual_13);
                    } else {
                        value_qualification = getString(R.string.qual_12);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                value_qualification = getString(R.string.qual_12);
            }
        });
    }

    private void setupStateSpinner() {
        ArrayAdapter stateAdapter = ArrayAdapter.createFromResource(this, R.array.array_state,
                android.R.layout.simple_spinner_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mState.setAdapter(stateAdapter);
        mState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Write Your code here
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Write Your code here
            }
        });
    }
    private void uploadData() {
        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value_name = etName.getText().toString();
                String value_age = etAge.getText().toString();
                String value_aadhaar = etAadhar.getText().toString();
                String value_Contact = etContact.getText().toString();
                String value_city = etCity.getText().toString();
                String value_skill1 = skill1.getText().toString();
                String value_skill2 = skill2.getText().toString();
                String value_skill3 = skill3.getText().toString();

                String value_salary = etSalary.getText().toString();
                String value_experience = etExperience.getText().toString();

                String email = firebaseUser.getEmail();
                String username = email.split("@")[0];

                Employee employee = new Employee(username,value_name,value_age,value_gender,value_aadhaar,
                        value_Contact,value_qualification,
                        value_city,value_skill1,value_skill2,value_skill3,value_salary,value_experience);

                mDatabase.child(mUid).setValue(employee);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("upload_content","1");
                Toast.makeText(EmployeeProfileUpdate.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
