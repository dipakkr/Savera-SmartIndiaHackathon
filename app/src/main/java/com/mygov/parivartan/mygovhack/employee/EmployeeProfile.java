package com.mygov.parivartan.mygovhack.employee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mygov.parivartan.mygovhack.MainActivity;
import com.mygov.parivartan.mygovhack.R;

/**
 * Created by deepak on 01-04-2017.
 */

public class EmployeeProfile extends AppCompatActivity {

    //FireBase Variables
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    boolean backPressedOnce = false;

    TabLayout tabLayout;
    SimplePageAdapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            Intent intent = new Intent(EmployeeProfile.this, EmployeeLogin.class);
            startActivity(intent);
        }

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        adapter = new SimplePageAdapter(getApplicationContext(),this.getSupportFragmentManager());
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_employee,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();

        if(id ==R.id.logout){
            logout();
        }
        if(id == R.id.assist){
            //Command Here
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(EmployeeProfile.this,EmployeeRegistration.class));
    }

    @Override
    public void onBackPressed() {
        if(backPressedOnce ){

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }else if(!backPressedOnce){
                Toast.makeText(this,"Press again to exit",Toast.LENGTH_SHORT).show();
                backPressedOnce = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        backPressedOnce = false;
                    }
                },2000);
        }
    }
}
