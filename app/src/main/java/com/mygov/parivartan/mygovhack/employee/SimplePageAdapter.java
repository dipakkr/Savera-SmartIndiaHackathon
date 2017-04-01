package com.mygov.parivartan.mygovhack.employee;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mygov.parivartan.mygovhack.R;

/**
 * Created by deepak on 01-04-2017.
 */

public class SimplePageAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public SimplePageAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new JobSearch();
        }
        else if(position ==1 ){
            return new JobsResult();
        }
        else if(position ==2 ){
            return new EmployeeSetting();
        }
       return new JobSearch();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position ==0){
            return mContext.getString(R.string.search);
        }
        else if(position ==1){
            return mContext.getString(R.string.jobs);
        }
        else if(position ==2)
        {
            return mContext.getString(R.string.Profile);
        }
        else
           return mContext.getString(R.string.Profile);

    }
}
