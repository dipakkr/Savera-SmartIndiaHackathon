package com.mygov.parivartan.mygovhack.employer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mygov.parivartan.mygovhack.R;

/**
 * Created by deepak on 01-04-2017.
 */

public class EmployerSetting extends Fragment implements View.OnClickListener {

    Button updateProfile, bContact;
    TextView mName, mIntro, mCity, mSkill1, mContact;
    private static String TAG = EmployerSetting.class.getSimpleName();
    private DatabaseReference mReference;
    private ValueEventListener mValueEventListener;
    private SharedPreferences pref;
    private String aadhaar;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.employer_frag_setting,container,false);


        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        updateProfile = (Button)view.findViewById(R.id.bt_profile_update);
        mName = (TextView)view.findViewById(R.id.employer_name);
        mIntro = (TextView)view.findViewById(R.id.employer_intro);
        mCity = (TextView)view.findViewById(R.id.employer_city);
        mSkill1 = (TextView)view.findViewById(R.id.req_skill1);
        mContact = (TextView)view.findViewById(R.id.employer_contact);
        //mSkill2 = (TextView)view.findViewById(R.id.req_skill2);
        //mSkill3 = (TextView)view.findViewById(R.id.req_skill3);
        updateProfile.setOnClickListener(this);

        aadhaar = pref.getString("uid","");

        mReference = FirebaseDatabase.getInstance().getReference("Employer").child(aadhaar);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Employer employer = dataSnapshot.getValue(Employer.class);
                mName.setText(employer.name);
                mCity.setText(employer.city);
                mIntro.setText(employer.intro);
                mSkill1.setText(employer.req_skill1);
                //mContact.setText(employer.mobile);
                //mSkill2.setText(employer.req_skill2);
                //mSkill3.setText(employer.req_skill3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(getActivity(), "Failed to Load data", Toast.LENGTH_SHORT).show();
            }
        };
        mReference.addValueEventListener(valueEventListener);
        mValueEventListener = valueEventListener;
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mValueEventListener != null){
            mReference.removeEventListener(mValueEventListener);
        }
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getContext(),EmployerProfileUpdate.class));
    }
}
