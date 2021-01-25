package com.example.programming_quiz_app.models;

public class Question {

    private int id;
    private String ques;
    private String optionOne;
    private String optionTow;
    private String optionThree;
    private String optionFour;
    private String rightAns;

    public Question(String ques, String optionOne, String optionTow, String optionThree, String optionFour, String rightAns) {
        this.ques = ques;
        this.optionOne = optionOne;
        this.optionTow = optionTow;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.rightAns = rightAns;
    }

    public int getId() {
        return id;
    }

    public String getQues() {
        return ques;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public String getOptionTow() {
        return optionTow;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public String getOptionFour() {
        return optionFour;
    }

    public String getRightAns() {
        return rightAns;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public void setOptionTow(String optionTow) {
        this.optionTow = optionTow;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    public void setOptionFour(String optionFour) {
        this.optionFour = optionFour;
    }

    public void setRightAns(String rightAns) {
        this.rightAns = rightAns;
    }

}
