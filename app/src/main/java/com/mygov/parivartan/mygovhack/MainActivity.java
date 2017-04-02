package com.mygov.parivartan.mygovhack;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.mygov.parivartan.mygovhack.employee.EmployeeRegistration;
import com.mygov.parivartan.mygovhack.employer.EmployerRegistration;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button mEmployer, mEmployee;
    Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmployee = (Button) findViewById(R.id.bt_employee);
        mEmployer = (Button) findViewById(R.id.bt_employer);
        mSpinner = (Spinner) findViewById(R.id.lang);
        setupSpinner();

        mEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EmployeeRegistration.class));
            }
        });

        mEmployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EmployerRegistration.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupSpinner() {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.array_lang,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if(i ==0 ){

               }else if(i==1){

                   String languageToLoad = "hi"; // your language
                   Locale locale = new Locale(languageToLoad);
                   Locale.setDefault(locale);
                   Configuration config = new Configuration();
                   config.locale = locale;
                   getBaseContext().getResources().updateConfiguration(config,
                           getBaseContext().getResources().getDisplayMetrics());


                   Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                   startActivity(refresh);
                   finish();

               }else if(i==2){

                   String languageToLoad = "te"; // your language
                   Locale locale = new Locale(languageToLoad);
                   Locale.setDefault(locale);
                   Configuration config = new Configuration();
                   config.locale = locale;
                   getBaseContext().getResources().updateConfiguration(config,
                           getBaseContext().getResources().getDisplayMetrics());



                   Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                   startActivity(refresh);
                   finish();
               }else if(i==3){
                   String languageToLoad = "ta"; // your language
                   Locale locale = new Locale(languageToLoad);
                   Locale.setDefault(locale);
                   Configuration config = new Configuration();
                   config.locale = locale;
                   getBaseContext().getResources().updateConfiguration(config,
                           getBaseContext().getResources().getDisplayMetrics());


                   Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                   startActivity(refresh);
                   finish();
               }else{
                   String languageToLoad = "en"; // your language
                   Locale locale = new Locale(languageToLoad);
                   Locale.setDefault(locale);
                   Configuration config = new Configuration();
                   config.locale = locale;
                   getBaseContext().getResources().updateConfiguration(config,
                           getBaseContext().getResources().getDisplayMetrics());


                   Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                   startActivity(refresh);
                   finish();

               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "Please Make Selection", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
