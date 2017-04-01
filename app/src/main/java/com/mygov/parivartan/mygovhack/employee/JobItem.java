package com.mygov.parivartan.mygovhack.employee;

/**
 * Created by deepak on 01-04-2017.
 */

public class JobItem  {
    public String result_name;
    public String result_age;
    public String result_gender;
    public String result_mobile;

    public JobItem(){
    }
    public JobItem(String result_name,String result_age,String result_gender,String result_mobile){
        this.result_name = result_name;
        this.result_age = result_age;
        this.result_mobile = result_mobile;
        this.result_gender = result_gender;
    }

    public String getResult_name() {
        return result_name;
    }

    public String getResult_age() {
        return result_age;
    }

    public String getResult_mobile() {
        return result_mobile;
    }

    public String getResult_gender() {
        return result_gender;
    }

    public void setResult_name(String result_name) {
        this.result_name = result_name;
    }

    public void setResult_age(String result_age) {
        this.result_age = result_age;
    }

    public void setResult_gender(String result_gender) {
        this.result_gender = result_gender;
    }

    public void setResult_mobile(String result_mobile) {
        this.result_mobile = result_mobile;
    }

}
