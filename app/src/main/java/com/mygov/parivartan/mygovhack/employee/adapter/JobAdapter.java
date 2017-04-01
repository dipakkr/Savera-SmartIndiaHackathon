package com.mygov.parivartan.mygovhack.employee.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mygov.parivartan.mygovhack.R;
import com.mygov.parivartan.mygovhack.employee.JobItem;
import java.util.List;

/**
 * Created by deepak on 01-04-2017.
 */

public class JobAdapter extends ArrayAdapter<JobItem>{

    public JobAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<JobItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater()
                    .inflate(R.layout.list_item_jobs,parent,false);
        }
        JobItem jobItem = getItem(position);

        TextView nameText = (TextView)convertView.findViewById(R.id.result_name);
        TextView ageText = (TextView)convertView.findViewById(R.id.result_age);
        TextView mobileText = (TextView)convertView.findViewById(R.id.result_mobile);
        TextView genderText = (TextView)convertView.findViewById(R.id.result_gender);

        nameText.setText(jobItem.getResult_name());
        ageText.setText(jobItem.getResult_age());
        mobileText.setText(jobItem.getResult_mobile());
        genderText.setText(jobItem.getResult_gender());

        return convertView;

    }
}
