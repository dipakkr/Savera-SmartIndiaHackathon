package com.mygov.parivartan.mygovhack.employee;

/**
 * Created by deepak on 01-04-2017.
 */

public class Employee {
    public String username;
    public String empname;
    public String age;
    public String sex;
    public String uid_no;
    public String mobile;
    public String city;
    public String qualification;
    public String skill1;
    //public String skill2;
    //public  String skill3;


    public Employee(){

    }

    public Employee(String username,String empname, String age, String sex, String uid_no,
                    String mobile, String qualification, String city,String skill1) {

        this.username = username;
        this.empname = empname;
        this.age = age;
        this.sex = sex;
        this.uid_no = uid_no;
        this.mobile = mobile;
        this.qualification = qualification;
        this.city = city;
        this.skill1 = skill1;
        //this.skill2 =skill2;
        //this.skill3 = skill3;
    }

    public String getSkill1() {
        return skill1;
    }
}


