package com.creedglobal.survey.surveyportal.Info;

import java.util.List;

/**
 * Created by SUJEET on 5/3/2016.
 */
public class QuestionData {
    private String qid;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    public String QuestionOption[][];
    public static List<QuestionData> quetionList;

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }


}
