package com.mygov.parivartan.mygovhack.employer;

/**
 * Created by deepak on 01-04-2017.
 */

public class Employer {

    public String name;
    public String intro;
    public String city;
    public String state;
    public String req_skill1;
    public String req_skill2;
    public String req_skill3;

    public Employer(){
    }

    public Employer(String name,String intro,String city,String req_skill1,String req_skill2,String req_skill3,String state){
        this.name = name;
        this.intro = intro;
        this.state = state;
        this.city = city;
        this.req_skill1 = req_skill1;
        this.req_skill2 = req_skill2;
        this.req_skill3 = req_skill3;
    }
    public String getReq_skill1() {
        return req_skill1;
    }

    public String getCity() {
        return city;
    }
}
