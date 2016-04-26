package com.creedglobal.survey.surveyportal.Database;

/**
 * Created by cp on 4/25/2016.
 */
public class Details_user {

String username;
    String mobile_no;
    String email;
    String msg;
    String ques_1;
    String ques_2;
    String ques_3;
    String ques_4;
    String ques_5;
    String temp_1 = "";
    public Details_user() {
    }

    public Details_user(String username, String ques_4, String ques_5) {
        this.username = username;
        this.ques_4 = ques_4;
        this.ques_5 = ques_5;
    }

    public Details_user(String username, String mobile_no, String email, String msg, String ques_1, String ques_2, String ques_3, String temp_1) {
        this.username = username;
        this.mobile_no = mobile_no;
        this.email = email;
        this.msg = msg;
        this.ques_1 = ques_1;
        this.ques_2 = ques_2;
        this.ques_3 = ques_3;
        this.temp_1 = temp_1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getQues_1() {
        return ques_1;
    }

    public void setQues_1(String ques_1) {
        this.ques_1 = ques_1;
    }

    public String getQues_2() {
        return ques_2;
    }

    public void setQues_2(String ques_2) {
        this.ques_2 = ques_2;
    }

    public String getQues_3() {
        return ques_3;
    }

    public void setQues_3(String ques_3) {
        this.ques_3 = ques_3;
    }

    public String getTemp_1() {
        return temp_1;
    }

    public void setTemp_1(String temp_1) {
        this.temp_1 = temp_1;
    }

    public String getQues_4() {
        return ques_4;
    }

    public void setQues_4(String ques_4) {
        this.ques_4 = ques_4;
    }

    public String getQues_5() {
        return ques_5;
    }

    public void setQues_5(String ques_5) {
        this.ques_5 = ques_5;
    }
}
