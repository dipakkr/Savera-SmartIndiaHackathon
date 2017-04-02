package com.mygov.parivartan.mygovhack.employer.model;

/**
 * Created by deepak on 23-03-2017.
 */

public class EmployeeDetail {
    public String emp_name;
    public String emp_age;
    public String emp_gender;
    public String emp_city;
    public String emp_skill1;
    //public String emp_skill2;
    //public String emp_skill3;
    public String emp_mobile;

    public EmployeeDetail(){
    }
    public EmployeeDetail(String emp_name,String emp_age, String emp_gender,
                          String emp_city,String emp_skill1,String emp_mobile){
        this.emp_name = emp_name;
        this.emp_age =emp_age;
        this.emp_city = emp_city;
        this.emp_gender = emp_gender;
        this.emp_skill1 = emp_skill1;
        //this.emp_skill2 = emp_skill2;
        //this.emp_skill3 = emp_skill3;
        this.emp_mobile = emp_mobile;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public String getEmp_age() {
        return emp_age;
    }

    public String getEmp_gender() {
        return emp_gender;
    }

    public String getEmp_city() {
        return emp_city;
    }

    public String getEmp_skill() {
        return emp_skill1;
    }

    public String getEmp_mobile() {
        return emp_mobile;
    }
}
