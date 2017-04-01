package com.mygov.parivartan.mygovhack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mygov.parivartan.mygovhack.employee.EmployeeRegistration;
import com.mygov.parivartan.mygovhack.employer.EmployerRegistration;

public class MainActivity extends AppCompatActivity {

    Button mEmployer, mEmployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmployee = (Button)findViewById(R.id.bt_employee);
        mEmployer = (Button)findViewById(R.id.bt_employer);

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
}
