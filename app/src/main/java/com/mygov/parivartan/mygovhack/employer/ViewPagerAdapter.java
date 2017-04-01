package com.mygov.parivartan.mygovhack.employer;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mygov.parivartan.mygovhack.R;

/**
 * Created by deepak on 01-04-2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    public ViewPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0 ){
            return new DisplayEmployee();
        }else
        return new EmployerSetting();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position ==0){
            return mContext.getString(R.string.emp_search_result);
        }else if(position ==1){
            return mContext.getString(R.string.employer_profile);
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
