package com.mygov.parivartan.mygovhack.employer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mygov.parivartan.mygovhack.R;
import com.mygov.parivartan.mygovhack.employee.Employee;
import com.mygov.parivartan.mygovhack.employer.adapter.EmployeeAdapter;
import com.mygov.parivartan.mygovhack.employer.model.EmployeeDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepak on 01-04-2017.
 */

public class DisplayEmployee extends Fragment {

    EditText et_search;
    ImageView bt_search;
    ListView listView;
    Spinner spinner_sort;

    private DatabaseReference databaseReference;
    EmployeeAdapter employeeAdapter;
    public String data ;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jobs_search,container,false);

        //Getting id of Views
        listView = (ListView)view.findViewById(R.id.list_searched_job);
        et_search = (EditText)view.findViewById(R.id.et_search);
        bt_search = (ImageView) view.findViewById(R.id.bt_search);
        spinner_sort = (Spinner)view.findViewById(R.id.spinner_sort);


        final List<EmployeeDetail> employeeDetails = new ArrayList<>();
         employeeAdapter = new EmployeeAdapter(getContext()
                ,R.layout.suggestion_employee_list,employeeDetails);
        listView.setAdapter(employeeAdapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("Employee");

        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(employeeAdapter != null){
                    employeeAdapter.clear();
                }
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Searching Jobs");
                progressDialog.show();

                data = et_search.getText().toString();

                databaseReference.orderByChild("skill1").equalTo(data)
                        .addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                Employee emp = dataSnapshot.getValue(Employee.class);
                                EmployeeDetail employeeDetail = new EmployeeDetail(emp.empname,emp.age,
                                        emp.sex,emp.city,emp.skill1,emp.skill2,emp.skill3,emp.mobile);
                                employeeAdapter.add(employeeDetail);
                                progressDialog.dismiss();

                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                //If adapter is empty show a toast
                progressDialog.dismiss();
                if(employeeAdapter == null){
                    Toast.makeText(getContext(), "No match Found", Toast.LENGTH_SHORT).show();
                }
                listView.setAdapter(employeeAdapter);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        employeeAdapter.clear();
    }
}
