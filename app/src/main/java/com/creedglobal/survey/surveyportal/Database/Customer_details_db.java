package com.creedglobal.survey.surveyportal.Database;


public class Customer_details_db {
String adminname,email_id,mobile_nos,occupation,pswd,temp_2,temp_3,user_type ="";

    public Customer_details_db() {
    }

    public Customer_details_db(String adminname, String email_id, String mobile_nos, String occupation, String pswd, String temp_2, String temp_3, String user_type) {
        this.adminname = adminname;
        this.email_id = email_id;
        this.mobile_nos = mobile_nos;
        this.occupation = occupation;
        this.pswd = pswd;
        this.temp_2 = temp_2;
        this.temp_3 = temp_3;
        this.user_type = user_type;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getMobile_nos() {
        return mobile_nos;
    }

    public void setMobile_nos(String mobile_nos) {
        this.mobile_nos = mobile_nos;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getTemp_2() {
        return temp_2;
    }

    public void setTemp_2(String temp_2) {
        this.temp_2 = temp_2;
    }

    public String getTemp_3() {
        return temp_3;
    }

    public void setTemp_3(String temp_3) {
        this.temp_3 = temp_3;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}

