package com.example.otpsender;


public class ContactModel {


    private String fname;
    private  String lname;
    private String phone_number;


    public ContactModel(String fname, String lname, String phone_number) {
        this.fname = fname;
        this.lname = lname;
        this.phone_number = phone_number;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
