package com.creedglobal.survey.surveyportal.Database;

/**
 * Created by vishal on 2/22/2016.
 */
public class Details_db {


    //private variables
    int qid;
    String question = "";
    String surveyname = "";
    String response_1, response_2, response_3,response_4 = "";

    // Empty constructor
    public Details_db() {

    }





    // constructor
    public Details_db(String surveyname, String question, String response_1, String response_2, String response_3, String response_4) {
//        this.name = name;
//        this.phone = phone;
        this.surveyname=surveyname;
        this.question = question;
        this.response_1 = response_1;
        this.response_2 = response_2;
        this.response_3 = response_3;
        this.response_4 = response_4;
//        this.surveyname = surveyname;

    }

    //    // constructor
//    public Details(String name, String _phone_number){
//        this._name = name;
//        this._phone_number = _phone_number;
//    }
    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getSurveyname() {
        return surveyname;
    }

    public void setSurveyname(String surveyname) {
        this.surveyname = surveyname;
    }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse_1() {
        return response_1;
    }

    public void setResponse_1(String response_1) {
        this.response_1 = response_1;
    }

    public String getResponse_2() {
        return response_2;
    }

    public void setResponse_2(String response_2) {
        this.response_2 = response_2;
    }

    public String getResponse_3() {
        return response_3;
    }

    public void setResponse_3(String response_3) {
        this.response_3 = response_3;
    }
    public String getResponse_4() {
        return response_4;
    }

    public void setResponse_4(String response_4) {
        this.response_4 = response_4;
    }

//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }

}
