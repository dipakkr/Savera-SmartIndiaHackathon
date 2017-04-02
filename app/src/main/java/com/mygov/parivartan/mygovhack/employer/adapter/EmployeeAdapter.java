package com.mygov.parivartan.mygovhack.employer.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mygov.parivartan.mygovhack.R;
import com.mygov.parivartan.mygovhack.employer.model.EmployeeDetail;

import java.util.List;

/**
 * Created by deepak on 01-04-2017.
 */

public class EmployeeAdapter extends ArrayAdapter<EmployeeDetail> {

    EmployeeDetail employeeDetail;
    String mobile;
    Context context;


    public EmployeeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<EmployeeDetail> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater()
                    .inflate(R.layout.suggestion_employee_list,parent,false);
        }

        TextView mName, mAge, mSex, mCity, mSkill1, mSkill2, mSkill3, mContact, mExperience, mSalary;
        ImageView caller;

        employeeDetail = getItem(position);

        mName = (TextView)convertView.findViewById(R.id.result_name);
        mAge = (TextView)convertView.findViewById(R.id.result_age);
        mSex = (TextView)convertView.findViewById(R.id.result_gender);
        mCity = (TextView)convertView.findViewById(R.id.result_city);
        mSkill1 = (TextView)convertView.findViewById(R.id.res_skill1);
        mSkill2 = (TextView)convertView.findViewById(R.id.res_skill2);
        mSkill3 = (TextView)convertView.findViewById(R.id.res_skill3);
        mContact = (TextView)convertView.findViewById(R.id.result_contact);
        mExperience = (TextView)convertView.findViewById(R.id.res_exp);
        mSalary = (TextView)convertView.findViewById(R.id.res_salary);
        caller = (ImageView)convertView.findViewById(R.id.img_call);

        mName.setText(employeeDetail.getEmp_name());
        mAge.setText(employeeDetail.getEmp_age());
        mContact.setText(employeeDetail.getEmp_mobile());
        mCity.setText(employeeDetail.getEmp_city());
        mSex.setText(employeeDetail.getEmp_gender());
        mSkill1.setText(employeeDetail.getEmp_skill());
        mSkill2.setText(employeeDetail.getEmp_skill2());
        mSkill3.setText(employeeDetail.getEmp_skill3());
        mobile = employeeDetail.getEmp_mobile();

        mExperience.setText(employeeDetail.getEmp_experience());
        mSalary.setText(employeeDetail.getEmp_salary());
        caller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(employeeDetail.getEmp_mobile()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

}
