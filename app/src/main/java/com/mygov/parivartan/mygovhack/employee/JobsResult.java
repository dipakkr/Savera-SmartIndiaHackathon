package com.mygov.parivartan.mygovhack.employee;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mygov.parivartan.mygovhack.R;
import com.mygov.parivartan.mygovhack.employee.adapter.JobAdapter;
import com.mygov.parivartan.mygovhack.employer.Employer;
import com.mygov.parivartan.mygovhack.employer.EmployerProfile;

import java.util.ArrayList;
import java.util.List;

import static com.mygov.parivartan.mygovhack.R.string.emp_search_result;
import static com.mygov.parivartan.mygovhack.R.string.employee;

/**
 * Created by deepak on 01-04-2017.
 */

public class JobsResult extends Fragment {

    private ListView mJobResult;
    private JobAdapter mJobAdapter;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    LinearLayout linearlayout;
    String val_key;
    String key = "req_skill1";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_jobs, container, false);
        linearlayout = (LinearLayout)view.findViewById(R.id.rootlayout);
        setHasOptionsMenu(true);

        //Search in database
        mAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference("Employer");

        mJobResult = (ListView) view.findViewById(R.id.list_jobs);
        final List<JobItem> jobItemList = new ArrayList<>();
        mJobAdapter = new JobAdapter(getContext(), R.layout.list_item_jobs, jobItemList);

        /*mRef.orderByChild(key).equalTo(val_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Employer employer = snapshot.getValue(Employer.class);
                    JobItem jobItem = new JobItem(employer.name, employer.city, employer.state, employer.intro);
                    mJobAdapter.add(jobItem);
                }
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });*/

        refresh();

        return view;
    }
    private void refresh(){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        Employer employer = new Employer();

        val_key = employer.getReq_skill1();

        mRef.orderByChild(key).equalTo("Android").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    Employer child = dataSnapshot.getValue(Employer.class);
                    JobItem jobItem = new JobItem(child.name, child.city, child.state, child.intro);
                    mJobAdapter.add(jobItem);
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
        progressDialog.dismiss();
        if(mJobAdapter == null){
            Toast.makeText(getContext(), "No match Found", Toast.LENGTH_SHORT).show();
        }else{
            mJobResult.setAdapter(mJobAdapter);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_sort,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.sort_skill :
                refresh();
                break;
            case R.id.sort_city :
                key = "city";
                val_key = "New Delhi" ;
                Log.e("Skill Sorting pressed","true");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}