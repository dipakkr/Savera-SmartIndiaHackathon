package com.mygov.parivartan.mygovhack.employee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mygov.parivartan.mygovhack.R;
import com.mygov.parivartan.mygovhack.employer.EmployerProfileUpdate;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;


/**
 * Created by deepak on 01-04-2017.
 */

public class EmployeeSetting extends Fragment implements View.OnClickListener{

    private DatabaseReference mReference;
    private ValueEventListener mEventListener;
    private String TAG = EmployeeSetting.class.getSimpleName();
    private static final int CAMERA_REQUEST_CODE = 1 ;
    private static final int RC_PHOTO_PICKER = 1;

    @Nullable
    TextView mTextName,mTextAge,mAadhaar,mContact,mCity,mSex,mQualification,mEmail;
    TextView mTxtSkill1, mTxtSkill2, mTxtSkill3, empSkill1, empCity;
    SharedPreferences sharedPreferences;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private StorageReference mStorage;
    ProgressDialog progressDialog;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_set_profile,container,false);

        Button updateProfile = (Button)view.findViewById(R.id.bt_profile_update);
        updateProfile.setOnClickListener(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        progressDialog = new ProgressDialog(getContext());
        //Instance
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        mStorage = FirebaseStorage.getInstance().getReference();

        //Id for field
        mTextName = (TextView)view.findViewById(R.id.emp_name);
        mTextAge = (TextView)view.findViewById(R.id.field_age);
        mAadhaar = (TextView)view.findViewById(R.id.field_uid);
        mContact = (TextView)view.findViewById(R.id.field_contact);
        mCity = (TextView)view.findViewById(R.id.field_city);
        mSex = (TextView)view.findViewById(R.id.field_sex);
        mEmail = (TextView)view.findViewById(R.id.field_email);
        mQualification = (TextView)view.findViewById(R.id.field_qualification);
        imageView = (ImageView)view.findViewById(R.id.imageview);
        imageView.setOnClickListener(this);

        mTxtSkill1 = (TextView)view.findViewById(R.id.txt_skill_1);
        mTxtSkill2 = (TextView)view.findViewById(R.id.txt_skill_2);
        mTxtSkill3 = (TextView)view.findViewById(R.id.txt_skill_3);
        //String name = sharedPreferences.getString("name","");

        String email = firebaseUser.getEmail();
        String userid = email.split("@")[0];
        String uid = sharedPreferences.getString("uid","");
        Log.e("Aadjaar Number",uid);
        Log.e("User id" ,userid);

        empSkill1 =(TextView)view.findViewById(R.id.emp_skill1);
        empCity = (TextView)view.findViewById(R.id.emp_city);
        mReference = FirebaseDatabase.getInstance().getReference("Employee").child(uid);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        ValueEventListener eventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Employee employee = dataSnapshot.getValue(Employee.class);
                mTextName.setText(employee.empname);
                mEmail.setText(employee.username);
                mTextAge.setText(employee.age);
                mAadhaar.setText(employee.uid_no);
                mSex.setText(employee.sex);
                mCity.setText(employee.city);
                mContact.setText(employee.mobile);
                mQualification.setText(employee.qualification);
                mTxtSkill1.setText(employee.skill1);
                mTxtSkill2.setText(employee.skill2);
                mTxtSkill3.setText(employee.skill3);
                empSkill1.setText(employee.skill1);
                empCity.setText(employee.city);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(getActivity(), "Failed to Load data", Toast.LENGTH_SHORT).show();
            }
        };
        mReference.addValueEventListener(eventListener);
        mEventListener = eventListener;
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mEventListener != null){
            mReference.removeEventListener(mEventListener);
        }
    }

    @Override
    public void onClick(View view) {
      int id  = view.getId();

        switch (id){
            case R.id.bt_profile_update :
                startActivity(new Intent(getActivity(),EmployeeProfileUpdate.class));
                break;

            case R.id.imageview :

                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                imageView.setImageResource(0);
                startActivityForResult(Intent.createChooser(i, "Complete action using"), RC_PHOTO_PICKER);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){
            Uri uri = data.getData();

            StorageReference filepath = mStorage.child("photos").child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri imageUrl = taskSnapshot.getDownloadUrl();
                    if(imageUrl != null){
                        String emp_img_url = imageUrl.toString();
                        Picasso.with(imageView.getContext()).load(emp_img_url).into(imageView);
                    }
                }
            });
        }
    }
}
